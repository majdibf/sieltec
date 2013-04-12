package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Conducteur;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.Vehicule;

import screenbean.ScreenParcours;
import service.IManagementService;

@ManagedBean
public class ListeParcoursBean {

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

	
}