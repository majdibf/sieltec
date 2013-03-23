package service;

import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import db.Conducteur;
import db.ElementProgramme;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.SouscriptionAlerte;
import db.Station;
import db.Utilisateur;


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
	
	public List<ElementProgramme> findElementsProgrammes(long idParcours ,DateTime date);

	public Parcours getParcoursByName(String destination);

	public List<Ligne> getAllLignes();
	
	public Long insertSouscriptionAlerte(SouscriptionAlerte souscriptionAlerte);

	public Ligne findLigneByName(String ligne);

	public Utilisateur getUtilisateurByUserNameAndPassword(String login,String password);

	public void insertStation(Station s);

	public List<Conducteur> getAllConducteurs();

	public List<Programme> getAllProgrammes();

	
}