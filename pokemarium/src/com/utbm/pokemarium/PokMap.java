package com.utbm.pokemarium;

import java.util.List;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public interface PokMap {
	
	public void addCase(int ligne, int colonne, Case uneCase);
	public void addCase(Coordonnees coordonnees, Case uneCase);
	
	public Case getCase(int ligne, int colonne);
	public Case getCase(Coordonnees coordonnees);
	
	public ZONE getZone(int ligne, int colonne);
	public ZONE getZone(Coordonnees coordonnees);
	
	public void setOccupant(int ligne, int colonne, Pokemon occupant);
	public void setOccupant(Coordonnees coordonnees, Pokemon occupant);
	
	public Pokemon getOccupant(int ligne, int colonne);
	public Pokemon getOccupant(Coordonnees coordonnees);
	
	public void viderCase(int ligne, int colonne);
	public void viderCase(Coordonnees coordonnees);
	
	public List<Coordonnees> getCasesVoisinesAccessibles(int ligne, int colonne);
	public List<Coordonnees> getCasesVoisinesAccessibles(Coordonnees coordonnees);
	

}
