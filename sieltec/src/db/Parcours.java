package db;

import java.util.ArrayList;
import java.util.List;

public class Parcours {
	private Long id;
	private String nom;
	private Long ligneId;
	private int version;

	
	public Parcours(Long id, String nom, Long ligneId, int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.ligneId = ligneId;
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


	public Long getLigneId() {
		return ligneId;
	}


	public void setLigneId(Long ligneId) {
		this.ligneId = ligneId;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


}
