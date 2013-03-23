package formbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.IManagementService;
import db.Ligne;
import db.Station;

@ManagedBean
public class ListeLigneBean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	// output
	private List<Ligne> lignes;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public List<Ligne> getLignes() {
		lignes=managementService.getAllLignes();
		return lignes;
	}

	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
	}

	
	
}
