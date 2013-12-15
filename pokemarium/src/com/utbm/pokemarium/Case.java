package com.utbm.pokemarium;

import com.utbm.pokemarium.POK_Constantes.ZONE;

public class Case {
	
	ZONE zone;
	Pokemon occupant;
	String key;
	

	public Case(String key) {
		super();
		this.key = key;
	}

	public Pokemon getOccupant() {
		return occupant;
	}

	public void setOccupant(Pokemon occupant) {
		this.occupant = occupant;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ZONE getZone() {
		return zone;
	}

	public void setZone(ZONE zone) {
		this.zone = zone;
	}

	public void setZone(String zone) {
		switch (zone) {
		case "TERRE":
			this.zone = POK_Constantes.ZONE.TERRE;
			break;
		case "HERBE":
			this.zone = POK_Constantes.ZONE.HERBE;
			break;
		case "EAU":
			this.zone = POK_Constantes.ZONE.EAU;
			break;
		case "FEU":
			this.zone = POK_Constantes.ZONE.FEU;
			break;
		}
	
	}

	

}
