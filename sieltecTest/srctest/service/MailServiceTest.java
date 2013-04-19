package service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.impl.MailDao;

import service.impl.MailService;

public class MailServiceTest {
	private static MailService ms = new MailService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		ms.setMailDao(new MailDao());
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
	public void testSendMail() {
		ms.sendMail("sieltec3@gmail.com", "testSendMail", "Ceci est un test de sendMail");
	}

}
