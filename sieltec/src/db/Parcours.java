package db;

import java.util.ArrayList;
import java.util.List;

public class Parcours {
	private double id;
	private String nom;
	private int idLigne;
	// private List<ElementParcours> elementsParcours;
	private int version;

	public Parcours(double id, String nom, int idLigne, int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.idLigne = idLigne;
		this.version = version;
	}

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

	public int getIdLigne() {
		return idLigne;
	}

	public void setIdLigne(int idLigne) {
		this.idLigne = idLigne;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
