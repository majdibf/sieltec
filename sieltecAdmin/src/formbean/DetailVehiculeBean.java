package formbean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import service.IManagementService;
import db.Conducteur;
import db.Vehicule;

@ManagedBean(name = "detailVehiculeBean")
@SessionScoped
public class DetailVehiculeBean {

	// input
	private Vehicule vehicule;
	// output

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	public String afficherDetailVehicule(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idVehicule =  Long.parseLong((params.get("idVehicule"))); 
		this.vehicule = managementService.getVehiculeById(idVehicule);
		
		return"detail_vehicule";
	}
	
	public String retour(){
		return "liste_vehicules";
	}
}
