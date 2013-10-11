package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;

import dao.*;

public class FactoryDAOImpl extends FactoryDAO {

	public FactoryDAOImpl(){
    }
	
	public static EntityManager getEntityManager(){
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookingaadd");
		return emf.createEntityManager();
	}

	public  HotelDAO getHotel() {		return new HotelDAOImpl();	}
	public  ReservaDAO getReserva() {		return new ReservaDAOImpl();	}
	public  TipoHabitacionDAO getTipoHabitacion(){ return new TipoHabitacionDAOImpl();}
	public  UsuarioDAO getUsuario(){ return new UsuarioDAOImpl();}
	public  CiudadDAO getCiudad(){ return new CiudadDAOImpl();}
	public  OfertaDescuentoDAO getOfertaDescuento(){ return new OfertaDescuentoDAOImpl();}
	public  ProvinciaDAO getProvincia() { return new ProvinciaDAOImpl();}
	public  TarjetaDAO getTarjeta() { return new TarjetaDAOImpl();}
	public  PeriodoNoDisponibilidadDAO getPeriodoNoDisponibilidad(){ return new PeriodoNoDisponibilidadDAOImpl();}
	public  HabitacionDAO getHabitacion(){ return new HabitacionDAOImpl();}
	 
}
