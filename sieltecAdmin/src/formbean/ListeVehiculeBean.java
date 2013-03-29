package formbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Station;
import db.Vehicule;

import service.IManagementService;

@ManagedBean
public class ListeVehiculeBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//output
	private List<Vehicule> vehicules;
	

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	
	
	public List<Vehicule> getVehicules() {
		vehicules = managementService.getAllVehicules();
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}


	
}
