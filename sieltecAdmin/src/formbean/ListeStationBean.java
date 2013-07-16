package formbean;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.Programme;
import db.Station;

import service.IManagementService;

@ManagedBean
public class ListeStationBean {

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//output
	private List<Station> stations;
	

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	
	
	public List<Station> getStations() {
		stations = managementService.getAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	public String remove(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		Long idStation =  Long.parseLong((params.get("idStation"))); 
		Station s = managementService.getStationsById(idStation);
		
		boolean result=managementService.removeStation(s);
		logger.trace(result);
		//
		//
		return "liste_stations";
	}
	


}
