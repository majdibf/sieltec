package formbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlPanelGrid;

import org.openfaces.component.input.Spinner;

import service.IManagementService;

@ManagedBean (name="ajouterParcours3Bean")
@RequestScoped
public class AjouterParcours3Bean implements Serializable{

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	private HtmlPanelGrid grid =new HtmlPanelGrid();

	//output
	
	public AjouterParcours3Bean() {	
		
	}
	
	public IManagementService getManagementService() {
		return managementService;
	}
	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	public HtmlPanelGrid getGrid() {
		for(int i=1;i<5;i++){
			Spinner input= new Spinner();
			input.setId("id"+i);
			grid.getChildren().add(input);
		}
		return grid;
	}
	public void setGrid(HtmlPanelGrid grid) {
		//this.grid = grid;
		Spinner input=new Spinner();
		input= (Spinner) grid.getChildren().get(0);
		System.out.println(input.getValue());
		
		input= (Spinner) grid.getChildren().get(1);
		System.out.println(input.getValue());
		
		input= (Spinner) grid.getChildren().get(2);
		System.out.println(input.getValue());
				
		input= (Spinner) grid.getChildren().get(3);	
		System.out.println(input.getValue());

		
		
	}
	
	
	
	public String valider(){
		Spinner input=new Spinner();
		input= (Spinner) grid.getChildren().get(0);
		System.out.println(input.getValue());
		
		input= (Spinner) grid.getChildren().get(1);
		System.out.println(input.getValue());
		
		input= (Spinner) grid.getChildren().get(2);
		System.out.println(input.getValue());
				
		input= (Spinner) grid.getChildren().get(3);	
		System.out.println(input.getValue());

		
		return null; // on recharge la m�me page
		
	}
		
}