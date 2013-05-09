package service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import db.Alerte;
import db.Conducteur;
import db.ElementParcours;
import db.ElementProgramme;
import db.Evenement;
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
	
	public List<ElementProgramme> buildUpdatedElementsProgrammes(DateTime jour);
	
	public HashMap<Long, Station> getStationsByIdList(List<Long> idStations);

	public HashMap<Long, Parcours> getParcoursByIdList(List<Long> idParcours);

	public List<Ligne> getLignesByIdStation(long idStation);
	
	public List<Parcours> getParcoursByIdLigne(long idLigne);
	
	public List<ElementProgramme>findProchainPassage(long idStation,long idParcours,DateTime date);

	public Station getStationByName(String startStation);
	
	public List<ElementProgramme> buildUpdatedElementsProgrammes(long idParcours ,DateTime date);

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

	public boolean updateParcours(Parcours p, List<ElementParcours> elementsParcours);

	public boolean updateConducteur(Conducteur c);

	public boolean updateLigne(Ligne l);

	public boolean updateStation(Station s);

	public boolean updateVehicule(Vehicule v);

	public Programme getProgrammeById(Long idProgramme);

	public boolean updateProgramme(Programme p);

	public boolean removeConducteur(Conducteur c);

	public boolean removeLigne(Ligne l);

	public boolean removeProgramme(Programme p);

	public boolean removeStation(Station s);

	public boolean removeVehicule(Vehicule v);

	public boolean removeParcours(Parcours p);

	public List<Alerte> getAllAlertes();

	public void insertAlerte(Alerte a);

	public List<SouscriptionAlerte> getSouscriptionAlerteByIdLigne(Long ligneId);

	public List<Programme> findTodaysPrograms();
	
	public void insertEvenement(Evenement e);
	
	public Evenement getEvenement(Long idProgramme, Long idStation, Long idTypeEvenement);
	
	public List<ElementProgramme> executeProgramme(Programme prog);
	
	public List<ElementProgramme> executeUpdatedProgramme(Programme prog);

}
