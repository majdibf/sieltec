package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.openfaces.util.Faces;

import db.Ligne;
import db.Station;

import service.IManagementService;

@ManagedBean
public class ProchainPassage1Bean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String namePage = "Prochain passae à l'arrêt";

	// input
	private String startStation = "Station1";

	// output
	private List<Ligne> lignes;
	private long idStartStation;
	
	
	public long getIdStartStation() {
		Station s =managementService.getStationByName(startStation);
		idStartStation=s.getId();
		return idStartStation;
	}

	public void setIdStartStation(long idStartStation) {
		this.idStartStation = idStartStation;
	}

	public ProchainPassage1Bean() {
		super();
		System.out.println("ProchainPassageBean instanciated");
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public List<Ligne> getLignes() {
		return lignes;
	}

	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
	}

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getNamePage() {
		return namePage;
	}

	public void setNamePage(String namePage) {
		this.namePage = namePage;
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

	public String searchLignes() {
		Station stationDep = managementService.getStationByName(startStation);
		lignes = managementService.getLignesByIdStation(stationDep.getId());
		return "prochain_passage1";

	}

}
