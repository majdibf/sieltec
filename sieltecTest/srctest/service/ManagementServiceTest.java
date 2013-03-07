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
import dao.impl.ParcoursDao;
import dao.impl.ProgrammeDao;
import dao.impl.StationDao;
import db.ElementProgramme;
import db.Parcours;

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
		
		ms.setDbLoader(dbLoader);
		ms.setStationDao(stationDao);
		ms.setProgrammeDao(programmeDao);
		ms.setElementParcoursDao(elementParcoursDao);
		ms.setParcoursDao(parcoursDao);
		
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
	public void test() {
		
		List<ElementProgramme> itineraire = ms.findPath("Station1", "Station24", new DateTime(2013,1,5,07,50));
		assertTrue(itineraire != null);
		assertTrue(!itineraire.isEmpty());
	}

	@Test
	public void test2() {
		List<Long> idParcoursList = new ArrayList<Long>();
		idParcoursList.add(1L);
		idParcoursList.add(3L);
		idParcoursList.add(2L);
		idParcoursList.add(6L);
		HashMap<Long, Parcours> result = ms.getParcoursbyIdList(idParcoursList);
		Parcours p = result.get(1);
		assertTrue(p.getId() == 1L);
		p = result.get(2);
		assertTrue(p.getId() == 2L);
		p = result.get(3);
		assertTrue(p.getId() == 3L);
		p = result.get(6);
		assertTrue(p.getId() == 6L);

	}


}
