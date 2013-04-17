package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Conducteur;
import db.Station;

import service.IManagementService;

@ManagedBean
public class ModifierStationBean {

	private Long id;
	private String nom;
	private String longitude;
	private String latitude;
	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	@ManagedProperty(value = "#{detailStationBean}")
	private DetailStationBean detailStationBean;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public DetailStationBean getDetailStationBean() {
		return detailStationBean;
	}

	public void setDetailStationBean(DetailStationBean detailStationBean) {
		this.detailStationBean = detailStationBean;
	}
	
	
	
	public String initialiser(){
		Station s=detailStationBean.getStation();
		
		this.id=s.getId();
		this.nom=s.getNom();
		this.latitude=s.getLatitude();
		this.longitude=s.getLongitude();
		
		return "modifier_station";
	}
	
	public String update(){
		Station s =new Station(detailStationBean.getStation().getId() , nom , longitude , latitude , detailStationBean.getStation().getVersion());
		managementService.updateStation(s);
		return "liste_stations";
	}
	
	public String retour(){
		return "detail_station";
	}

	
	
}
