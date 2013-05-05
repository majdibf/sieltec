package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.joda.time.DateTime;

import service.impl.ManagementService;

import commun.DBLoader;

import dao.impl.ElementParcoursDao;
import dao.impl.EvenementDao;
import dao.impl.LigneDao;
import dao.impl.ParcoursDao;
import dao.impl.ProgrammeDao;
import dao.impl.StationDao;
import db.ElementParcours;
import db.ElementProgramme;
import db.Evenement;
import db.Parcours;
import db.Programme;
import db.Station;

public class Simulate {
		
	private static DBLoader dbLoader = new DBLoader();
	private static StationDao stationDao = new StationDao();
	private static ProgrammeDao programmeDao = new ProgrammeDao();
	private static ElementParcoursDao elementParcoursDao = new ElementParcoursDao();
	private static ParcoursDao parcoursDao = new ParcoursDao();
	private static LigneDao ligneDao = new LigneDao();
	private static EvenementDao evenementDao = new EvenementDao();
	
	private static ManagementService ms = new ManagementService();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		stationDao.setDbLoader(dbLoader);
		programmeDao.setDbLoader(dbLoader);
		elementParcoursDao.setDbLoader(dbLoader);
		parcoursDao.setDbLoader(dbLoader);
		ligneDao.setDbLoader(dbLoader);
		evenementDao.setDbLoader(dbLoader);
		
		ms.setDbLoader(dbLoader);
		ms.setStationDao(stationDao);
		ms.setProgrammeDao(programmeDao);
		ms.setElementParcoursDao(elementParcoursDao);
		ms.setParcoursDao(parcoursDao);
		ms.setLigneDao(ligneDao);
		ms.setEvenementDao(evenementDao);
		
		try{
			List<Evenement> evenements = new ArrayList<Evenement>();
			List<ElementProgramme> elementsProgrammes = new ArrayList<ElementProgramme>();
			List<Programme> programmes = ms.findTodaysPrograms();
			
			for (Programme progr : programmes) {
				elementsProgrammes = executeProgramme(progr);
				for(ElementProgramme elemProg : elementsProgrammes){
					if(elemProg.getDateHeureDepart().isAfterNow()){
						Evenement evenementDep = new Evenement(null, progr.getId(), elemProg.getStationDepId(), 2L, elemProg.getDateHeureDepart(), 0);
						evenements.add(evenementDep);						
					}

					if(elemProg.getDateHeureArrivee().isAfterNow()){
						Evenement evenementArr = new Evenement(null, progr.getId(), elemProg.getStationArrId(), 1L, elemProg.getDateHeureArrivee(), 0);
						evenements.add(evenementArr);
					}
				}
			}
			
			while (evenements.size() > 0){
				List<Evenement> evenementsTmp = new ArrayList<Evenement>();
				for(Evenement evt : evenements){
					if (evt.getDateHeure().isBeforeNow()){
						evt.setDateHeure(new DateTime());
						ms.insertEvenement(evt);
						
						Programme prg = ms.getProgrammeById(evt.getProgrammeId());
						Parcours parc = ms.getParcoursById(prg.getParcoursId());
						Station station = ms.getStationsById(evt.getStationId());
						Long typeEvtId = evt.getTypeEvenementId();
						String typeEvt = ""; 
						if(typeEvtId == 2){
							typeEvt = "DEPART";
						} else {
							typeEvt = "ARRIVEE";
						}
						System.out.println(parc.getNom() + " : " + typeEvt + " : " + station.getNom());
						
					} else {
						evenementsTmp.add(evt);
					}
				}
				evenements = evenementsTmp;
				Thread.sleep(15000);
				System.out.println("**********");
			}
			
			
			System.out.println("End Simulation with success");
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		
	}
	
	private static List<ElementProgramme> executeProgramme(Programme prog) {
		List<ElementProgramme> result = new ArrayList<ElementProgramme>();

		List<ElementParcours> elementsParcours = new ArrayList<ElementParcours>();
		List<ElementParcours> allElementsParcours = elementParcoursDao.findAll();		
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
	

	private static List<ElementParcours> trierElementsParcours(
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
	
}
