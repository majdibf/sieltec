package formbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import db.Station;

import service.IManagementService;

@ManagedBean (name="ajouterParcours1Bean")
@ApplicationScoped
public class AjouterParcours1Bean implements Serializable{

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	private String ligne;
	private String nomParcours;
	private Long[] selectedStations = new Long[]{2L,5L};
	
	//output
	private List<SelectItem> stationsItems;
	
	
	
	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getLigne() {
		return ligne;
	}

	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

	public String getNomParcours() {
		return nomParcours;
	}

	public void setNomParcours(String nomParcours) {
		this.nomParcours = nomParcours;
	}
	

	public Long[] getSelectedStations() {
		return selectedStations;
	}

	public void setSelectedStations(Long[] selectedStations) {
		this.selectedStations = selectedStations;
	}

	public List<SelectItem> getStationsItems() {
		List<Station> stations= managementService.getAllStations();
		stationsItems=new ArrayList<SelectItem>();
		for(Station s:stations){
			stationsItems.add( new SelectItem(s.getId(), s.getNom(), "Description_" + s.getNom()));
			
		}        
       
       return stationsItems;
	}

	public void setStationsItems(List<SelectItem> stationsItems) {
		this.stationsItems = stationsItems;
	}

	public String ajouter(){		
		return "ajouter_parcours2";
	}
	
}