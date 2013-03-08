package formbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import org.joda.time.DateTime;
import org.openfaces.util.Faces;

import db.ElementProgramme;
import db.Parcours;
import db.Programme;
import db.Station;

import screenbean.ElementItineraire;
import service.IManagementService;

@ManagedBean
public class ItineraireBean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String namePage = "Rechercher itinéraire";

	// input
	private String id;
	private String startStation = "Station1";
	private String endStation = "Station24";

	private Date date = new Date(new DateTime().getMillis());

	// private int heur = date.getHours();
	private int minute = date.getMinutes();

	// output
	private List<ElementItineraire> itineraire;

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

	public List<ElementItineraire> getItineraire() {
		return itineraire;
	}

	public void setItineraire(List<ElementItineraire> itineraire) {
		this.itineraire = itineraire;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNamePage() {
		return namePage;
	}

	public void setNamePage(String namePage) {
		this.namePage = namePage;
	}

	public int getHeur() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		return cal.get(GregorianCalendar.HOUR_OF_DAY);
	}

	public void setHeur(int heur) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		cal.set(GregorianCalendar.HOUR_OF_DAY, heur);
		this.date = cal.getTime();
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	// auto completion
	public List<String> getSuggestedStations() {
		List<String> suggestedStations = new ArrayList<String>();
		List<Station> stations = new ArrayList<Station>();
		stations = managementService.getAllStations();

		String typedValue = Faces.var("searchString", String.class);
		if (typedValue != null) {

			for (Station s : stations) {
				String stationForComparison = s.getNom().toLowerCase();
				String typedValueForComparison = typedValue.toLowerCase();
				if (stationForComparison.startsWith(typedValueForComparison))
					suggestedStations.add(s.getNom());
			}
		} else {
			for (int i = 0; i < stations.size(); i++) {

				Station s = stations.get(i);
				suggestedStations.add(s.getNom());

			}
		}
		return suggestedStations;
	}

	// Recherche itineraire

	public String search() {

		DateTime d = new DateTime(date.getTime());
		System.out.println(d);

		List<ElementProgramme> itineraireEP = managementService.findPath(
				startStation, endStation, d);
		List<ElementItineraire> itineraireEI = new ArrayList<ElementItineraire>();
		List<Long> idStations = new ArrayList<Long>();
		List<Long> idParcours = new ArrayList<Long>();

		HashMap<Long, Station> stations = new HashMap<Long, Station>();
		HashMap<Long, Parcours> parcours = new HashMap<Long, Parcours>();

		ElementItineraire ei = null;

		for (ElementProgramme ep : itineraireEP) {
			if (!idStations.contains(ep.getStationDepId())){
			idStations.add(ep.getStationDepId());
			}
		
			if (!idStations.contains(ep.getStationArrId())){
				idStations.add(ep.getStationArrId());
				}
				
			idParcours.add(ep.getParcoursId());
		}

		stations = managementService.getStationsByIdList(idStations);
		parcours = managementService.getParcoursByIdList(idParcours);

		// convertir ep en ei

		for (ElementProgramme ep : itineraireEP) {
			
			if(ei != null && ep.getParcoursId() != ei.getParcours().getId()){
				itineraireEI.add(ei);
				Station stationDep = stations.get(ep.getStationDepId());
				Station stationArr = stations.get(ep.getStationArrId());
				Date dateHeureDepart = new Date(ep.getDateHeureDepart().getMillis());
				Date dateHeureArrivee = new Date(ep.getDateHeureArrivee().getMillis());
				Parcours parc = parcours.get(ep.getParcoursId());
				ei = new ElementItineraire(stationDep, stationArr, dateHeureDepart,	dateHeureArrivee, parc);								
			} else if(ei != null){
					Station stationDep = stations.get(ep.getStationDepId());
					Station stationArr = stations.get(ep.getStationArrId());
					Date dateHeureDepart = new Date(ep.getDateHeureDepart().getMillis());
					Date dateHeureArrivee = new Date(ep.getDateHeureArrivee().getMillis());
					Parcours parc = parcours.get(ep.getParcoursId());
					ei.setDateHeureArrivee(dateHeureArrivee);
					ei.setStationArr(stationArr);
			} else {
					Station stationDep = stations.get(ep.getStationDepId());
					Station stationArr = stations.get(ep.getStationArrId());
					Date dateHeureDepart = new Date(ep.getDateHeureDepart().getMillis());
					Date dateHeureArrivee = new Date(ep.getDateHeureArrivee().getMillis());
					Parcours parc = parcours.get(ep.getParcoursId());
					ei = new ElementItineraire(stationDep, stationArr, dateHeureDepart,	dateHeureArrivee, parc);								
			}
			
		}
		itineraireEI.add(ei);
		
		this.itineraire = itineraireEI;

		return "itineraire";

	}
}
