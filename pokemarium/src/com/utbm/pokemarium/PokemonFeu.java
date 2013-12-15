package com.utbm.pokemarium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public class PokemonFeu extends Pokemon {

	public PokemonFeu(String name, HashMap<String, Case> map) {
		super(name, ZONE.FEU, map);
	}

	// attaque tous les pokemon autour de lui
	@Override
	protected void attaquerVoisins(List<Case> casesVoisinesAccessibles) {
		for (Case uneCase : casesVoisinesAccessibles) {
			if (uneCase.getOccupant() != null) {
					Pokemon adversaire = uneCase.getOccupant();
					System.out.println(this.getName() + " : attacking "
							+ adversaire.getName() + " ...");
					attaquer(adversaire);
				}
		}

	}

	@Override
	protected void bouger(List<Case> casesLibres) {
		if (!casesLibres.isEmpty()) {
			Case destination = casesLibres.get(randomizer.nextInt(casesLibres
					.size()));
			
			double risque=calculRisque(destination);
			System.out.println(this.getName() + " : risque de la destination initial= ("+destination.getKey()+") : " + risque );
			
			for(Case tmpCase: casesLibres){
				if(tmpCase.getKey() != this.getCaseKey()){
					double tmpRisque=calculRisque(tmpCase);
					if(tmpRisque<risque){
						destination = tmpCase;
						risque= tmpRisque;
					}

				}

			}
			
			System.out.println(this.getName() + " : risque de la destination final= ("+destination.getKey()+") : " + risque);
		
			synchronized (destination) {
				if (destination.getOccupant() != null) {
					System.out.println(this.getName() + " : case "
							+ destination.getKey() + " déjà occupée par "
							+ destination.getOccupant().getName()
							+ ", Je reste donc à ma place : "
							+ this.getCaseKey());
				} else {
					System.out.println(this.getName() + " : " + getCaseKey()
							+ " ==> " + destination.getKey());
					getMap().get(getCaseKey()).setOccupant(null);
					destination.setOccupant(this);
					this.setCaseKey(destination.getKey());
				}
			}
		}
	}
	
	private double calculRisque(Case c) {
		List<Case> cases = new ArrayList<>();
		
		double sommeAttaque=0;
		
		int x = Integer.parseInt(c.getKey().substring(0, c.getKey().indexOf(':')));
		int y = Integer.parseInt(c.getKey().substring(c.getKey().indexOf(':') + 1));

		for(int i=x-1; i<=x+1; i++){
			for(int j=y-1; j<=y+1; j++){
				if(1<= i && i<= Pokemarium.nbLigne && 1<= j && j<= Pokemarium.nbColonne){
					String key = "" + i + ":" + j;
					if(!c.equals(key)){
						Case uneCase = this.getMap().get(key);
						if(uneCase.zone != ZONE.VIDE){
							cases.add(uneCase);
						}
					}
				}
			}			
		}
		
		for (Case tmpCase:cases){
			if(tmpCase.getOccupant() != null && tmpCase.getKey()!=this.getCaseKey()){
				sommeAttaque += tmpCase.getOccupant().getAttaque();
			}
		}		
		
		return sommeAttaque/8;
	}

}
