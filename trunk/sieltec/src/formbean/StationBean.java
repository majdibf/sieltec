package formbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.Station;

import service.IManagementService;

@ManagedBean
public class StationBean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private Logger logger = LogManager.getLogger(this.getClass().getName());

	private String namePage = "Visualiser les stations";
	private List<Station> stations;

	public StationBean() {
		super();
		logger.debug("StationBean instanciated");
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

	public List<Station> getStations() {
		stations=managementService.getAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

}
