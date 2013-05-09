package screenbean;

import java.util.Date;

import db.Parcours;
import db.Station;

public class ScreenEvenement {

	private Parcours parcours;
	private String typEvt;
	private Station station;
	private Date dateHeureDebut;

	public ScreenEvenement(Parcours parcours, String typEvt, Station station,
			Date dateHeureDebut) {
		super();
		this.parcours = parcours;
		this.typEvt = typEvt;
		this.station = station;
		this.dateHeureDebut = dateHeureDebut;
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public String getTypEvt() {
		return typEvt;
	}

	public void setTypEvt(String typEvt) {
		this.typEvt = typEvt;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Date getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(Date dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

}