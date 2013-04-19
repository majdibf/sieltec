package formbean;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import service.IManagementService;
import db.Conducteur;
import db.Station;

@ManagedBean
public class ListeConducteurBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	@ManagedProperty(value = "#{errorBean}")
	private ErrorBean errorBean;
	
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

	public ErrorBean getErrorBean() {
		return errorBean;
	}

	public void setErrorBean(ErrorBean errorBean) {
		this.errorBean = errorBean;
	}
	
	public String remove(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idConducteur =  Long.parseLong((params.get("idConducteur"))); 
		Conducteur c = managementService.getConducteurById(idConducteur);
		
		boolean result=managementService.removeConducteur(c);
		System.out.println(result);
		//
		//
		return "liste_conducteurs";
	}
	
	
	
}
