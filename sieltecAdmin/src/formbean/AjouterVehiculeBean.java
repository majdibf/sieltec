package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Station;
import db.Vehicule;

import service.IManagementService;

@ManagedBean
public class AjouterVehiculeBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	//input
	private String immatriculation;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	
	public String ajouter(){
		
		Vehicule v=new Vehicule(null, immatriculation, 0);
		managementService.insertVehicule(v);
		
		return "liste_vehicules";
	}


}
