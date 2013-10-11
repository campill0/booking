package dao;

import dao.jpa.FactoryDAOImpl;
import dao.jpa.HabitacionDAOImpl;


public abstract class FactoryDAO {

	

public static FactoryDAO loadInstance(){
    return new FactoryDAOImpl();
}

public abstract HotelDAO getHotel();
public abstract ReservaDAO getReserva();
public abstract TipoHabitacionDAO getTipoHabitacion();
public abstract UsuarioDAO getUsuario();
public abstract CiudadDAO getCiudad();
public abstract OfertaDescuentoDAO getOfertaDescuento();
public abstract ProvinciaDAO getProvincia();
public abstract TarjetaDAO getTarjeta();
public abstract PeriodoNoDisponibilidadDAO getPeriodoNoDisponibilidad();
public abstract HabitacionDAO getHabitacion();



}

