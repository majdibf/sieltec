package service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import service.IManagementService;

import commun.DBLoader;

import dao.IStationDao;
import db.ElementProgramme;
import db.Station;

@ManagedBean(name="managementService", eager=true)
@ApplicationScoped
public class ManagementService implements IManagementService, Serializable {
	
	@ManagedProperty(value="#{stationDao}")
	private IStationDao stationDao;

	@ManagedProperty(value="#{dbloader}")
	private DBLoader dbLoader;

	
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
	
	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public List<ElementProgramme> findPath(String startStation, String endStation, DateTime dateHeurDepart) {
//		String result = "";
//		PathFinder finder = new PathFinder();
//		List<ElementProgramme> bestPath = finder.findBestPath(dbLoader.getStations().get(Integer.parseInt(startStation)), dbLoader.getStations().get(Integer.parseInt(endStation)), new DateTime(2013, 01, 05, 07, 52), dbLoader.getElementsProgramme());
		
		List<ElementProgramme> result = new ArrayList<ElementProgramme>();
		
		Map<Station, DateTime> initialStationsMap = new HashMap<Station, DateTime>();
		Map<Station, DateTime> finalStationsMap = new HashMap<Station, DateTime>();
		Map<Station, ElementProgramme> stationsPredecessorsMap = new HashMap<Station, ElementProgramme>();
		
		List<Station> stations = dbLoader.getStations();
		Station staDep = stations.get(Integer.parseInt(startStation));
		Station staArr = stations.get(Integer.parseInt(endStation));
		List<ElementProgramme> elementsProgramme = dbLoader.getElementsProgramme();
		
		for(Station station : stations){
			if(station == null){
				continue;
			}
			if(station.getId() == staDep.getId()){
				initialStationsMap.put(station, dateHeurDepart);
			} else {
				initialStationsMap.put(station, dateHeurDepart.plus(Minutes.MAX_VALUE));
			}
		}
		
		List<ElementProgramme> candidats = null;
		while(!initialStationsMap.isEmpty()){
		
			Station minStation = getMinStation(initialStationsMap);
			DateTime minTime = initialStationsMap.get(minStation);
			finalStationsMap.put(minStation, minTime);
			initialStationsMap.remove(minStation);
			candidats = getCandidats(minStation, dateHeurDepart, elementsProgramme);
			updateVoisins(candidats, initialStationsMap, stationsPredecessorsMap);
			
		}
		
		Station sta = staArr;
		boolean stop = false;
		while(!stop && !sta.equals(staDep)){
			ElementProgramme element = stationsPredecessorsMap.get(sta);
			if(element != null){
				result.add(0,element);
				sta = element.getStationDep();
			} else {
				stop = true;
			}
		}

		System.out.println(finalStationsMap.get(staArr));
		return result;		
	}
	
	private static void updateVoisins(List<ElementProgramme> candidats, Map<Station, DateTime> stationsMap, Map<Station, ElementProgramme> stationsPredecessorsMap){
		
		for(ElementProgramme element : candidats){
			
			Station stationArr = element.getStationArr();
			DateTime dateHeureArr = element.getDateHeureArrivee();
			DateTime oldDateTime = stationsMap.get(stationArr);
			if(oldDateTime != null && dateHeureArr.isBefore(oldDateTime)){
				stationsMap.put(stationArr, dateHeureArr);
				stationsPredecessorsMap.put(stationArr, element);
			}
			
		}
		
	}
	
	private Station getMinStation(Map<Station, DateTime> map){
		Station result = null;
		DateTime min = null;
		Set<Station> stations = map.keySet();
		
		for(Station station : stations){
			DateTime time = map.get(station);
			if(min == null || time.isBefore(min)){
				result = station;
				min = time;
			}
		}
		
		
		return result;
	}
	
	private List<ElementProgramme> getCandidats(Station station, DateTime dateHeure, List<ElementProgramme> elementsProgramme){
		
		List<ElementProgramme> resultat = new ArrayList<ElementProgramme>(); 
		
		for(ElementProgramme element : elementsProgramme){
			if(element.getStationDep().getId() == station.getId() && element.getDateHeureDepart().isAfter(dateHeure)){
				resultat.add(element);
			}
		}
		
		return resultat;
	}	
	
	

	@Override
	public List<Station> getAllStations() {
		return stationDao.findAll();
	}

}
