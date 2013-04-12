package service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import db.Conducteur;
import db.ElementParcours;
import db.ElementProgramme;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.SouscriptionAlerte;
import db.Station;
import db.Utilisateur;
import db.Vehicule;


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

	public long insertConducteur(Conducteur c);

	public long insertLigne(Ligne l);

	public List<Vehicule> getAllVehicules();

	public long insertVehicule(Vehicule v);

	public Parcours getParcoursById(Long parcoursId);

	public Vehicule getVehiculeById(Long vehiculeId);

	public Conducteur getConducteurById(Long conducteurId);

	public List<Parcours> getAllParcours();

	public Vehicule getVehiculeByImmatriculation(String vehicule);

	public Conducteur getConducteurByName(String nom, String prenom);

	public void insertProgramme(Programme programme);

	public Ligne getLignesById(Long ligneId);

	public Ligne getLigneByName(String ligne);

	public Long insertParcours(Parcours parc);

	public Station getStationsById(Long stationDepId);

	public void insertParcours(Parcours p, List<ElementParcours> elementsParcours);

	public List<ElementParcours> getElementParcoursByIdParcours(Long idParcours);

	
}