package com.utbm.pokemarium;

import java.util.HashMap;
import java.util.List;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public class PokemonTerre extends Pokemon {

	public PokemonTerre(String name, HashMap<String, Case> map) {
		super(name, ZONE.TERRE, map);
	}

	
	// attaque d'une façon aléatoire les pokemon autour de lui 2 fois successive
	@Override
	protected void attaquerVoisins(List<Case> casesVoisinesAccessibles) {
		for (Case uneCase : casesVoisinesAccessibles) {
			if (uneCase.getOccupant() != null) {
				if (randomizer.nextBoolean()) {
					Pokemon adversaire = uneCase.getOccupant();
					System.out.println(this.getName() + " : attacking "
							+ adversaire.getName() + " ...");
					attaquer(adversaire);
					attaquer(adversaire);
					}
				}
		}

	}
	
	//se déplace n'importe ou il choisie une case aléatoire parmi les cases vide autour de lui et se deplace
	@Override
	protected void bouger(List<Case> casesLibres) {
		if (!casesLibres.isEmpty()) {
			Case destination = casesLibres.get(randomizer.nextInt(casesLibres.size()));
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
