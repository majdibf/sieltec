package screenbean;

import java.util.Date;

import org.joda.time.DateTime;

import db.Parcours;
import db.Station;

public class ElementItineraire {

	private Station stationDep;
	private Station stationArr;
	private Date dateHeureDepart;
	private Date dateHeureArrivee;
	private Parcours parcours;

	public ElementItineraire(Station stationDep, Station stationArr,
			Date dateHeureDepart, Date dateHeureArrivee,
			Parcours parcours) {
		
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

	public Date getDateHeureDepart() {
		return dateHeureDepart;
	}

	public void setDateHeureDepart(Date dateHeureDepart) {
		this.dateHeureDepart = dateHeureDepart;
	}

	public Date getDateHeureArrivee() {
		return dateHeureArrivee;
	}

	public void setDateHeureArrivee(Date dateHeureArrivee) {
		this.dateHeureArrivee = dateHeureArrivee;
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

}
