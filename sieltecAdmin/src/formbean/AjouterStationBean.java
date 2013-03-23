package formbean;

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
	private String longitude;
	private String latitude;
	
	
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
