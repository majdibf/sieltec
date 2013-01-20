package formbean;

import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import db.ElementProgramme;
import db.Station;

import service.IManagementService;

@ManagedBean
public class ItineraireBean {
	@ManagedProperty(value="#{managementService}")
	private IManagementService managementService;

	//input
	private String id;
	private String startStation = "Station1";
	private String endStation = "Station24";
	private String[] stationNames = {"Station1", "Station2", "Station3", "Station4", "Station5", "Station6", "Station7", "Station8", "Station9", "Station10", "Station11", "Station12", "Station13", "Station14", "Station15", "Station16", "Station17", "Station18", "Station19", "Station20", "Station21", "Station22", "Station23", "Station24"};

	//output
	private List<ElementProgramme> itineraire;
	
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


	public String search() {
		
		this.itineraire = managementService.findPath(startStation, endStation, new DateTime(2013, 01, 05, 07, 52));
		return "itineraire";
	}

	public List<ElementProgramme> getItineraire() {
		return itineraire;
	}

	public void setItineraire(List<ElementProgramme> itineraire) {
		this.itineraire = itineraire;
	}
	
	
	
}
