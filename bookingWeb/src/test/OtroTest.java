package test;

import model.CiudadDTO;

import org.junit.Test;

import dao.DAOException;
import dao.jpa.CiudadDAOImpl;


public class OtroTest {
	@Test
	public void test() throws DAOException {
		CiudadDAOImpl ciudades=new CiudadDAOImpl();
		CiudadDTO c=new CiudadDTO();
		c.setCodigoPostal("11");
		c.setNombre("Pepe");
		ciudades.insertCiudad(c);
		System.out.println("blaha");
	}
}
