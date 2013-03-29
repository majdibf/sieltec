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
public class AjouterParcours1Bean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	private String ligne;
	private String nomParcours;
	private List<SelectItem> selectedStations;
	
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
	
	public List<SelectItem> getSelectedStations() {
		return selectedStations;
	}

	public void setSelectedStations(List<SelectItem> selectedStations) {
		this.selectedStations = selectedStations;
	}

	public List<SelectItem> getStationsItems() {
		List<Station> stations= managementService.getAllStations();
		stationsItems=new ArrayList<SelectItem>();
		for(Station s:stations){
			stationsItems.add( new SelectItem(s, s.getNom()));
			
		}        
       
       return stationsItems;
	}

	public void setStationsItems(List<SelectItem> stationsItems) {
		this.stationsItems = stationsItems;
	}

	public String ajouter(){
		return "liste_parcours";
	}
	
}