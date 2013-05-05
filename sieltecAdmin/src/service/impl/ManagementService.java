package service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
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

import dao.IAlerteDao;
import dao.IConducteurDao;
import dao.IElementParcoursDao;
import dao.IEvenementDao;
import dao.ILigneDao;
import dao.IParcoursDao;
import dao.IProgrammeDao;
import dao.ISouscriptionAlerteDao;
import dao.IStationDao;
import dao.IUtilisateurDao;
import dao.IVehiculeDao;
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

@ManagedBean(name = "managementService", eager = true)
@ApplicationScoped
public class ManagementService implements IManagementService, Serializable {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@ManagedProperty(value = "#{evenementDao}")
	private IEvenementDao evenementDao;

	@ManagedProperty(value = "#{stationDao}")
	private IStationDao stationDao;
	
	@ManagedProperty(value = "#{alerteDao}")
	private IAlerteDao alerteDao;
	
	@ManagedProperty(value = "#{vehiculeDao}")
	private IVehiculeDao vehiculeDao;
	
	@ManagedProperty(value = "#{conducteurDao}")
	private IConducteurDao conducteurDao;

	@ManagedProperty(value = "#{programmeDao}")
	private IProgrammeDao programmeDao;

	@ManagedProperty(value = "#{elementParcoursDao}")
	private IElementParcoursDao elementParcoursDao;

	@ManagedProperty(value = "#{parcoursDao}")
	private IParcoursDao parcoursDao;

	@ManagedProperty(value = "#{ligneDao}")
	private ILigneDao ligneDao;
	
	@ManagedProperty(value = "#{souscriptionAlerteDao}")
	private ISouscriptionAlerteDao souscriptionAlerteDao;
	
	@ManagedProperty(value = "#{utilisateurDao}")
	private IUtilisateurDao utilisateurDao;

	public ManagementService() {
		super();
		System.out.println("ManagementService instanciated");
	}
			
	public IAlerteDao getAlerteDao() {
		return alerteDao;
	}

	public void setAlerteDao(IAlerteDao alerteDao) {
		this.alerteDao = alerteDao;
	}

	public IUtilisateurDao getUtilisateurDao() {
		return utilisateurDao;
	}

