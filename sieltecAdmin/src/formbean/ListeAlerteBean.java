package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import screenbean.ScreenAlerte;
import service.IManagementService;
import db.Alerte;
import db.Ligne;
import db.Parcours;

@ManagedBean
public class ListeAlerteBean {

	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	
	
public IManagementService getManagementService() {
		return managementService;
	}



	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}


public List<ScreenAlerte> getScreenAlertes() {
		
		List <ScreenAlerte> screenAlertes=new ArrayList<ScreenAlerte>();
		List<Alerte> alertes=managementService.getAllAlertes();
		for(Alerte a:alertes){
			Parcours parc=managementService.getParcoursById(a.getParcoursId());
			Ligne lig=managementService.getLignesById(parc.getLigneId());
			
			ScreenAlerte sa=new ScreenAlerte(a.getId(), a.getNom(),a.getDescription(), parc, lig);
			screenAlertes.add(sa);
		}
		return screenAlertes;
	
	
	}

}
