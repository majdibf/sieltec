package formbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.IManagementService;
import db.Conducteur;
import db.Station;

@ManagedBean
public class ListeConducteurBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//output
	private List<Conducteur> conducteurs;
	

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public List<Conducteur> getConducteurs() {
		conducteurs=managementService.getAllConducteurs();
		return conducteurs;
	}

	public void setConducteurs(List<Conducteur> conducteurs) {
		this.conducteurs = conducteurs;
	}


	
}
