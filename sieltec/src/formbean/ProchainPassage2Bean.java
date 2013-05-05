package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


import org.joda.time.DateTime;

import db.ElementProgramme;
import db.Parcours;

import screenbean.Passage;
import service.IManagementService;

@ManagedBean
public class ProchainPassage2Bean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String namePage = "Prochain passae à l'arrêt";

	//input
	private Date date = new Date(new DateTime().getMillis());
	private String destination;
	

	//output
	private List<SelectItem> parcoursItems;
	private String idStation;
	private String idLigne;
	private List<Passage> passages;
	
	public ProchainPassage2Bean(){
		super();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String idStation =  (params.get("idStation")); 
		String idLigne =  (params.get("idLigne")); 
		System.out.println("idStation="+idStation);
		this.idStation=idStation;
		this.idLigne=idLigne;
		
	}
			
	public String getIdLigne() {
		return idLigne;
	}

	public void setIdLigne(String idLigne) {
		this.idLigne = idLigne;
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
		
		List<Parcours> parcours= managementService.getParcoursByIdLigne(Long.parseLong(this.idLigne));
		parcoursItems=new ArrayList<SelectItem>();
		for(Parcours p:parcours){
			parcoursItems.add( new SelectItem(p.getNom()));
		}        
       
       return parcoursItems;
	}

	public void setParcoursItems(List<SelectItem> parcoursItems) {
		this.parcoursItems = parcoursItems;
	}
	
	public List<Passage> getPassages() {
		return passages;
	}

	public void setPassages(List<Passage> passages) {
		this.passages = passages;
	}

	public int getHeure() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		return cal.get(GregorianCalendar.HOUR_OF_DAY);
	}

	public void setHeure(int heure) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		cal.set(GregorianCalendar.HOUR_OF_DAY, heure);
		this.date = cal.getTime();
	}

	public int getMinute() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		return cal.get(GregorianCalendar.MINUTE);
	}

	public void setMinute(int minute) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		cal.set(GregorianCalendar.MINUTE, minute);
		this.date = cal.getTime();
		}
	
	
	 public String searchPassage(){
		  passages=new ArrayList<Passage>();
		  Parcours parc=managementService.getParcoursByName(destination);
		  List<ElementProgramme> elementProgrammes=managementService.FindProchainPassage(Long.parseLong(idStation), parc.getId(), new DateTime(date.getTime()));
		  int i=0;
		  for(ElementProgramme ep : elementProgrammes){
		   i++;
		   Date d = null;
		   if(ep.getStationDepId() == Long.parseLong(idStation)){
		    d = new  Date(ep.getDateHeureDepart().getMillis());
		   } else {
		    d = new  Date(ep.getDateHeureArrivee().getMillis());
		   }
		   
		   passages.add(new Passage(i,d)); 
		  }
		  
		  return "prochain_passage2";
		 }
}