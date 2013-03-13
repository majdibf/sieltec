package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import org.joda.time.DateTime;

import db.Parcours;

import service.IManagementService;

@ManagedBean
public class ProchainPassage2Bean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String namePage = "Prochain passae à l'arrêt";

	//input
	private Date date;
	private String destination;
	

	//output
	private List<SelectItem> parcoursItems;
	private String idStation;

	
	public ProchainPassage2Bean(){
		super();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String idStation =  (params.get("idStation")); 
		System.out.println("idStation="+idStation);
		this.idStation=idStation;
		
	}
	
	
	
	public String getIdStation() {
		return idStation;
	}


	public void setIdStation(String idStation) {
		this.idStation = idStation;
	}




	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getNamePage() {
		return namePage;
	}

	public void setNamePage(String namePage) {
		this.namePage = namePage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	
	public List<SelectItem> getParcoursItems() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		long idLigne =  Long.parseLong(params.get("idLigne")); 
		
		
		List<Parcours> parcours= managementService.getParcoursByIdLigne(idLigne);
		parcoursItems=new ArrayList<SelectItem>();
		for(Parcours p:parcours){
			parcoursItems.add( new SelectItem(p.getNom()));
		}        
       
       return parcoursItems;
	}

	public void setParcoursItems(List<SelectItem> parcoursItems) {
		this.parcoursItems = parcoursItems;
	}
	
	public String searchPassage(){
		
		return "prochain_passage2";
	}
	 
	
}
