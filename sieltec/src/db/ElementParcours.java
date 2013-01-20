package db;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class ElementParcours {
	private Long id;
	private Long parcoursId;
	private Long stationDepId;
	private Long stationArrId;
	private Minutes duree;
	private Minutes dureeArret;
	private int version;


	public ElementParcours(Long id, Long parcoursId, Long stationDepId,
			Long stationArrId, Minutes duree, Minutes dureeArret, int version) {
		super();
		this.id = id;
		this.parcoursId = parcoursId;
		this.stationDepId = stationDepId;
		this.stationArrId = stationArrId;
		this.duree = duree;
		this.dureeArret = dureeArret;
		this.version = version;
	}


	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public Long getParcoursId() {
		return parcoursId;
	}





	public void setParcoursId(Long parcoursId) {
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
		return parcoursId + "#(" + stationDepId + "===" + duree.getMinutes() + "===>" + stationArrId + " : " + dureeArret.getMinutes() + ")";
	}

}
