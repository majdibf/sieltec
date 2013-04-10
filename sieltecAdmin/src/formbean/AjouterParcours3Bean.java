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
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.openfaces.util.Faces;

import db.ElementParcours;
import db.Ligne;
import db.Parcours;
import db.Station;

import service.IManagementService;

@ManagedBean (name="ajouterParcours3Bean")
@SessionScoped
public class AjouterParcours3Bean implements Serializable{

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	
	//output
	
	public AjouterParcours3Bean() {	
		for(int i=1;i<5;i++){
			UIInput input= new UIInput();
			input.setId("id"+i);
			grid.getChildren().add(input);
		}
	}
	
	private HtmlPanelGrid grid =new HtmlPanelGrid();
	public IManagementService getManagementService() {
		return managementService;
	}
	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	public HtmlPanelGrid getGrid() {
		return grid;
	}
	public void setGrid(HtmlPanelGrid grid) {
		this.grid = grid;
		UIInput input=new UIInput();
		input= (UIInput) grid.getChildren().get(0);
		System.out.println(input.getValue());
		
		input= (UIInput) grid.getChildren().get(1);
		System.out.println(input.getValue());
		
		input= (UIInput) grid.getChildren().get(2);
		System.out.println(input.getValue());
				
		input= (UIInput) grid.getChildren().get(3);	
		System.out.println(input.getValue());

	}
	
	
	
	public String valider(){
		return null; // on recharge la même page
		
	}
		
}