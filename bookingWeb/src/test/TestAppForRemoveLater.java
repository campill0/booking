package test;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.CiudadDTO;
import model.HabitacionDTO;
import model.HotelDTO;
import model.ProvinciaDTO;
import model.ReservaDTO;
import model.TipoHabitacionDTO;
import model.UsuarioDTO;

import org.apache.commons.validator.routines.*;

import view.Util;



import dao.DAOException;

import dao.jpa.FactoryDAOImpl;



public class TestAppForRemoveLater {

	/**
	 * @param args
	 * @throws DAOException 
	 */
	
	public static void main(String[] args) throws DAOException {
		
	
		
		
		/*
		dao.jpa.Util.updateQuery("DELETE FROM Habitacion");
		dao.jpa.Util.updateQuery("DELETE FROM Reserva");*/
		/*
		
		Util util=new Util();
		
		Calendar sd = Calendar.getInstance();
		Calendar ed = Calendar.getInstance();
		sd.set(2012, 8, 1, 0, 0, 0); 		ed.set(2012, 8, 1, 0, 0, 0);
	
		System.out.println(util.daysBetween(sd, ed));
		sd.set(2012, 8, 1, 0, 0, 0); 		ed.set(2012, 8, 2, 0, 0, 0);
		System.out.println(util.daysBetween(sd, ed));
		sd.set(2012, 8, 1, 0, 0, 0); 		ed.set(2012, 8, 3, 0, 0, 0);
		System.out.println(util.daysBetween(sd, ed));
		
		dao.jpa.Util.updateQuery("DELETE FROM Habitacion");
		dao.jpa.Util.updateQuery("DELETE FROM Reserva");
		Calendar sd = Calendar.getInstance();
		Calendar ed = Calendar.getInstance();
		sd.set(2012, 8, 1, 0, 0, 0); 		ed.set(2012, 8, 5, 0, 0, 0);
		method(sd,ed,34);*/ 
		/*
		TipoHabitacionDTO th = FactoryDAOImpl.loadInstance().getTipoHabitacion().getTipoHabitacion(14678);
		Calendar startDate = Calendar.getInstance();
		startDate.set(2012, 9, 2, 0, 0, 0);
		
		Calendar endDate = Calendar.getInstance();
		endDate.set(2012, 9, 15, 0, 0, 0);
		int restantes=FactoryDAOImpl.loadInstance().getTipoHabitacion().getHabitacionesLibres(th, startDate, endDate);
		System.out.println(restantes);*/
		
	}
	public static void method(Calendar startDate,Calendar endDate,int numHabitaciones) throws DAOException{
		
		ReservaDTO r= new ReservaDTO();
		r.setFechaIni(startDate);
		r.setFechaFin(endDate);
		
		HotelDTO h=FactoryDAOImpl.loadInstance().getHotel().getHotel(2095);
		List<TipoHabitacionDTO> habitaciones=h.getHabitaciones();
		
		HabitacionDTO hab= new HabitacionDTO();
		hab.setNumHabitaciones(numHabitaciones);// tiene 59 habitaciones
		
for (TipoHabitacionDTO th : habitaciones) {
		if (th.getTipoHabitacionId()==14678)
		{
			hab.setTipoHabitacion(th);
		}	
		
		}
		
	hab.setHabitacionId(FactoryDAOImpl.loadInstance().getHabitacion().insertHabitacion(hab));
		r.getHabitaciones().add(hab);
		r.setPeticiones("Quiero un cojin rosa");
		r.setValoracion(3);
		UsuarioDTO u=FactoryDAOImpl.loadInstance().getUsuario().getUsuario(1);
		r.setUsuario(u);
		r.setHotel(h);
		FactoryDAOImpl.loadInstance().getReserva().insertReserva(r);
		
		System.out.println("blahah");
	}

}
