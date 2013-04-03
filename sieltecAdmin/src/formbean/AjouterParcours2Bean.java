package formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import db.Station;

import service.IManagementService;

@ManagedBean
public class AjouterParcours2Bean {
	
	@ManagedProperty(value="#{ajouterParcours1Bean}")
	private AjouterParcours1Bean ajouterParcours1Bean;
	

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	
	//output
	
	public AjouterParcours1Bean getAjouterParcours1Bean() {
		return ajouterParcours1Bean;
	}

	public void setAjouterParcours1Bean(AjouterParcours1Bean ajouterParcours1Bean) {
		this.ajouterParcours1Bean = ajouterParcours1Bean;
	}

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String ajouter(){	
		
		System.out.println(ajouterParcours1Bean.getLigne());
		System.out.println(ajouterParcours1Bean.getNomParcours());
		Long[] selectedStations = ajouterParcours1Bean.getSelectedStations();

		return "ajouter_parcours2";
	}
	
}