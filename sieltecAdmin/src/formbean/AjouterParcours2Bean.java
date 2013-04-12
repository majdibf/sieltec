package formbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jfree.chart.needle.PinNeedle;
import org.jfree.data.time.Minute;
import org.joda.time.Minutes;
import org.openfaces.component.input.Spinner;
import org.openfaces.component.output.GraphicText;
import org.primefaces.component.graphicimage.GraphicImageRenderer;

import db.ElementParcours;
import db.Ligne;
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
	private HtmlPanelGrid grid=new HtmlPanelGrid();
	
	//output
	
	
	
	public AjouterParcours2Bean() {
	}
	
	public AjouterParcours1Bean getAjouterParcours1Bean() {
		return ajouterParcours1Bean;
	}

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

	public void setGrid(HtmlPanelGrid grid) {
		this.grid = grid;
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
	
	
	public List<Station> getStations() {
		List<Station> resultStations = new ArrayList<Station>();
		List<Long> idStations = new ArrayList<Long>();
		// covertir Long[] en List<Long>
		for (Long id : ajouterParcours1Bean.getSelectedStations()) {
			idStations.add(id);
		}

		HashMap<Long, Station> stations = managementService
				.getStationsByIdList(idStations);

		for (Long st : idStations) {
			resultStations.add(stations.get(st));
		}

		return resultStations;
	}
	
	
	public String ajouter(){	
		List<ElementParcours> elementsParcours=new ArrayList<ElementParcours>();
		Long selectedStations []=ajouterParcours1Bean.getSelectedStations();
		Ligne l=managementService.getLigneByName(ajouterParcours1Bean.getLigne());
		
		Parcours p=new Parcours(null, ajouterParcours1Bean.getNomParcours(), l.getId(), 0);
		
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
		
		
		managementService.insertParcours(p,elementsParcours);
		
		return "liste_parcours";
	}
	
}