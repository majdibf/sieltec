package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.openfaces.util.Faces;

import db.ElementParcours;
import db.Ligne;
import db.Parcours;
import db.Station;

import service.IManagementService;

@ManagedBean(name="modifierParcours1Bean")
@SessionScoped
public class ModifierParcours1Bean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	@ManagedProperty(value = "#{detailParcoursBean}")
	private DetailParcoursBean detailParcoursBean;

	//input
	private String ligne;
	private String nomParcours;
	private Long[] selectedStations;
	
	private Long idparcours;
	
	
	//output
	private List<SelectItem> stationsItems;
	private List<ElementParcours> elementsParcours;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	

	public DetailParcoursBean getDetailParcoursBean() {
		return detailParcoursBean;
	}

	public void setDetailParcoursBean(DetailParcoursBean detailParcoursBean) {
		this.detailParcoursBean = detailParcoursBean;
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
		public Long getIdparcours() {
		return idparcours;
	}

	public void setIdparcours(Long idparcours) {
		this.idparcours = idparcours;
	}

	
		public String initialiser(){
			Parcours p=detailParcoursBean.getParc();
			Ligne l=managementService.getLignesById(p.getLigneId());
			List<ElementParcours> elementsParcours=new ArrayList<ElementParcours>();
			elementsParcours=detailParcoursBean.getElementsParcours();
			
			this.idparcours=p.getId();
			
			this.ligne=l.getNom();
			this.nomParcours=p.getNom();
			
			this.selectedStations=new Long[elementsParcours.size()+1];
			
			for(int i=0;i<elementsParcours.size();i++){
				if(i==0){
					this.selectedStations[i]=elementsParcours.get(i).getStationDepId();
					this.selectedStations[i+1]=elementsParcours.get(i).getStationArrId();
				
				}else{
					this.selectedStations[i+1]=elementsParcours.get(i).getStationArrId();
				}
			}
			
			return "modifier_parcours1";
		}
		
		
		public String suivant(){
			return"modifier_parcours2";
		}
		
		public String retour(){
			return"detail_parcours";
		}
		
}
