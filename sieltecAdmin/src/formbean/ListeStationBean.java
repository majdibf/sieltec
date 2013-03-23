package formbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Station;

import service.IManagementService;

@ManagedBean
public class ListeStationBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//output
	private List<Station> stations;
	

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	
	
	public List<Station> getStations() {
		stations = managementService.getAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}


	



}
