package com.sieltec.db;

import java.util.ArrayList;
import java.util.List;

public class Parcours {
	private double id;
	private String nom;
	private Ligne ligne;
	private List<ElementParcours> elementsParcours;

	public Parcours(double id, String nom, Ligne ligne, int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.ligne = ligne;
		this.version = version;
		elementsParcours = new ArrayList<ElementParcours>();
	}

	private int version;

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Ligne getLigne() {
		return ligne;
	}

	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<ElementParcours> getElementsParcours() {
		return elementsParcours;
	}

	
}
