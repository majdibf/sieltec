package service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.impl.ManagementService;

import commun.DBLoader;

import dao.IElementParcoursDao;
import dao.IParcoursDao;
import dao.IProgrammeDao;
import dao.IStationDao;
import dao.impl.ElementParcoursDao;
import dao.impl.LigneDao;
import dao.impl.ParcoursDao;
import dao.impl.ProgrammeDao;
import dao.impl.StationDao;
import db.ElementProgramme;
import db.Ligne;
import db.Parcours;
import db.Station;

public class ManagementServiceTest {
	private static ManagementService ms = new ManagementService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		DBLoader dbLoader = new DBLoader();
		StationDao stationDao = new StationDao();
		stationDao.setDbLoader(dbLoader);
		ProgrammeDao programmeDao = new ProgrammeDao();
		programmeDao.setDbLoader(dbLoader);
		ElementParcoursDao elementParcoursDao = new ElementParcoursDao();
		elementParcoursDao.setDbLoader(dbLoader);
		ParcoursDao parcoursDao = new ParcoursDao();
		parcoursDao.setDbLoader(dbLoader);
		
		LigneDao ligneDao = new LigneDao();
		ligneDao.setDbLoader(dbLoader);
		
		ms.setDbLoader(dbLoader);
		ms.setStationDao(stationDao);
		ms.setProgrammeDao(programmeDao);
		ms.setElementParcoursDao(elementParcoursDao);
		ms.setParcoursDao(parcoursDao);
		
		ms.setLigneDao(ligneDao);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindPath() {
		
		List<ElementProgramme> itineraire = ms.findPath("Station1", "Station24", new DateTime(2013,1,5,07,50));
		assertTrue(itineraire != null);
		assertTrue(!itineraire.isEmpty());
	}

	@Test
	public void testGetParcoursByIdList() {
		List<Long> idParcoursList = new ArrayList<Long>();
		idParcoursList.add(1L);
		idParcoursList.add(3L);
		idParcoursList.add(2L);
		idParcoursList.add(6L);
		HashMap<Long, Parcours> result = ms.getParcoursByIdList(idParcoursList);
		Parcours p = result.get(1L);
		assertTrue(p.getId() == 1L);
		p = result.get(2L);
		assertTrue(p.getId() == 2L);
		p = result.get(3L);
		assertTrue(p.getId() == 3L);
		p = result.get(6L);
		assertTrue(p.getId() == 6L);

	}

	@Test
	public void testGetParcoursByIdList2() {
		List<Long> idParcoursList = new ArrayList<Long>();
		HashMap<Long, Parcours> result = ms.getParcoursByIdList(idParcoursList);
		assertTrue(result.isEmpty());

	}

	@Test
	public void testGetStationByIdList() {
		List<Long> idStationsList = new ArrayList<Long>();
		HashMap<Long, Station> result = ms.getStationsByIdList(idStationsList);
		assertTrue(result.isEmpty());

	}

	@Test
	public void testGetLignesByIdStation() {
		long idStartStation=1;
		List<Ligne> result = ms.getLignesByIdStation(idStartStation);
		assertTrue(!result.isEmpty());
		
		Ligne l1 = result.get(0);
		assertTrue(l1.getId() == 1);
		assertTrue(l1.getNom().equals("bleu"));
		
		Ligne l2 = result.get(1);
		assertTrue(l2.getId() == 2);
		assertTrue(l2.getNom().equals("rouge"));

	}
	

	
	@Test
	public void testGetParcoursByIdLigne() {
		long idLigne=1;
		List<Parcours> result = ms.getParcoursByIdLigne(idLigne);
		assertTrue(!result.isEmpty());
		
		Parcours p1 = result.get(0);
		assertTrue(p1.getId() == 1);
		assertTrue(p1.getNom().equals("bleu_aller"));
		
		//Parcours p2 = result.get(1);
		//assertTrue(p2.getId() == 7);
		//assertTrue(p2.getNom().equals("bleu_retour"));

	}

	@Test
	public void testFindElementsProgrammes(){
		DateTime date=new DateTime(2012, 1, 1,8,0) ;
		long idParcours=1;
	
		List<ElementProgramme> result = ms.findElementsProgrammes(idParcours, date);
		assertTrue(!result.isEmpty()); 
	}


	
	@Test
	public void testFindProchainPassage(){
		DateTime d=new DateTime(2012, 1, 1,8,0) ;
		long idParcours=1;
		long idStation=1;
		
		List<ElementProgramme> result = ms.FindProchainPassage(idStation, idParcours, d);
		assertTrue(!result.isEmpty());
	}

}
