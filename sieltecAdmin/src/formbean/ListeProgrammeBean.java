package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;

import screenbean.ScreenProgramme;
import service.IManagementService;
import db.Conducteur;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.Vehicule;

@ManagedBean
public class ListeProgrammeBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//output
	private List<ScreenProgramme> screenProgrammes;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	
	public void setScreenProgrammes(List<ScreenProgramme> screenProgrammes) {
		this.screenProgrammes = screenProgrammes;
	}

	public List<ScreenProgramme> getScreenProgrammes() {
		
		List <ScreenProgramme> screenProrammes=new ArrayList<ScreenProgramme>();
		List<Programme> programmes=managementService.getAllProgrammes();
		for(Programme p:programmes){
			Parcours parc=managementService.getParcoursById(p.getParcoursId());
			Vehicule vehic=managementService.getVehiculeById(p.getVehiculeId());
			Conducteur conduc=managementService.getConducteurById(p.getConducteurId());
			
			ScreenProgramme sp=new ScreenProgramme(p.getId(), new Date(p.getDateHeureDebut().getMillis()), parc, vehic, conduc);
			
			screenProrammes.add(sp);
		}
		return screenProrammes;
		
	}

	
	public String remove(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idProgramme =  Long.parseLong((params.get("idProgramme"))); 
		Programme p = managementService.getProgrammeById(idProgramme);
		
		boolean result=managementService.removeProgramme(p);
		System.out.println(result);
		//
		//
		return "liste_programmes";
	}
	
	
	
}
