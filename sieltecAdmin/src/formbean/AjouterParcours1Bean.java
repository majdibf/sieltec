package formbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.openfaces.util.Faces;

import db.ElementParcours;
import db.Ligne;
import db.Parcours;
import db.Station;

import service.IManagementService;

@ManagedBean (name="ajouterParcours1Bean")
@SessionScoped
public class AjouterParcours1Bean implements Serializable{

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	private String ligne;
	private String nomParcours;
	private Long[] selectedStations = new Long[]{2L,5L};
	
	//output
	private List<SelectItem> stationsItems;
	private List<ElementParcours> elementsParcours;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	
	

	public List<ElementParcours> getElementsParcours() {
		return elementsParcours;
	}

	public void setElementsParcours(List<ElementParcours> elementsParcours) {
		this.elementsParcours = elementsParcours;
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
	
	// auto completion ligne
		public List<String> getSuggestedLignes() {
			List<String> suggestedLignes = new ArrayList<String>();
			List<Ligne> lignes = new ArrayList<Ligne>();
			lignes = managementService.getAllLignes();

			String typedValue = Faces.var("searchString", String.class);
			if (typedValue != null) {

				for (Ligne l : lignes) {
					String ligneForComparison = l.getNom().toLowerCase();
					String typedValueForComparison = typedValue.toLowerCase();
					if (ligneForComparison.startsWith(typedValueForComparison))
						suggestedLignes.add(l.getNom());
				}
			} else {
				for (int i = 0; i < lignes.size(); i++) {

					Ligne l = lignes.get(i);
					suggestedLignes.add(l.getNom());

				}
			}
			return suggestedLignes;
		}
	
		
	public String ajouter(){	
		/*
		//inserer une dans la table parcours 
		Ligne l=managementService.getLigneByName(ligne);
		Parcours parc=new Parcours(null,nomParcours,l.getId(), 0);
		Long idParc=managementService.insertParcours(parc);
		
		
		//covertir Long[] en List<Long>
		List<Long> idStations=new ArrayList<Long>();
		for(Long id:selectedStations){
			idStations.add(id);
		}
		
		HashMap<Long, Station> stations=managementService.getStationsByIdList(idStations);
		elementsParcours=new ArrayList<ElementParcours>();
	
		Long stDep=null;
		for(Long stArr:idStations){
			Station stationDep;
			Station stationArr;
			if (stDep!=null){
				stationDep = stations.get(stDep);
				stationArr = stations.get(stArr);
				ElementParcours ep= new ElementParcours(null, idParc,stationDep.getId() ,stationArr.getId() , null, null, 0);
				elementsParcours.add(ep);
			}
		stDep=stArr;		
		}
		*/
		return "ajouter_parcours2";
	}
	
}