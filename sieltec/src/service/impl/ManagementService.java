package service.impl;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.DateTime;

import com.sieltec.demo.PathFinder;

import service.IManagementService;
import dao.IStationDao;
import db.ElementProgramme;

@ManagedBean(name="managementService", eager=true)
@ApplicationScoped
public class ManagementService implements IManagementService, Serializable {
	
	@ManagedProperty(value="#{stationDao}")
	private IStationDao stationDao;

	public ManagementService() {
		super();
		System.out.println("ManagementService instanciated");
	}
	
	public IStationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(IStationDao stationDao) {
		this.stationDao = stationDao;
	}

	@Override
	public String findPath(String startStation, String endStation) {
		String result = "";
		String[] startEndStation = {"1","24"};
		PathFinder finder = new PathFinder();
		List<ElementProgramme> bestPath = finder.findBestPath(finder.getStations().get(Integer.parseInt(startStation)), finder.getStations().get(Integer.parseInt(endStation)), new DateTime(2013, 01, 05, 07, 52), finder.getElementsProgramme());
		
		for(ElementProgramme elemResultat : bestPath){
			result = result + elemResultat + "||\n";
		}
		
		return result;
	}


}
