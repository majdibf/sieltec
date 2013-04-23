package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Station;

import service.IManagementService;

@ManagedBean
public class AjouterStationBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	//input
	private String nomStation;
	private String longitude="10";
	private String latitude="10";
	private List<Station>stations;

	
	
	
	public List<Station> getStations() {
		Station st;
		stations=new ArrayList<Station>();
		st=new Station(1L, "st1", "24", "6", 0);
		stations.add(st);
		
		st=new Station(2L, "st2", "34", "7", 0);
		stations.add(st);
		
		st=new Station(3L, "st3", "42", "5", 0);
		stations.add(st);
		
		st=new Station(4L, "st4", "20", "6", 0);
		stations.add(st);
		
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}


	
	
	
	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getNomStation() {
		return nomStation;
	}

	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
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

	public String ajouter(){
		
		Station s=new Station(null, nomStation, longitude, latitude, 0);
		managementService.insertStation(s);
		
		return "liste_stations";
	}
	
	
}
