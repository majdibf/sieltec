package db;

import java.util.Date;

public class Conducteur {
	private Long id;
	private String nom;
	private String prenom;
	private String contact;
	private int version;

	public Conducteur(Long id, String nom, String prenom, String contact,
			int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.contact = contact;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
