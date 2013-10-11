package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.TipoHabitacionDTO;

import org.junit.Test;

import dao.DAOException;
import dao.jpa.OfertaDescuentoDAOImpl;
import dao.jpa.PeriodoNoDisponibilidadDAOImpl;
import dao.jpa.TipoHabitacionDAOImpl;


public class TipoHabitacionTest extends BasicoTest {

@Test
public void test() throws DAOException {
	PeriodoNoDisponibilidadDAOImpl periodosND= new PeriodoNoDisponibilidadDAOImpl();
	TipoHabitacionDAOImpl habitaciones=new TipoHabitacionDAOImpl();
	OfertaDescuentoDAOImpl ofertas= new OfertaDescuentoDAOImpl();
	OfertaDescuentoDTO od=new OfertaDescuentoDTO();
	od.setFechaFin(Calendar.getInstance());
	od.setFechaIni(Calendar.getInstance());
	od.setPorcentaje(12.5F);
	
	TipoHabitacionDTO hab1=new TipoHabitacionDTO();
	hab1.setCategoria("Doble");
	hab1.setMaxPers(4);
	hab1.setNumHabitaciones(300);
	hab1.setPrecio(35.3F);
	
	PeriodoNoDisponibilidadDTO periodoND2=new PeriodoNoDisponibilidadDTO();
	periodoND2.setMotivo("Obras 2");
	periodoND2.setFechaIni(Calendar.getInstance());
	periodoND2.setFechaFin(Calendar.getInstance());
	
	hab1.getPeriodosDeNoDisponibilidad().add(periodoND2);
	hab1.getDescuentos().add(od);
	hab1.setTipoHabitacionId(habitaciones.insertTipoHabitacion(hab1));
	TipoHabitacionDTO hab2 = habitaciones.getTipoHabitacion(hab1.getTipoHabitacionId());
	System.out.println("Blahaha");
		
}
}
