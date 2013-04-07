package screenbean;

import org.joda.time.Minutes;

import db.Parcours;
import db.Station;

public class ScreenElementParcours {
	private Long id;
	private Parcours parcours;
	private Station stationDep;
	private Station stationArr;
	private Minutes duree;
	private Minutes dureeArret;
	private int version;
	public ScreenElementParcours(Long id, Parcours parcours,
			Station stationDep, Station stationArr, Minutes duree,
			Minutes dureeArret, int version) {
		super();
		this.id = id;
		this.parcours = parcours;
		this.stationDep = stationDep;
		this.stationArr = stationArr;
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
	
}
