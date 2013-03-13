package service;

import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import db.ElementProgramme;
import db.Ligne;
import db.Parcours;
import db.Station;


public interface IManagementService {
	
	
	public List<ElementProgramme> findPath(String startStation, String endStation, DateTime dateHeurDepart);
	
	public List<Station> getAllStations();
	
	public List<ElementProgramme> buildElementsProgrammes(DateTime jour);

	public HashMap<Long, Station> getStationsByIdList(List<Long> idStations);

	public HashMap<Long, Parcours> getParcoursByIdList(List<Long> idParcours);

	public List<Ligne> getLignesByIdStation(long idStation);
	
	public List<Parcours> getParcoursByIdLigne(long idLigne);
	
	public List<ElementProgramme>FindProchainPassage(long idStation,long idParcours,DateTime date);

	public Station getStationByName(String startStation);
	
}
