package service;

import static org.junit.Assert.assertTrue;

import java.util.Date;
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

}
