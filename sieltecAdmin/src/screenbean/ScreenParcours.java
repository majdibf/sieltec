package screenbean;

import db.Ligne;

public class ScreenParcours {

	long id;
	String nom;
	Ligne ligne;

	public ScreenParcours(long id, String nom, Ligne ligne) {
		super();
		this.id = id;
		this.nom = nom;
		this.ligne = ligne;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
