package com.sieltec.db;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ElementProgramme {

	private Station stationDep;
	private Station stationArr;
	private DateTime dateHeureDepart;
	private DateTime dateHeureArrivee;
	private Parcours parcours;


	public ElementProgramme(Station stationDep, Station stationArr,
			DateTime dateHeureDepart, DateTime dateHeureArrivee,
			Parcours parcours) {
		super();
		this.stationDep = stationDep;
		this.stationArr = stationArr;
		this.dateHeureDepart = dateHeureDepart;
		this.dateHeureArrivee = dateHeureArrivee;
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

	public DateTime getDateHeureDepart() {
		return dateHeureDepart;
	}

	public void setDateHeureDepart(DateTime dateHeureDepart) {
		this.dateHeureDepart = dateHeureDepart;
	}

	public DateTime getDateHeureArrivee() {
		return dateHeureArrivee;
	}

	public void setDateHeureArrivee(DateTime dateHeureArrivee) {
		this.dateHeureArrivee = dateHeureArrivee;
	}
	
	
	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
		return parcours.getNom() + "#(" + stationDep.getNom() + " : " + fmt.print(dateHeureDepart) + "=====>" + stationArr.getNom() + " : " + fmt.print(dateHeureArrivee) + ")";
	}
}
