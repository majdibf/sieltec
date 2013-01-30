package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import db.ElementProgramme;
import db.Programme;
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
	private Date date=new Date(new DateTime().getMillis());
	
	
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



	public String search() {
		DateTime d= new DateTime(date.getTime());
		System.out.println(d);
		this.itineraire = managementService.findPath(startStation, endStation, d);
		return "itineraire";
	}

	public List<ElementProgramme> getItineraire() {
		return itineraire;
	}

	public void setItineraire(List<ElementProgramme> itineraire) {
		this.itineraire = itineraire;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> complete(String ch) {
		List<Station> stations = new ArrayList<>();
		List<String> result = new ArrayList<>();
		stations = managementService.getAllStations();
		for (Station s : stations) {
			if (s.getNom().startsWith(ch)){
				result.add(s.getNom());
			}
		}
		return result;
	}
	
}
