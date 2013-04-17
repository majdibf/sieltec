package formbean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import service.IManagementService;

import db.Station;

@ManagedBean(name = "detailStationBean")
@SessionScoped
public class DetailStationBean {
	// input
	private Station station;
	// output

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	public IManagementService getManagementService() {
		return managementService;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String afficherDetailStation() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		Long idStation = Long.parseLong((params.get("idStation")));
		this.station = managementService.getStationsById(idStation);

		return "detail_station";
	}

	public String retour() {
		return "liste_stations";
	}

	
	
}
