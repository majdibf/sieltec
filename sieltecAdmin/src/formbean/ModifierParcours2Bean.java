package formbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.joda.time.Minutes;
import org.openfaces.component.output.GraphicText;

import service.IManagementService;
import db.ElementParcours;
import db.Ligne;
import db.Parcours;
import db.Station;

@ManagedBean
public class ModifierParcours2Bean {

	@ManagedProperty(value="#{modifierParcours1Bean}")
	private ModifierParcours1Bean modifierParcours1Bean;
	

	@ManagedProperty(value="#{detailParcoursBean}")
	private DetailParcoursBean detailParcoursBean;
	
	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	private HtmlPanelGrid grid=new HtmlPanelGrid();
	
	//output
	
	
	
	public HtmlPanelGrid getGrid() {
		List<Station> stations = getStations();
		for(int i=0; i<stations.size();i++){
			
			if(i==0){
				UIOutput output1=new UIOutput();
				output1.setId("output1"+i);
				output1.setValue(stations.get(i).getNom());
				grid.getChildren().add(output1);
				
				UIOutput output2=new UIOutput();
				output1.setId("output2"+i);
				output2.setValue("");
				grid.getChildren().add(output2);
				
				GraphicText gt=new GraphicText();
				gt.setId("gt"+i);
				gt.setValue("<---------------");
				gt.setDirection(90);
				grid.getChildren().add(gt);
				
				UIInput input= new UIInput();
				input.setId("input"+i);
				//output.setMinValue(0);
				//output.setMaxValue(59);
				input.setValue(0);
				grid.getChildren().add(input);
				
			}
			
			if(i>0 && i<stations.size()-1){
				UIOutput output1=new UIOutput();
				output1.setId("output1"+i);
				output1.setValue(stations.get(i).getNom());
				grid.getChildren().add(output1);
				
			
				UIInput input1= new UIInput();
				input1.setId("input1"+i);
				//input1.setMinValue(0);
				//input1.setMaxValue(59);
				input1.setValue(0);
				grid.getChildren().add(input1);
			
				GraphicText gt=new GraphicText();
				gt.setId("gt"+i);
				gt.setValue("<---------------");
				gt.setDirection(90);
				grid.getChildren().add(gt);
				
				UIInput input2= new UIInput();
				input2.setId("input2"+i);
				//input2.setMinValue(0);
				//input2.setMaxValue(59);
				input2.setValue(0);
				grid.getChildren().add(input2);
			}
			
			if(i==stations.size()-1){
				UIOutput output1=new UIOutput();
				output1.setId("output1"+i);
				output1.setValue(stations.get(i).getNom());
				grid.getChildren().add(output1);
			
				UIOutput output2=new UIOutput();
				output2.setId("output2"+i);
				output2.setValue("");
				grid.getChildren().add(output2);	
			}
		}
		
		return grid;
	}

	public ModifierParcours1Bean getModifierParcours1Bean() {
		return modifierParcours1Bean;
	}

	public void setModifierParcours1Bean(ModifierParcours1Bean modifierParcours1Bean) {
		this.modifierParcours1Bean = modifierParcours1Bean;
	}
	

	public DetailParcoursBean getDetailParcoursBean() {
		return detailParcoursBean;
	}

	public void setDetailParcoursBean(DetailParcoursBean detailParcoursBean) {
		this.detailParcoursBean = detailParcoursBean;
	}

	public void setGrid(HtmlPanelGrid grid) {
		this.grid = grid;
	}


	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}	
	
	public List<Station> getStations() {
		List<Station> resultStations = new ArrayList<Station>();
		List<Long> idStations = new ArrayList<Long>();
		// covertir Long[] en List<Long>
		for (Long id : modifierParcours1Bean.getSelectedStations()) {
			idStations.add(id);
		}

		HashMap<Long, Station> stations = managementService
				.getStationsByIdList(idStations);

		for (Long st : idStations) {
			resultStations.add(stations.get(st));
		}

		return resultStations;
	}
	
	
	public String update(){	
		List<ElementParcours> elementsParcours=new ArrayList<ElementParcours>();
		Long selectedStations []=modifierParcours1Bean.getSelectedStations();
		Ligne l=managementService.getLigneByName(modifierParcours1Bean.getLigne());
		
		Parcours p=new Parcours(detailParcoursBean.getParc().getId(), modifierParcours1Bean.getNomParcours(), l.getId(), detailParcoursBean.getParc().getVersion());
		
		for(int i=0;i<selectedStations.length-1;i++){
			Long idStationDep=selectedStations[i];
			Long idStationArr=selectedStations[i+1];
	
			if(i==selectedStations.length-2){
				UIInput inputDuree =(UIInput)grid.getChildren().get(4*i+3);
				int duree= Integer.parseInt((String) inputDuree.getValue());
				int dureeArret=0;
				
				Minutes dureeMinute= Minutes.minutes(duree);
				Minutes dureeArretMinute=Minutes.minutes(dureeArret);
				
				ElementParcours ep =new ElementParcours(null, null, idStationDep, idStationArr,dureeMinute , dureeArretMinute, 0);
				elementsParcours.add(ep);
			}else{
			
			UIInput inputDuree =(UIInput)grid.getChildren().get(4*i+3);
			UIInput inputDureeArret =(UIInput)grid.getChildren().get(4*i+5);
			
			int duree= Integer.parseInt((String) inputDuree.getValue());
			int dureeArret=Integer.parseInt((String) inputDureeArret.getValue());
			
			Minutes dureeMinute= Minutes.minutes(duree);
			Minutes dureeArretMinute=Minutes.minutes(dureeArret);
			
			ElementParcours ep =new ElementParcours(null, null, idStationDep, idStationArr,dureeMinute , dureeArretMinute, 0);
			elementsParcours.add(ep);
		}}
		
		
		managementService.updateParcours(p,elementsParcours);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).removeAttribute("detailParcoursBean");
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).removeAttribute("modifierParcours1Bean");
		
		
		return "liste_parcours";
	}
	
	public String retour(){	
		return"modifier_parcours1";
	}
	
}
