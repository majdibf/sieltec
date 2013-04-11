package formbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jfree.chart.needle.PinNeedle;
import org.openfaces.component.input.Spinner;
import org.openfaces.component.output.GraphicText;
import org.primefaces.component.graphicimage.GraphicImageRenderer;

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
				gt.setValue("--------------->");
				//gt.setDirection(270);
				grid.getChildren().add(gt);
				
				Spinner spinner= new Spinner();
				spinner.setId("spinner"+i);
				spinner.setMinValue(0);
				spinner.setMaxValue(59);
				spinner.setValue(0);
				grid.getChildren().add(spinner);
				
			}
			
			if(i>0 && i<stations.size()-1){
				UIOutput output1=new UIOutput();
				output1.setId("output1"+i);
				output1.setValue(stations.get(i).getNom());
				grid.getChildren().add(output1);
				
			
				Spinner spinner1= new Spinner();
				spinner1.setId("spinner1"+i);
				spinner1.setMinValue(0);
				spinner1.setMaxValue(59);
				spinner1.setValue(0);
				grid.getChildren().add(spinner1);
			
				
				
				GraphicText gt=new GraphicText();
				gt.setId("gt"+i);
				gt.setValue("--------------->");
				//gt.setDirection(270);
				grid.getChildren().add(gt);
				
				Spinner spinner2= new Spinner();
				spinner2.setId("spinner2"+i);
				spinner2.setMinValue(0);
				spinner2.setMaxValue(59);
				spinner2.setValue(0);
				grid.getChildren().add(spinner2);
				
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