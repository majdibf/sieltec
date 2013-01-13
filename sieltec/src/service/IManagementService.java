package service;

import java.util.List;

import org.joda.time.DateTime;

import db.ElementProgramme;
import db.Station;


public interface IManagementService {
	
	
	public List<ElementProgramme> findPath(String startStation, String endStation, DateTime dateHeurDepart);
	
	public List<Station> getAllStations();
	
	
}
