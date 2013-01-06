package com.sieltec.db;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class ElementParcours {
	private double id;
	private Parcours parcours;
	private Station stationDep;
	private Station stationArr;
	private Minutes duree;
	private Minutes dureeArret;
	private int version;

	public ElementParcours(double id, Parcours parcours, Station stationDep,
			Station stationArr, Minutes duree, Minutes dureeArret, int version) {
		super();
		this.id = id;
		this.parcours = parcours;
		this.stationDep = stationDep;
		this.stationArr = stationArr;
		this.duree = duree;
		this.dureeArret = dureeArret;
		this.version = version;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public Station getStationDep() {
		return stationDep;
	}

	public void setStationDep(Station stationDep) {
		this.stationDep = stationDep;
	}

	public Station getStationArr() {
		return stationArr;
	}

	public void setStationArr(Station stationArr) {
		this.stationArr = stationArr;
	}

	public Minutes getDuree() {
		return duree;
	}

	public void setDuree(Minutes duree) {
		this.duree = duree;
	}

	public Minutes getDureeArret() {
		return dureeArret;
	}

	public void setDureeArret(Minutes dureeArret) {
		this.dureeArret = dureeArret;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return parcours.getNom() + "#(" + stationDep.getNom() + "===" + duree.getMinutes() + "===>" + stationArr.getNom() + " : " + dureeArret.getMinutes() + ")";
	}

}
