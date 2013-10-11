package test;

import java.util.Calendar;

import model.PeriodoNoDisponibilidadDTO;

import org.junit.Test;

import dao.DAOException;
import dao.PeriodoNoDisponibilidadDAO;
import dao.jpa.FactoryDAOImpl;
import dao.jpa.PeriodoNoDisponibilidadDAOImpl;
import dao.jpa.pojos.Hotel;


public class PeriodoNoDisponibilidadTest extends BasicoTest {
	@Test
	public void test() throws DAOException{
		
		
		FactoryDAOImpl f=(FactoryDAOImpl) FactoryDAOImpl.loadInstance();
		PeriodoNoDisponibilidadDAO periodos= f.getPeriodoNoDisponibilidad();
		
		PeriodoNoDisponibilidadDTO periodoND1=new PeriodoNoDisponibilidadDTO();
		periodoND1.setMotivo("Obras");
		periodoND1.setFechaIni(Calendar.getInstance());
		periodoND1.setFechaFin(Calendar.getInstance());
		periodoND1.setPeriodoID(periodos.insertPeriodoNoDisponibilidad(periodoND1));
		periodoND1.setMotivo("cerrado");
		periodos.updatePeriodoNoDisponibilidad(periodoND1);
		System.out.println("Blaha");
		
		
	}
}
