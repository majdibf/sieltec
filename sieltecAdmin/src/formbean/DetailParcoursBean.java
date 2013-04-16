package formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import db.ElementParcours;
import db.Ligne;
import db.Parcours;
import db.Station;

import screenbean.ScreenElementParcours;
import screenbean.ScreenParcours;
import service.IManagementService;


@ManagedBean(name="detailParcoursBean")
@SessionScoped
public class DetailParcoursBean {

	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	private List<ElementParcours> elementsParcours;
	private Parcours parc;
	//output

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	
	public Parcours getParc() {
		return parc;
	}

	public void setParc(Parcours parc) {
		this.parc = parc;
	}

	public List<ElementParcours> getElementsParcours() {
		return elementsParcours;
	}

	public void setElementsParcours(List<ElementParcours> elementsParcours) {
		this.elementsParcours = elementsParcours;
	}
	
	public ScreenParcours getScreenParcours() {
		ScreenParcours screenParcours;
		Ligne l=managementService.getLignesById(parc.getLigneId());
		screenParcours=new ScreenParcours(parc.getId(), parc.getNom(), l);
		return screenParcours;
	}

	
	
	public List<ScreenElementParcours> getScreenElementsParcours(){
		List<ScreenElementParcours> screenElementsParcours=new ArrayList<ScreenElementParcours>();
		for (ElementParcours ep: elementsParcours){
			Parcours p=managementService.getParcoursById(ep.getParcoursId());
			Station stDep=managementService.getStationsById(ep.getStationDepId());
			Station stArr=managementService.getStationsById(ep.getStationArrId());
			ScreenElementParcours sep=new ScreenElementParcours(ep.getId(), p, stDep, stArr, ep.getDuree(), ep.getDureeArret(), ep.getVersion());
			
			screenElementsParcours.add(sep);
		}
		return screenElementsParcours;
	}
	

	public String afficherDetailParcours(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idParcours =  Long.parseLong((params.get("idParcours"))); 
		this.parc = managementService.getParcoursById(idParcours);
		this.elementsParcours=managementService.getElementParcoursByIdParcours(idParcours);
		return "detail_parcours";
	}
	
	
	public String retour(){
		return"liste_parcours";
		
	}
	
}
