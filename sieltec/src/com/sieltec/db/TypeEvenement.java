package com.sieltec.db;

public class TypeEvenement {
	private double id;
	private String libelle;
	private int version;

	public TypeEvenement(double id, String libelle, int version) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.version = version;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
