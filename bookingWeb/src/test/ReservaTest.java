package test;

import java.util.Calendar;
import model.CiudadDTO;
import model.HabitacionDTO;
import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.ProvinciaDTO;
import model.ReservaDTO;
import model.TarjetaDTO;
import model.TipoHabitacionDTO;
import model.TipoTarjetaDTO;
import model.UsuarioDTO;

import org.junit.Test;

import dao.CiudadDAO;
import dao.DAOException;
import dao.HabitacionDAO;
import dao.OfertaDescuentoDAO;
import dao.PeriodoNoDisponibilidadDAO;
import dao.ProvinciaDAO;
import dao.ReservaDAO;
import dao.TarjetaDAO;
import dao.TipoHabitacionDAO;
import dao.UsuarioDAO;
import dao.jpa.FactoryDAOImpl;
import dao.jpa.UniqueConstraintViolationException;


public class ReservaTest extends BasicoTest {
	@Test
	public void test() throws DAOException, UniqueConstraintViolationException {
		/*
		FactoryDAOImpl f=(FactoryDAOImpl) FactoryDAOImpl.loadInstance();
	
	PeriodoNoDisponibilidadDAO periodos= f.getPeriodoNoDisponibilidad();
	TipoHabitacionDAO tiposHabitaciones=f.getTipoHabitacion();
	OfertaDescuentoDAO ofertas= f.getOfertaDescuento();
	ReservaDAO reservas= f.getReserva();
	UsuarioDAO usuarios=f.getUsuario();
	CiudadDAO ciudades=f.getCiudad();
	TarjetaDAO tarjetas = f.getTarjeta();
	HabitacionDAO habitaciones = f.getHabitacion();
	ProvinciaDAO provincias=f.getProvincia();
	//---------------------------------
	CiudadDTO c1= new CiudadDTO();
	c1.setCodigoPostal("3000");
	c1.setNombre("Murcia");
	CiudadDTO c2= new CiudadDTO();
	c2.setCodigoPostal("3001");
	c2.setNombre("Cieza");
	ProvinciaDTO p1= new ProvinciaDTO();
	p1.setId(100+(1 + (int)(Math.random()*10000)));
	p1.setNombre("Invernalia"+ (1 + (int)(Math.random()*10000)));
	
	c1.setCiudadId(ciudades.insertCiudad(c1));
	c2.setCiudadId(ciudades.insertCiudad(c2));
	
	
	p1.getCiudades().add(c1);
	p1.getCiudades().add(c2);
	provincias.insertProvincia(p1);
	//--------------------------------------------------

	//--------------------------------------------------
	PeriodoNoDisponibilidadDTO periodoND1=new PeriodoNoDisponibilidadDTO();
	periodoND1.setMotivo("Obras");
	periodoND1.setFechaIni(Calendar.getInstance());
	periodoND1.setFechaFin(Calendar.getInstance());
	periodoND1.setPeriodoID(periodos.insertPeriodoNoDisponibilidad(periodoND1));

	PeriodoNoDisponibilidadDTO periodoND2=new PeriodoNoDisponibilidadDTO();
	periodoND2.setMotivo("Obras 2");
	periodoND2.setFechaIni(Calendar.getInstance());
	periodoND2.setFechaFin(Calendar.getInstance());
	periodoND2.setPeriodoID(periodos.insertPeriodoNoDisponibilidad(periodoND2));

	OfertaDescuentoDTO od=new OfertaDescuentoDTO();
	od.setFechaFin(Calendar.getInstance());
	od.setFechaIni(Calendar.getInstance());
	od.setPorcentaje(3.4F);
	od.setOfertaDescuentoId(ofertas.insertOfertaDescuento(od));
	
	TipoHabitacionDTO habDoble=new TipoHabitacionDTO();
	habDoble.setCategoria("Habitaci�n doble");
	habDoble.setMaxPers(2);
	habDoble.setNumHabitaciones(300);
	habDoble.setPrecio(35.3F);
	
	TipoHabitacionDTO habCuadruple=new TipoHabitacionDTO();
	habCuadruple.setCategoria("Habitaci�n cu�druple");
	habCuadruple.setMaxPers(4);
	habCuadruple.setNumHabitaciones(300);
	habCuadruple.setPrecio(50.0F);
	
	habDoble.getDescuentos().add(od);
	habDoble.getPeriodosDeNoDisponibilidad().add(periodoND2);
	habDoble.setTipoHabitacionId(tiposHabitaciones.insertTipoHabitacion(habDoble));
	habCuadruple.setTipoHabitacionId(tiposHabitaciones.insertTipoHabitacion(habCuadruple));
	
	HabitacionDTO hab1= new HabitacionDTO();
	HabitacionDTO hab2= new HabitacionDTO();
	
	// Esto quiero decir que el cliente quiere 3 habitaciones dobles
	hab1.setNumHabitaciones(1);
	hab1.setTipoHabitacion(habDoble);
	hab2.setNumHabitaciones(1);
	hab2.setTipoHabitacion(habCuadruple);
	hab1.setHabitacionId(habitaciones.insertHabitacion(hab1));
	hab2.setHabitacionId(habitaciones.insertHabitacion(hab2));
	//--------------------------------------------------------------------
	
	TarjetaDTO t=new TarjetaDTO();
	t.setEntidad("La caixa");
	t.setNumero("420045587380452"+ (1 + (int)(Math.random()*1000)));
	t.setTipo(TipoTarjetaDTO.VISA);
	tarjetas.insertTarjeta(t);
	
	UsuarioDTO u= new UsuarioDTO();
	u.setNombre("Francisco");
	u.setApellidos("Campillo Asensio");
	u.setMail("taspodrio@msn.com");
	u.setPassword("123456");
	u.setCiudad(c1);
	u.getTarjetas().add(t);
	u.setUsuarioId(usuarios.insertUsuario(u));
	//-------------------------------------------------------------------------
	ReservaDTO r= new ReservaDTO();
	r.setFechaFin(Calendar.getInstance());
	r.setFechaIni(Calendar.getInstance());
	r.getHabitaciones().add(hab1);
	r.getHabitaciones().add(hab2);
	r.setPeticiones("Quiero un coj�n rosa");
	r.setValoracion(3); 
	r.setUsuario(u);
	r.setReservaId(reservas.insertReserva(r));
	System.out.println("blahah");
	*/
}
	
}
