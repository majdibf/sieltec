package formbean;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

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
	
	public String remove(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idVehicule =  Long.parseLong((params.get("idVehicule"))); 
		Vehicule v = managementService.getVehiculeById(idVehicule);
		
		boolean result=managementService.removeVehicule(v);
		System.out.println(result);
		//
		//
		return "liste_vehicules";
	}
	
	
}