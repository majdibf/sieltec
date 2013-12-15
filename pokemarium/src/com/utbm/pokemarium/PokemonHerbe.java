package com.utbm.pokemarium;

import java.util.HashMap;
import java.util.List;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public class PokemonHerbe extends Pokemon {

	public PokemonHerbe(String name, HashMap<String, Case> map) {
		super(name, ZONE.HERBE, map);
	}

	//attaque d'une façon aléatoire tous les pokemon autour de lui sauf ses amis
	@Override
	protected void attaquerVoisins(List<Case> casesVoisinesAccessibles) {
		for (Case uneCase : casesVoisinesAccessibles) {
			if (uneCase.getOccupant() != null) {
				if (randomizer.nextBoolean()) {
					Pokemon adversaire = uneCase.getOccupant();

					if (!this.getType().equals(adversaire.getType())) {
						System.out.println(this.getName() + " : attacking "
								+ adversaire.getName() + " ...");
						attaquer(adversaire);
					}
				}
			}
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
