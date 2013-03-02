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

	// input
	private String startStation = "Station1";

	// output
	private List<Ligne> resLignes;

	public ProchainPassage1Bean() {

		super();
		System.out.println("ProchainPassage1Bean instanciated");
	}

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public List<Ligne> getResLignes() {
		return resLignes;
	}

	public void setResLignes(List<Ligne> resLignes) {
		this.resLignes = resLignes;
	}

	// searchLigne :
	public String searchLignes() {
//		this.resLignes = managementService.findLignes(startStation);
		return "resLignes";
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
}
