package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.Conducteur;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.Vehicule;

import screenbean.ScreenParcours;
import service.IManagementService;

@ManagedBean
public class ListeParcoursBean {

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//output
	private List<ScreenParcours> screenParcours;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public List<ScreenParcours> getScreenParcours() {
		
		List <ScreenParcours> screenParcours=new ArrayList<ScreenParcours>();
		List<Parcours> parcours=managementService.getAllParcours();
		for(Parcours p: parcours){
			Ligne l=managementService.getLignesById(p.getLigneId());
			
			ScreenParcours sp=new ScreenParcours(p.getId(),p.getNom(),l);
			
			screenParcours.add(sp);
		}
		
		
		return screenParcours;
	}

	public void setScreenParcours(List<ScreenParcours> screenParcours) {
		this.screenParcours = screenParcours;
	}

	
	public String remove(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idParcours =  Long.parseLong((params.get("idParcours"))); 
		Parcours p = managementService.getParcoursById(idParcours);
		
		boolean result=managementService.removeParcours(p);
		logger.trace(result);
		//
		//
		return "liste_parcours";
	}
	
	
}
