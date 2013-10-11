package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.ProvinciaDTO;

import org.junit.Test;

import dao.DAOException;
import dao.jpa.ProvinciaDAOImpl;


public class ProvinciaTest extends BasicoTest {

	@Test
	public void test() throws DAOException {
	ProvinciaDAOImpl provincias=new ProvinciaDAOImpl();
	//ArrayList<ProvinciaDTO> listaProvincias = provincias.findAllProvincia();
	System.out.println("aaa");
	}

}
