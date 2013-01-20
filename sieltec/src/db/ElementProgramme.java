package db;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ElementProgramme {

	private Long stationDepId;
	private Long stationArrId;
	private DateTime dateHeureDepart;
	private DateTime dateHeureArrivee;
	private Long parcoursId;



	public ElementProgramme(Long stationDepId, Long stationArrId,
			DateTime dateHeureDepart, DateTime dateHeureArrivee,
			Long parcoursId) {
		super();
		this.stationDepId = stationDepId;
		this.stationArrId = stationArrId;
		this.dateHeureDepart = dateHeureDepart;
		this.dateHeureArrivee = dateHeureArrivee;
		this.parcoursId = parcoursId;
	}



	public Long getStationDepId() {
		return stationDepId;
	}



	public void setStationDepId(Long stationDepId) {
		this.stationDepId = stationDepId;
	}



	public Long getStationArrId() {
		return stationArrId;
	}



	public void setStationArrId(Long stationArrId) {
		this.stationArrId = stationArrId;
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



	public Long getParcoursId() {
		return parcoursId;
	}



	public void setParcoursId(Long parcoursId) {
		this.parcoursId = parcoursId;
	}



	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm");
		return parcoursId + "#(" + stationDepId + " : " + fmt.print(dateHeureDepart) + "=====>" + stationArrId + " : " + fmt.print(dateHeureArrivee) + ")";
	}
}
