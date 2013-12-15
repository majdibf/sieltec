package com.utbm.pokemarium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public class PokemonEau extends Pokemon {

	public PokemonEau(String name, HashMap<String, Case> map) {
		super(name, ZONE.EAU, map);
	}
	
	// attaque les pokemon les plus faible que lui (le plus faible pv)
	@Override
	protected void attaquerVoisins(List<Case> casesVoisinesAccessibles) {
		
		List<Pokemon> adversairesFaibles=new ArrayList<Pokemon>();
		for(Case uneCase : casesVoisinesAccessibles){
			if(uneCase.getOccupant() != null){
					if(uneCase.getOccupant().getPointsVie()<this.getPointsVie()){
						adversairesFaibles.add(uneCase.getOccupant());
					}
				}
			}
			
		for(Pokemon adversaire : adversairesFaibles){	
			System.out.println(this.getName() + " : attacking "
					+ adversaire.getName() + " ...");
			attaquer(adversaire);
		}
	

	}

	@Override
	protected void bouger(List<Case> casesLibres) {
		if (!casesLibres.isEmpty()) {
			Case destination = casesLibres.get(randomizer.nextInt(casesLibres
					.size()));
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
}
