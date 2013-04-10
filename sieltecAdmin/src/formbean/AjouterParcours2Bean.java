package formbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import db.ElementParcours;
import db.Parcours;
import db.Station;

import screenbean.ScreenElementParcours;
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

	
	/*
	public List<ScreenElementParcours> getScreenElementsParcours(){
		List<ScreenElementParcours> screenElementsParcours=new ArrayList<ScreenElementParcours>();
		for (ElementParcours ep: ajouterParcours1Bean.getElementsParcours()){
			Parcours p=managementService.getParcoursById(ep.getParcoursId());
			Station stDep=managementService.getStationsById(ep.getStationDepId());
			Station stArr=managementService.getStationsById(ep.getStationArrId());
			ScreenElementParcours sep=new ScreenElementParcours(ep.getId(), p, stDep, stArr, ep.getDuree(), ep.getDureeArret(), ep.getVersion());
			
			screenElementsParcours.add(sep);
		}
		return screenElementsParcours;
	}
	*/
	
	
	public List<Station> getStations(){
		List<Station> resultStations=new ArrayList<Station>();
		List<Long> idStations=new ArrayList<Long>();
		//covertir Long[] en List<Long>
		for(Long id:ajouterParcours1Bean.getSelectedStations()){
			idStations.add(id);
		}
		
		HashMap<Long, Station> stations=managementService.getStationsByIdList(idStations);
		
		for(Long st:idStations){
			resultStations.add(stations.get(st));	
			}
		
		return resultStations;
	}
	
	
	public String ajouter(){	
		
		System.out.println(ajouterParcours1Bean.getLigne());
		System.out.println(ajouterParcours1Bean.getNomParcours());
		Long[] selectedStations = ajouterParcours1Bean.getSelectedStations();

		return "ajouter_parcours3";
	}
	
}