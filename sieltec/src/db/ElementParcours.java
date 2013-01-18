package db;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class ElementParcours {
	private double id;
	private int idParcours;
	private int idStationDep;
	private int idStationArr;
	private Minutes duree;
	private Minutes dureeArret;
	private int version;

	public ElementParcours(double id, int idParcours, int idStationDep,
			int idStationArr, Minutes duree, Minutes dureeArret, int version) {
		super();
		this.id = id;
		this.idParcours = idParcours;
		this.idStationDep = idStationDep;
		this.idStationArr = idStationArr;
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

	public int getIdParcours() {
		return idParcours;
	}

	public void setIdParcours(int idParcours) {
		this.idParcours = idParcours;
	}

	public int getIdStationDep() {
		return idStationDep;
	}

	public void setIdStationDep(int idStationDep) {
		this.idStationDep = idStationDep;
	}

	public int getIdStationArr() {
		return idStationArr;
	}

	public void setIdStationArr(int idStationArr) {
		this.idStationArr = idStationArr;
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

	/*
	 * @Override public String toString() { return parcours.getNom() + "#(" +
	 * stationDep.getNom() + "===" + duree.getMinutes() + "===>" +
	 * stationArr.getNom() + " : " + dureeArret.getMinutes() + ")"; }
	 */
}
