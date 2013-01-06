package com.sieltec.db;

import java.util.Date;

public class Station {
	private double id;
	private String nom;
	private String longitude;
	private String latitude;
	private int version;

	public Station(double id, String nom, String longitude, String latitude,
			int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
