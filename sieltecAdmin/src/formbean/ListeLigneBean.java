package formbean;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.IManagementService;
import db.Conducteur;
import db.Ligne;
import db.Station;

@ManagedBean
public class ListeLigneBean {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
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

	public String remove(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idLigne =  Long.parseLong((params.get("idLigne"))); 
		Ligne l = managementService.getLignesById(idLigne);
		
		boolean result=managementService.removeLigne(l);
		logger.trace(result);
		//
		//
		return "liste_lignes";
	}
	
	
	
}
