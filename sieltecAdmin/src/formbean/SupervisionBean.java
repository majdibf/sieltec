package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.joda.time.DateTime;
import screenbean.ScreenEvenement;
import service.IManagementService;
import db.Evenement;
import db.Parcours;
import db.Programme;
import db.Station;

@ManagedBean(name="supervisionBean")
@SessionScoped
public class SupervisionBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	
	
	
	public IManagementService getManagementService() {
		return managementService;
	}




	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}




	public List<ScreenEvenement> getScreenEvenement(){
		List<Evenement> evenements=managementService.getEvenementByDate(DateTime.now());
		List <ScreenEvenement> screenEvenements=new ArrayList<ScreenEvenement>();
			
			for(Evenement evt : evenements){
					Programme prg = managementService.getProgrammeById(evt.getProgrammeId());
					Parcours parc = managementService.getParcoursById(prg.getParcoursId());
					Station station = managementService.getStationsById(evt.getStationId());
					Long typeEvtId = evt.getTypeEvenementId();
					String typeEvt = ""; 
					if(typeEvtId == 2){
						typeEvt = "DEPART";
					} else {
						typeEvt = "ARRIVEE";
					}
					ScreenEvenement se=new ScreenEvenement(prg.getId(),parc,typeEvt ,station, new Date(evt.getDateHeure().getMillis()));
					screenEvenements.add(se);
				}
			
			return  screenEvenements;
		}
	}
	

