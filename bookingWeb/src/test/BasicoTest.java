package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicoTest {
	
	
	protected Logger logger;
	private EntityManagerFactory emf;
	protected EntityManager em;

	@Before
	public void initEmfAndEm() {
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
		Logger.getLogger("tutorial").setLevel(Level.INFO);
		this.logger = Logger.getLogger("ejercicio");
		
		emf = Persistence.createEntityManagerFactory("bookingaadd");
		em = emf.createEntityManager();
	}

	@After
	public void cleanup() {
		em.close();
	}

	@Test
	public void emptyTest() {
		logger.warn("Test vacio de BasicoTest funciona");
		
	}
}
