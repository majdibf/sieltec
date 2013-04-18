package formbean;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import screenbean.ScreenProgramme;
import service.IManagementService;
import db.Conducteur;
import db.Parcours;
import db.Programme;
import db.Vehicule;

@ManagedBean(name="detailProgrammeBean")
@SessionScoped
public class DetailProgrammeBean {

	// input
		private ScreenProgramme screenProgramme;
		private int version;

	
	public ScreenProgramme getScreenProgramme() {
			return screenProgramme;
		}


		public void setScreenProgramme(ScreenProgramme screenProgramme) {
			this.screenProgramme = screenProgramme;
		}

		
		

	public int getVersion() {
			return version;
		}


		public void setVersion(int version) {
			this.version = version;
		}




	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;


	
	public IManagementService getManagementService() {
		return managementService;
	}


	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
		

	public String afficherDetailProgramme(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idProgramme =  Long.parseLong((params.get("idProgramme"))); 
		Programme p = managementService.getProgrammeById(idProgramme);	
			
			this.version=p.getVersion();
			Parcours parc=managementService.getParcoursById(p.getParcoursId());
			Vehicule vehic=managementService.getVehiculeById(p.getVehiculeId());
			Conducteur conduc=managementService.getConducteurById(p.getConducteurId());
			
			this.screenProgramme=new ScreenProgramme(p.getId(), new Date(p.getDateHeureDebut().getMillis()), parc, vehic, conduc);
			
		
		return "detail_programme";
	}
	
	
	public String retour(){
		return"liste_programme";
		
	}
	

}
