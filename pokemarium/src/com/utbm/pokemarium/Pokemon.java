package com.utbm.pokemarium;


import com.utbm.pokemarium.POK_Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public abstract class Pokemon extends Thread {
	
	static Random randomizer = new Random();
	
	private HashMap<String, Case> map;
	private ZONE type;
	private int pointsVie = POK_Constantes.NB_POINTS_VIE_INITIALE;
	private int attaque;
	private int defence;
	private String caseKey;

	public Pokemon(String name, ZONE type, HashMap<String, Case> map) {
		super(name);
		this.type = type;
		switch (type) {
		case TERRE:
			this.attaque = POK_Constantes.ATTAQUE_TERRE;
			this.defence = POK_Constantes.DEFENCE_TERRE;
			break;
		case HERBE:
			this.attaque = POK_Constantes.ATTAQUE_HERBE;
			this.defence = POK_Constantes.DEFENCE_HERBE;
			break;
		case EAU:
			this.attaque = POK_Constantes.ATTAQUE_EAU;
			this.defence = POK_Constantes.DEFENCE_EAU;
			break;
		case FEU:
			this.attaque = POK_Constantes.ATTAQUE_FEU;
			this.defence = POK_Constantes.DEFENCE_FEU;			
			break;
		}
		
		this.map = map;
	}
		

	public HashMap<String, Case> getMap() {
		return map;
	}


	public void setMap(HashMap<String, Case> map) {
		this.map = map;
	}

	
	public String getCaseKey() {
		return caseKey;
	}


	public void setCaseKey(String caseKey) {
		this.caseKey = caseKey;
	}


	public ZONE getType() {
		return type;
	}


	public void setType(ZONE type) {
		this.type = type;
	}
	

	public int getPointsVie() {
		return pointsVie;
	}


	public void setPointsVie(int pointsVie) {
		this.pointsVie = pointsVie;
	}


	public int getAttaque() {
		return attaque;
	}


	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}


	public int getDefence() {
		return defence;
	}


	public void setDefence(int defence) {
		this.defence = defence;
	}

	public boolean isNotDead() {
		return (pointsVie > 0);
	}

	
	
	@Override
	public void run() {
		while (this.isNotDead()) {
			try {
				attackAndMove();
				updateGraphics();
//				long sleeping = ((randomizer.nextInt(100)/100) + 1) * POK_Constantes.POK_SLEEP;
				long sleeping = POK_Constantes.POK_SLEEP;
				System.out.println(getName() + " : sleeping " + sleeping);
				Thread.sleep(sleeping);
			} catch (Exception e) {
				System.out.println("Pokemon interrupted.");
			}
		}
		
		System.out.println(getName() + " : FIN");
	}
	
	protected abstract void attaquerVoisins(List<Case> casesVoisinesAccessibles);

	protected abstract void bouger(List<Case> casesLibres);

	public void attackAndMove() {
		
		List<Case> casesVoisinesAccessibles = getCasesVoisinesAccessibles();
		attaquerVoisins(casesVoisinesAccessibles);
		
		List<Case> casesLibres = new ArrayList<>();
		for(Case uneCase : casesVoisinesAccessibles){
			if(uneCase.getOccupant() == null){
				casesLibres.add(uneCase);
			}			
		}
		
		bouger(casesLibres);
	}
	
	public void attaquer (Pokemon adversaire){
		Object lock1;
		Object lock2;
		
		
		
		if(this.getId() < adversaire.getId()){
			lock1 = this;
			lock2 = adversaire;
		} else {
			lock1 = adversaire;
			lock2 = this;
		}
		
		synchronized (lock1) {
			synchronized (lock2) {
				if(this.isNotDead()){
					int pointsAttaque = this.attaque;
					
					ZONE zone = map.get(caseKey).zone;
					
					if(zone == type){
						pointsAttaque = pointsAttaque + 2;
					} else if(zone != ZONE.NEUTRE) {
						pointsAttaque = pointsAttaque - 2;
					}
					int pointsGagnes = adversaire.defendre(pointsAttaque);
					this.pointsVie  = this.pointsVie + pointsGagnes;				
					System.out.println(getName() + " : J'ai attaqué " + adversaire.getName() + " et j'ai gagné " + pointsGagnes + " points : " + this.pointsVie);
					if(!adversaire.isNotDead()){
						map.get(adversaire.getCaseKey()).setOccupant(null);
					}
				} else {
					System.out.println(getName() + " : Je ne peux plus attaquer, je suis mort");
				}
			}
		}
	}
	

	public int defendre (int pointsAttaque){
		int pointsDefence = this.defence;
		
		ZONE zone = map.get(caseKey).zone;
		
		if(zone == type){
			pointsDefence = pointsDefence + 1;
		} else if(zone != ZONE.NEUTRE) {
			pointsDefence = pointsDefence - 1;
		}
		int pointsPerdus = Math.max(0, pointsAttaque - pointsDefence);
		pointsPerdus = Math.min(pointsPerdus, pointsVie);
		this.pointsVie  = this.pointsVie - pointsPerdus;

		String mort = "";
		if(!this.isNotDead()){
			mort = " et je suis mort";			
		}
		System.out.println(getName() + " : J'ai perdu " + pointsPerdus + " points" + mort + " : " + this.pointsVie);
		return pointsPerdus;
		
	}
	
	
	private List<Case> getCasesVoisinesAccessibles(){
		
		List<Case> resultat = new ArrayList<>();
		
		int x = Integer.parseInt(caseKey.substring(0, caseKey.indexOf(':')));
		int y = Integer.parseInt(caseKey.substring(caseKey.indexOf(':') + 1));

		for(int i=x-1; i<=x+1; i++){
			for(int j=y-1; j<=y+1; j++){
				if(1<= i && i<= Pokemarium.nbLigne && 1<= j && j<= Pokemarium.nbColonne){
					String key = "" + i + ":" + j;
					if(!caseKey.equals(key)){
						Case uneCase = map.get(key);
						if(uneCase.zone != ZONE.VIDE){
							resultat.add(uneCase);
						}
					}
				}
			}			
		}

		return resultat;
	}
	
	private void updateGraphics() {
		
	}

}
