package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.CiudadDTO;
import model.HotelDTO;
import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.ProvinciaDTO;
import model.ReservaDTO;
import model.TarjetaDTO;
import model.TipoHabitacionDTO;
import model.TipoTarjetaDTO;
import model.UsuarioDTO;

import org.junit.Ignore;
import org.junit.Test;

import dao.CiudadDAO;
import dao.DAOException;
import dao.HotelDAO;
import dao.OfertaDescuentoDAO;
import dao.PeriodoNoDisponibilidadDAO;
import dao.ProvinciaDAO;
import dao.ReservaDAO;
import dao.TarjetaDAO;
import dao.TipoHabitacionDAO;
import dao.UsuarioDAO;
import dao.jpa.CiudadDAOImpl;
import dao.jpa.FactoryDAOImpl;
import dao.jpa.HotelDAOImpl;
import dao.jpa.OfertaDescuentoDAOImpl;
import dao.jpa.PeriodoNoDisponibilidadDAOImpl;
import dao.jpa.ProvinciaDAOImpl;
import dao.jpa.TipoHabitacionDAOImpl;
import dao.jpa.UniqueConstraintViolationException;
import dao.jpa.pojos.OfertaDescuento;
import dao.jpa.pojos.TipoHabitacion;


public class CiudadTest {

	@Ignore
	public void test() throws DAOException, UniqueConstraintViolationException {
		FactoryDAOImpl f=(FactoryDAOImpl) FactoryDAOImpl.loadInstance();
		
		PeriodoNoDisponibilidadDAO periodosND= f.getPeriodoNoDisponibilidad();
		TipoHabitacionDAO habitaciones=f.getTipoHabitacion();
		OfertaDescuentoDAO ofertas= f.getOfertaDescuento();
		ReservaDAO reservas= f.getReserva();
		UsuarioDAO usuarios=f.getUsuario();
		CiudadDAO ciudades=f.getCiudad();
		TarjetaDAO tarjetas = f.getTarjeta();
		HotelDAO hoteles=f.getHotel();
		ProvinciaDAO provincias=f.getProvincia();
		//---------------------------------
		CiudadDTO c1= new CiudadDTO();
		c1.setCodigoPostal("3000");
		c1.setNombre("Murcia");
		CiudadDTO c2= new CiudadDTO();
		c2.setCodigoPostal("3001");
		c2.setNombre("Cieza");
		
		//---------------------------------
		//ciudades.insertCiudad(c1);
		//ciudades.insertCiudad(c2);
		
		//--------------------------------------------------
		ProvinciaDTO p1= new ProvinciaDTO();
		p1.setId(1);
		p1.setNombre("Murcia");
		ciudades.insertCiudad(c1);
		ciudades.insertCiudad(c2);
		p1.getCiudades().add(c1);
		p1.getCiudades().add(c2);
		provincias.insertProvincia(p1);
		//--------------------------------------------------
		
		
		//--------------------------------------------------
		PeriodoNoDisponibilidadDTO periodoND1=new PeriodoNoDisponibilidadDTO();
		periodoND1.setMotivo("Obras");
		periodoND1.setFechaIni(Calendar.getInstance());
		periodoND1.setFechaFin(Calendar.getInstance());
		periodoND1.setPeriodoID(periodosND.insertPeriodoNoDisponibilidad(periodoND1));
		//----------------------------------------------------------------
		HotelDTO h1=new HotelDTO();
		h1.setDescripcion("un hotel");
		h1.setDireccion("asdas");
		h1.setEstrellas(2);
		h1.setNombre("Un hotel");
		h1.setCiudad(c1);
		h1.getPeriodosNoDisponibilidad().add(periodoND1);
		
		
		TipoHabitacionDTO hab1=new TipoHabitacionDTO();
		hab1.setCategoria("Doble");
		hab1.setMaxPers(4);
		hab1.setNumHabitaciones(300);
		hab1.setPrecio(35.3F);
		
		PeriodoNoDisponibilidadDTO periodoND2=new PeriodoNoDisponibilidadDTO();
		periodoND2.setMotivo("Obras 2");
		periodoND2.setFechaIni(Calendar.getInstance());
		periodoND2.setFechaFin(Calendar.getInstance());
		periodoND2.setPeriodoID(periodosND.insertPeriodoNoDisponibilidad(periodoND2));
		
		hab1.getPeriodosDeNoDisponibilidad().add(periodoND2);
		OfertaDescuentoDTO od=new OfertaDescuentoDTO();
		od.setFechaFin(Calendar.getInstance());
		od.setFechaIni(Calendar.getInstance());
		od.setPorcentaje(3.4F);
		od.setOfertaDescuentoId(ofertas.insertOfertaDescuento(od));
		
		//List <OfertaDescuentoDTO> descuentos = new ArrayList<OfertaDescuentoDTO>();
		//descuentos.add(od);
		hab1.getDescuentos().add(od);
		
		hab1.setTipoHabitacionId(habitaciones.insertTipoHabitacion(hab1));
		
		h1.getHabitaciones().add(hab1);
		hoteles.insertHotel(h1);
		
		HotelDTO h2 = hoteles.getHotel(1);
		
		TarjetaDTO t=new TarjetaDTO();
		t.setEntidad("La caixa");
		t.setNumero("420045587380452");
		t.setTipo(TipoTarjetaDTO.VISA);
		tarjetas.insertTarjeta(t);
		
		UsuarioDTO u= new UsuarioDTO();
		u.setNombre("Francisco");
		u.setApellidos("Campillo Asensio");
		u.setMail("taspodrio@msn.com");
		u.setPassword("123456");
		u.setCiudad(h2.getCiudad());
		u.getTarjetas().add(t);
		u.setUsuarioId(usuarios.insertUsuario(u));
		
		ReservaDTO r= new ReservaDTO();
		r.setFechaFin(Calendar.getInstance());
		r.setFechaIni(Calendar.getInstance());
		//r.setHabitaciones(h2.getHabitaciones());
		r.setPeticiones("Quiero un cojín rosa");
		r.setValoracion(3);
		r.setUsuario(u);
		reservas.insertReserva(r);
		
		HotelDTO h3 = hoteles.getHotel(1);
		List<TipoHabitacionDTO> habitacionesLista=h3.getHabitaciones();
		TipoHabitacionDTO hab3= habitacionesLista.get(0);
		ReservaDTO res1=reservas.getReserva(1);
		TipoHabitacionDTO hab4=habitaciones.getTipoHabitacion(1);
		
		List<ReservaDTO> reserva1=habitaciones.getReservas(hab4);
		UsuarioDTO myuser = usuarios.getUsuario(1);
		myuser.setApellidos("Perez Sanchez");
		usuarios.updateUsuario(myuser);
		myuser=usuarios.getUsuario(1);
		myuser.setCiudad(c2);
		usuarios.updateUsuario(myuser);
		CiudadDTO c3= new CiudadDTO();
		c3.setNombre("Altorreal");
		c3.setCodigoPostal("30506");
		myuser.setCiudad(c3);
		usuarios.updateUsuario(myuser);
		myuser.getCiudad().setNombre("El romeral");
		usuarios.updateUsuario(myuser);
		myuser=usuarios.getUsuario(1);
		System.out.println("blahah");
		
		
	}

}
