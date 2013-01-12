package dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.joda.time.DateTime;

import db.ElementProgramme;
import com.sieltec.demo.PathFinder;

import dao.IStationDao;

@ManagedBean(name="stationDao", eager=true)
@ApplicationScoped
public class StationDao implements IStationDao, Serializable {
	

	int compteur = 0;
	
	public StationDao() {
		super();
		System.out.println("ManagementService instanciated");
	}

	@Override
	public String findPath(String startStation, String endStation) {
		String result = "";
		compteur ++;
		String[] startEndStation = {"1","24"};
		PathFinder finder = new PathFinder();
		List<ElementProgramme> bestPath = finder.findBestPath(finder.getStations().get(Integer.parseInt(startStation)), finder.getStations().get(Integer.parseInt(endStation)), new DateTime(2013, 01, 05, 07, 52), finder.getElementsProgramme());
		
		for(ElementProgramme elemResultat : bestPath){
			result = result + elemResultat + "||\n";
		}
		
		return result;
	}


}
