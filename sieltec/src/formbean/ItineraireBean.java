package formbean;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import service.IManagementService;

@ManagedBean
public class ItineraireBean {
	@ManagedProperty(value="#{managementService}")
	private IManagementService managementService;

	//input
	private String id;
	private String startStation = "3";
	private String endStation = "24";
	private String[] stationNames = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};

	//output
	private String itineraire;
	
	public ItineraireBean() {
		super();
		System.out.println("ItineraireBean instanciated");
	}

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getId() {
		return (id);
	}

	public void setId(String id) {
		this.id = id.trim();
		if (this.id.isEmpty()) {
			this.id = "(none entered)";
		}
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public String[] getStationNames() {
		return stationNames;
	}

	public void setStationNames(String[] stationNames) {
		this.stationNames = stationNames;
	}

	public String getItineraire() {
		return itineraire;
	}

	public void setItineraire(String itineraire) {
		this.itineraire = itineraire;
	}

	public String search() {
		
		this.itineraire = managementService.findPath(startStation, endStation);
		return "itineraire";
	}
	
	
	
}
