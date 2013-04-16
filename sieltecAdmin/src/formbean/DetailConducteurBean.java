package formbean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import db.Conducteur;

import service.IManagementService;

@ManagedBean(name="detailConducteurBean")
@SessionScoped
public class DetailConducteurBean {

	//input
	private Conducteur conducteur;
	//output
	
	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	
	public Conducteur getConducteur() {
		return conducteur;
	}

	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	public String afficherDetailConducteur(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idConducteur =  Long.parseLong((params.get("idConducteur"))); 
		this.conducteur = managementService.getConducteurById(idConducteur);
		
		return"detail_conducteur";
	}
	
	public String retour(){
		return "liste_conducteurs";
	}

	

}
