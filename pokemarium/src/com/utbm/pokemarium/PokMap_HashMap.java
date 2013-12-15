package com.utbm.pokemarium;

import java.util.HashMap;
import java.util.List;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public class PokMap_HashMap implements PokMap {
	
	HashMap<String, Case> map = new HashMap<>();

	

	@Override
	public void addCase(int ligne, int colonne, Case uneCase) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCase(Coordonnees coordonnees, Case uneCase) {
		// TODO Auto-generated method stub

	}

	@Override
	public Case getCase(int ligne, int colonne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Case getCase(Coordonnees coordonnees) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZONE getZone(int ligne, int colonne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZONE getZone(Coordonnees coordonnees) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOccupant(int ligne, int colonne, Pokemon occupant) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOccupant(Coordonnees coordonnees, Pokemon occupant) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pokemon getOccupant(int ligne, int colonne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pokemon getOccupant(Coordonnees coordonnees) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viderCase(int ligne, int colonne) {
		// TODO Auto-generated method stub

	}

	@Override
	public void viderCase(Coordonnees coordonnees) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Coordonnees> getCasesVoisinesAccessibles(int ligne, int colonne) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coordonnees> getCasesVoisinesAccessibles(Coordonnees coordonnees) {
		// TODO Auto-generated method stub
		return null;
	}

}
