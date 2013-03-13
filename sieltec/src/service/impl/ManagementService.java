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

import dao.IElementParcoursDao;
import dao.ILigneDao;
import dao.IParcoursDao;
import dao.IProgrammeDao;
import dao.IStationDao;
import db.ElementParcours;
import db.ElementProgramme;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.Station;

@ManagedBean(name = "managementService", eager = true)
@ApplicationScoped
public class ManagementService implements IManagementService, Serializable {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@ManagedProperty(value = "#{stationDao}")
	private IStationDao stationDao;

	@ManagedProperty(value = "#{programmeDao}")
	private IProgrammeDao programmeDao;

	@ManagedProperty(value = "#{elementParcoursDao}")
	private IElementParcoursDao elementParcoursDao;

	@ManagedProperty(value = "#{parcoursDao}")
	private IParcoursDao parcoursDao;

	@ManagedProperty(value = "#{ligneDao}")
	private ILigneDao ligneDao;

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
		List<Programme> programmes = programmeDao.findAll();
		List<ElementParcours> elementsParcours = elementParcoursDao.findAll();

		for (Programme progr : programmes) {
			elementsProgrammes
					.addAll(executeProgramme(progr, elementsParcours));
		}

		return elementsProgrammes;
	}

	private List<ElementProgramme> executeProgramme(Programme prog,
			List<ElementParcours> allElementsParcours) {
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
			ElementProgramme elPr = new ElementProgramme(
					elemPar.getStationDepId(), elemPar.getStationArrId(),
					dateHeureDepart, dateHeureDepart.plusMinutes(elemPar
							.getDuree().getMinutes()), elemPar.getParcoursId());
			dateHeureDepart = elPr.getDateHeureArrivee().plusMinutes(
					elemPar.getDureeArret().getMinutes());
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

	public IParcoursDao getParcoursDao() {
		return parcoursDao;
	}

	public void setParcoursDao(IParcoursDao parcoursDao) {
		this.parcoursDao = parcoursDao;
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
		List <ElementProgramme> elementProgrammes = buildElementsProgrammes(date);
		List<ElementProgramme> result=new ArrayList<ElementProgramme>();
		
		for(ElementProgramme ep : elementProgrammes){
			if((ep.getStationDepId()==idStation || ep.getStationArrId()==idStation) && ep.getParcoursId()== idParcours && ep.getDateHeureArrivee().isAfter(date.getMillis())){
				
				result.add(ep);
			}
		
		}
		return result;	
	}
	
}
