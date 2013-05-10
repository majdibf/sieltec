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
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

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

	private static int tauxErreur =20;

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
			int delay = 1;
			int sleepDelay = 15000;
			
			DateTime now = DateTime.now();
			Programme programme = new Programme(null, now.plus(Minutes.minutes(0 + delay)), 1L, 1L, 1L, 0);
			ms.insertProgramme(programme);
			
			programme = new Programme(null, now.plus(Minutes.minutes(5 + delay)), 2L, 2L, 2L, 0);
			ms.insertProgramme(programme);
			
			programme = new Programme(null, now.plus(Minutes.minutes(12 + delay)), 3L, 3L, 3L, 0);
			ms.insertProgramme(programme);
			
			programme = new Programme(null, now.plus(Minutes.minutes(20 + delay)), 4L, 4L, 4L, 0);
			ms.insertProgramme(programme);
			
			programme = new Programme(null, now.plus(Minutes.minutes(0 + delay)), 5L, 5L, 5L, 0);
			ms.insertProgramme(programme);
			
			programme = new Programme(null, now.plus(Minutes.minutes(40 + delay)), 6L, 6L, 6L, 0);
			ms.insertProgramme(programme);
			
			
			
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
						System.out.println("===========================================================");
						System.out.println(parc.getNom() + " : " + typeEvt + " : " + station.getNom() + " : " + evt.getDateHeure());
						System.out.println("===========================================================");
						
					} else {
						evenementsTmp.add(evt);
					}
				}
				evenements = evenementsTmp;
				Thread.sleep(sleepDelay);
				System.out.println("next check for events is in : " + sleepDelay/1000 + "seconds");
			}
			
			
			System.out.println("End Simulation with success");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
		
	}
	
	private static List<ElementProgramme> executeProgramme(Programme prog) {
		List<ElementProgramme> result = new ArrayList<ElementProgramme>();
		Long parcoursId = prog.getParcoursId();

		Random randomizer = new Random();
		//récupération et tri des éléments parcours correspondants au programme 
		List<ElementParcours> elementsParcours = ms.getElementParcoursByIdParcours(parcoursId);
		elementsParcours = trierElementsParcours(elementsParcours);

		
		DateTime dateHeureProchainDepart = prog.getDateHeureDebut();
		//Pour chaque élément parcours calculer l'élément programme correspondant
		for (ElementParcours elemPar : elementsParcours) {
			int sign = randomizer.nextBoolean() == true? 1 : -1;
			
			Long stationDepId = elemPar.getStationDepId();
			Long stationArrId = elemPar.getStationArrId();
			DateTime dateHeureDep = dateHeureProchainDepart;
			DateTime dateHeureArr = dateHeureDep.plusMinutes(elemPar.getDuree().getMinutes());
			//introduction d'un décalage (retard ou avance) pour la simulation
			int maxDecalage = elemPar.getDuree().getMinutes() * tauxErreur/100;
			int retardAvance = sign * randomizer.nextInt(Math.max(1, maxDecalage) + 1);
			System.out.println("décalge de " + retardAvance + " sur " + elemPar.getDuree().getMinutes());
			dateHeureArr = dateHeureArr.plusMinutes(retardAvance);
			ElementProgramme elPr = new ElementProgramme(stationDepId, stationArrId, dateHeureDep, dateHeureArr, parcoursId);
			dateHeureProchainDepart = dateHeureArr.plusMinutes(elemPar.getDureeArret().getMinutes());
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