	public void setUtilisateurDao(IUtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public ISouscriptionAlerteDao getSouscriptionAlerteDao() {
		return souscriptionAlerteDao;
	}

	public void setSouscriptionAlerteDao(
			ISouscriptionAlerteDao souscriptionAlerteDao) {
		this.souscriptionAlerteDao = souscriptionAlerteDao;
	}
	

	public IVehiculeDao getVehiculeDao() {
		return vehiculeDao;
	}

	public void setVehiculeDao(IVehiculeDao vehiculeDao) {
		this.vehiculeDao = vehiculeDao;
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
	public List<ElementProgramme> findPath(String startStation,
			String endStation, DateTime dateHeurDepart) {

		List<ElementProgramme> result = new ArrayList<ElementProgramme>();

		Map<Long, DateTime> initialStationsMap = new HashMap<Long, DateTime>();
		Map<Long, DateTime> finalStationsMap = new HashMap<Long, DateTime>();
		Map<Long, ElementProgramme> stationsPredecessorsMap = new HashMap<Long, ElementProgramme>();

		List<Station> stations = stationDao.findAll();
		Station staDep = stationDao.findByName(startStation);
		Station staArr = stationDao.findByName(endStation);
		List<ElementProgramme> elementsProgramme = buildElementsProgrammes(dateHeurDepart);

		for (Station station : stations) {
			if (station.getId() == staDep.getId()) {
				initialStationsMap.put(station.getId(), dateHeurDepart);
			} else {
				initialStationsMap.put(station.getId(),
						dateHeurDepart.plus(Minutes.MAX_VALUE));
			}
		}

		List<ElementProgramme> candidats = null;
		while (!initialStationsMap.isEmpty()) {

			Long minStation = getMinStation(initialStationsMap);
			DateTime minTime = initialStationsMap.get(minStation);

			finalStationsMap.put(minStation, minTime);
			initialStationsMap.remove(minStation);

			candidats = getCandidats(minStation, minTime, elementsProgramme);
			updateVoisins(candidats, initialStationsMap,
					stationsPredecessorsMap);

		}

		Long staId = staArr.getId();
		boolean stop = false;
		while (!stop && staId != staDep.getId()) {
			ElementProgramme element = stationsPredecessorsMap.get(staId);
			if (element != null) {
				result.add(0, element);
				staId = element.getStationDepId();
			} else {
				stop = true;
			}
		}

		System.out.println(finalStationsMap.get(staArr));
		return result;
	}

	private static void updateVoisins(List<ElementProgramme> candidats,
			Map<Long, DateTime> stationsMap,
			Map<Long, ElementProgramme> stationsPredecessorsMap) {

		for (ElementProgramme element : candidats) {

			Long stationArrId = element.getStationArrId();
			DateTime dateHeureArr = element.getDateHeureArrivee();
			DateTime oldDateTime = stationsMap.get(stationArrId);
			if (oldDateTime != null && dateHeureArr.isBefore(oldDateTime)) {
				stationsMap.put(stationArrId, dateHeureArr);
				stationsPredecessorsMap.put(stationArrId, element);
			}

		}

	}

	private Long getMinStation(Map<Long, DateTime> map) {
		Long result = -1l;
		DateTime min = null;
		Set<Long> stations = map.keySet();

		for (Long station : stations) {
			DateTime time = map.get(station);
			if (min == null || time.isBefore(min)) {
				result = station;
				min = time;
			}
		}

		return result;
	}

	private List<ElementProgramme> getCandidats(Long stationId,
			DateTime dateHeure, List<ElementProgramme> elementsProgramme) {

		List<ElementProgramme> resultat = new ArrayList<ElementProgramme>();

		for (ElementProgramme element : elementsProgramme) {
			if (element.getStationDepId() == stationId
					&& element.getDateHeureDepart().isAfter(dateHeure)) {
				resultat.add(element);
			}
		}

		return resultat;
	}

	@Override
	public List<Station> getAllStations() {
		return stationDao.findAll();
	}

	@Override
	public List<ElementProgramme> buildElementsProgrammes(DateTime jour) {
		List<ElementProgramme> elementsProgrammes = new ArrayList<ElementProgramme>();
		List<Programme> programmes = programmeDao.findByDate(jour);
		List<ElementParcours> elementsParcours = elementParcoursDao.findAll();

		for (Programme progr : programmes) {
			elementsProgrammes.addAll(executeProgramme(progr, elementsParcours));
		}

		return elementsProgrammes;
	}

	private List<ElementProgramme> executeProgramme(Programme prog,List<ElementParcours> allElementsParcours) {
		List<ElementProgramme> result = new ArrayList<ElementProgramme>();

		List<ElementParcours> elementsParcours = new ArrayList<ElementParcours>();
		for (ElementParcours elemParc : allElementsParcours) {
			if (elemParc.getParcoursId() == prog.getParcoursId()) {
				elementsParcours.add(elemParc);
			}
		}

		elementsParcours = trierElementsParcours(elementsParcours);

		DateTime dateHeureDepart = prog.getDateHeureDebut();
		for (ElementParcours elemPar : elementsParcours) {
			ElementProgramme elPr = new ElementProgramme(elemPar.getStationDepId(), elemPar.getStationArrId(), dateHeureDepart, dateHeureDepart.plusMinutes(elemPar.getDuree().getMinutes()), elemPar.getParcoursId());
			dateHeureDepart = elPr.getDateHeureArrivee().plusMinutes(elemPar.getDureeArret().getMinutes());
			result.add(elPr);
		}

		return result;
	}

	private List<ElementParcours> trierElementsParcours(
			List<ElementParcours> elemParcours) {
		List<ElementParcours> result = new ArrayList<ElementParcours>();

		ElementParcours firstEP = elemParcours.remove(0);
		ElementParcours lastEP = firstEP;
		result.add(firstEP);
		while (!elemParcours.isEmpty()) {
			ElementParcours ep = elemParcours.remove(0);
			if (ep.getStationArrId() == firstEP.getStationDepId()) {
				result.add(0, ep);
				firstEP = ep;
				continue;
			}
			if (ep.getStationDepId() == lastEP.getStationArrId()) {
				result.add(ep);
				lastEP = ep;
				continue;
			}
			elemParcours.add(ep);
		}

		return result;
	}
	

	public ILigneDao getLigneDao() {
		return ligneDao;
	}

	public void setLigneDao(ILigneDao ligneDao) {
		this.ligneDao = ligneDao;
	}

	public IProgrammeDao getProgrammeDao() {
		return programmeDao;
	}

	public void setProgrammeDao(IProgrammeDao programmeDao) {
		this.programmeDao = programmeDao;
	}

	public IElementParcoursDao getElementParcoursDao() {
		return elementParcoursDao;
	}

	public void setElementParcoursDao(IElementParcoursDao elementParcoursDao) {
		this.elementParcoursDao = elementParcoursDao;
	}
	

	public IConducteurDao getConducteurDao() {
		return conducteurDao;
	}

	public void setConducteurDao(IConducteurDao conducteurDao) {
		this.conducteurDao = conducteurDao;
	}

	public IParcoursDao getParcoursDao() {
		return parcoursDao;
	}

	public void setParcoursDao(IParcoursDao parcoursDao) {
		this.parcoursDao = parcoursDao;
	}

	public IEvenementDao getEvenementDao() {
		return evenementDao;
	}

	public void setEvenementDao(IEvenementDao evenementDao) {
		this.evenementDao = evenementDao;
	}

	@Override
	public HashMap<Long, Station> getStationsByIdList(List<Long> idStations) {

		return stationDao.findByListId(idStations);
	}

	@Override
	public HashMap<Long, Parcours> getParcoursByIdList(List<Long> idParcours) {

		return parcoursDao.findByIdList(idParcours);
	}
	
	@Override
	public List<Ligne> getLignesByIdStation(long idStation) {
		
		return ligneDao.findLignesByIdStation(idStation);
	

	
	}

	@Override
	public List<Parcours> getParcoursByIdLigne(long idLigne) {
			
		return parcoursDao.findParcoursByIdLigne(idLigne);
	}

	
	@Override
	public Station getStationByName(String startStation) {
		
		return stationDao.findByName(startStation);

	}
	
	
	@Override
	public List<ElementProgramme> FindProchainPassage(long idStation,long idParcours,DateTime date) {
		List <ElementProgramme> elementProgrammes = findElementsProgrammes(idParcours, date);
		List<ElementProgramme> result=new ArrayList<ElementProgramme>();
		
		for(ElementProgramme ep : elementProgrammes){
			if(((ep.getStationDepId()==idStation && ep.getDateHeureDepart().isAfter(date))) || (ep.getStationArrId()== idStation && ep.getDateHeureArrivee().isAfter(date))){
				
				result.add(ep);
			}
		
		}
		return result;	
	}

	@Override
	public List<ElementProgramme> findElementsProgrammes(long idParcours ,DateTime date) {
		
		List<ElementProgramme> elementsProgrammes = new ArrayList<ElementProgramme>();
		List<ElementParcours> elementsParcours = elementParcoursDao.findByIdParcours(idParcours);
		List<Programme> programmes = programmeDao.findByDateAndIdParcours(date, idParcours);
		
		for (Programme progr : programmes) {
			elementsProgrammes.addAll(executeProgramme(progr, elementsParcours));
		}

		return elementsProgrammes;
	}

	@Override
	public Parcours getParcoursByName(String destination) {
		return parcoursDao.findByNameParcours(destination);
		
	}

	@Override
	public List<Ligne> getAllLignes() {
		return ligneDao.findAll();
	}

	@Override
	public Long insertSouscriptionAlerte(SouscriptionAlerte souscriptionAlerte) {
		return souscriptionAlerteDao.insert(souscriptionAlerte);
	}

	@Override
	public Ligne findLigneByName(String nomLigne) {
		
		return ligneDao.findByName(nomLigne);
	}



	@Override
	public Utilisateur getUtilisateurByUserNameAndPassword(String login,String password) {
		return utilisateurDao.findUtilisateurByLoginAndPassword(login, password);
		
	}

	@Override
	public void insertStation(Station s) {
		stationDao.insert(s);
		
	}

	@Override
	public List<Conducteur> getAllConducteurs() {
		return conducteurDao.findAll() ;
	}

	@Override
	public List<Programme> getAllProgrammes() {
		return programmeDao.findAll();
	}

	@Override
	public long insertConducteur(Conducteur c) {
		return conducteurDao.insert(c);
	}

	@Override
	public long insertLigne(Ligne l) {
		return ligneDao.insert(l);
		
	}

	@Override
	public List<Vehicule> getAllVehicules() {
		return vehiculeDao.findAll();
	}

	@Override
	public long insertVehicule(Vehicule v) {
		return vehiculeDao.insert(v);
	}

	@Override
	public Parcours getParcoursById(Long parcoursId) {
		return parcoursDao.findById(parcoursId);
	}

	@Override
	public Vehicule getVehiculeById(Long vehiculeId) {
		return vehiculeDao.findById(vehiculeId);
		
	}

	@Override
	public Conducteur getConducteurById(Long conducteurId) {
		return conducteurDao.findById(conducteurId);
	}

	@Override
	public List<Parcours> getAllParcours() {
		return parcoursDao.findAll();
	}

	@Override
	public Vehicule getVehiculeByImmatriculation(String vehicule) {
		return vehiculeDao.findByImmatriculation(vehicule);
	}

	@Override
	public Conducteur getConducteurByName(String nom, String prenom) {
		return conducteurDao.findByName(nom,prenom);
	}

	@Override
	public void insertProgramme(Programme programme) {
		programmeDao.insert(programme);
		
	}

	@Override
	public Ligne getLignesById(Long ligneId) {
		return ligneDao.findLignesById(ligneId);
	}

	@Override
	public Ligne getLigneByName(String ligne) {
		return ligneDao.findByName(ligne);
	}

	@Override
	public Long insertParcours(Parcours parc) {
		return parcoursDao.insert(parc);
	}

	@Override
	public Station getStationsById(Long stationDepId) {
		return stationDao.findById(stationDepId);
	}

	@Override
	public void insertParcours(Parcours p, List<ElementParcours> elementsParcours) {
		parcoursDao.insert(p, elementsParcours);
	}

	@Override
	public List<ElementParcours> getElementParcoursByIdParcours(Long idParcours) {
		return trierElementsParcours(elementParcoursDao.findByIdParcours(idParcours));
	}

	@Override
	public boolean updateParcours(Parcours p, List<ElementParcours> elementsParcours) {
		return parcoursDao.update(p, elementsParcours);
	}

	@Override
	public boolean updateConducteur(Conducteur c) {
		return conducteurDao.update(c);
		
	}

	@Override
	public boolean updateLigne(Ligne l) {
		return ligneDao.update(l);
		
	}

	@Override
	public boolean updateStation(Station s) {
		return stationDao.update(s);
	}

	@Override
	public boolean updateVehicule(Vehicule v) {
		return vehiculeDao.update(v);
	}

	@Override
	public Programme getProgrammeById(Long idProgramme) {
		return programmeDao.findById(idProgramme);
	}

	@Override
	public boolean updateProgramme(Programme p) {
		return programmeDao.update(p);
	}

	@Override
	public boolean removeConducteur(Conducteur c) {
		return conducteurDao.delete(c);
	}

	@Override
	public boolean removeLigne(Ligne l) {
		
		return ligneDao.delete(l);
	}

	@Override
	public boolean removeProgramme(Programme p) {
		return programmeDao.delete(p);
	}

	@Override
	public boolean removeStation(Station s) {
		return stationDao.delete(s);
	}

	@Override
	public boolean removeVehicule(Vehicule v) {
		return vehiculeDao.delete(v);
	}

	@Override
	public boolean removeParcours(Parcours p) {
		return parcoursDao.delete(p);
	}

	@Override
	public List<Alerte> getAllAlertes() {
		return alerteDao.findAll();
	}

	@Override
	public void insertAlerte(Alerte a) {
		alerteDao.insert(a);
	}

	@Override
	public List<SouscriptionAlerte> getSouscriptionAlerteByIdLigne(Long ligneId) {
		return souscriptionAlerteDao.findByIdLigne();
	}

	@Override
	public List<Programme> findTodaysPrograms() {
		return programmeDao.findByDate(new DateTime());
	}
	
	@Override
	public void insertEvenement(Evenement e) {
		evenementDao.insert(e);
	}

}
