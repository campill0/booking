package view;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import view.SearchHotelBean.Filter;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;
import dao.jpa.UniqueConstraintViolationException;

import model.CiudadDTO;
import model.HabitacionDTO;
import model.HotelDTO;
import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.ReservaDTO;
import model.TarjetaDTO;
import model.TipoHabitacionDTO;
import model.UsuarioDTO;

public class ActionsController {
private static final String SEED="801eb12f97148ae78c3cdb1b5ee83cc3";
	public void savePerson(ActionEvent actionEvent) throws NumberFormatException, DAOException {  
		UsuarioDTO u=(UsuarioDTO) actionEvent.getComponent().getAttributes().get("usuario");
		CiudadBean c=(CiudadBean) actionEvent.getComponent().getAttributes().get("ciudad");
		TarjetaDTO t=(TarjetaDTO) actionEvent.getComponent().getAttributes().get("tarjeta");
		CiudadDTO ciudad;
		ciudad=FactoryDAOImpl.loadInstance().getCiudad().getCiudad(Long.parseLong(c.getCiudadElegida()));
		FactoryDAOImpl.loadInstance().getTarjeta().insertTarjeta(t);
		u.setCiudad(ciudad);
		u.getTarjetas().add(t);
			u.setUsuarioId(FactoryDAOImpl.loadInstance().getUsuario().insertUsuario(u));
			// TODO Auto-generated catch block
			
			
			FacesMessage fm1=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este email ya se encuentra en la base de datos", "Este email ya se encuentra en la base de datos");
			FacesContext.getCurrentInstance().addMessage("Email duplicado",fm1);
			
		
		String confirmationCode=generateConfirmationCode(u.getMail());
		FacesMessage fm= new FacesMessage("Usuario guardado: " + u.getNombre() + " " + u.getApellidos() + " " + u.getMail() + " " + u.getPassword() + " " + c.getCiudadElegida() + " " + c.getProvinciaElegida() +  " guardado\n"
				+ "Haga click en el link para confirmar su cuenta: <a href=\"" +Util.baseUrl() + "/activation.faces?code=" + confirmationCode + "&userid=" + u.getUsuarioId() + "\"> Confirmar</a>"
				); 
		FacesContext.getCurrentInstance().addMessage("hola",fm);  
    }
	public void saveHotel(ActionEvent actionEvent) throws NumberFormatException, DAOException, IOException{
		
		CiudadBean c=(CiudadBean) actionEvent.getComponent().getAttributes().get("ciudad");
		HotelDTO h=(HotelDTO) actionEvent.getComponent().getAttributes().get("hotel");
		CiudadDTO ciudad;
		long ciudadId=Long.parseLong(c.getCiudadElegida());
		ciudad=FactoryDAOImpl.loadInstance().getCiudad().getCiudad(ciudadId);
		h.setCiudad(ciudad);
		
		List<TipoHabitacionDTO> habitacionesHotel = h.getHabitaciones();
		for (TipoHabitacionDTO tipoHabitacion : habitacionesHotel) {
			
			List<PeriodoNoDisponibilidadDTO> periodosHabitaciones=tipoHabitacion.getPeriodosDeNoDisponibilidad();
			for (PeriodoNoDisponibilidadDTO periodoNoDisponibilidadDTO : periodosHabitaciones) {
				periodoNoDisponibilidadDTO.setPeriodoID(FactoryDAOImpl.loadInstance().getPeriodoNoDisponibilidad().insertPeriodoNoDisponibilidad(periodoNoDisponibilidadDTO));
			}
			
			List<OfertaDescuentoDTO> ofertaDescuento=tipoHabitacion.getDescuentos();
			for (OfertaDescuentoDTO ofertaDescuentoDTO : ofertaDescuento) {
				ofertaDescuentoDTO.setOfertaDescuentoId(FactoryDAOImpl.loadInstance().getOfertaDescuento().insertOfertaDescuento(ofertaDescuentoDTO));
			}
			
			tipoHabitacion.setTipoHabitacionId(FactoryDAOImpl.loadInstance().getTipoHabitacion().insertTipoHabitacion(tipoHabitacion));
		}
		List<PeriodoNoDisponibilidadDTO> pndhotel = h.getPeriodosNoDisponibilidad();
		for (PeriodoNoDisponibilidadDTO pnd : pndhotel) {
			pnd.setPeriodoID(FactoryDAOImpl.loadInstance().getPeriodoNoDisponibilidad().insertPeriodoNoDisponibilidad(pnd));
		}
		
		FactoryDAOImpl.loadInstance().getHotel().insertHotel(h);
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Hotel created","Hotel created" ));
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			
	        externalContext.redirect(Util.baseUrl()+"/default.faces");
	}
	
	public void searchPerform(ActionEvent actionEvent) throws DAOException{
		SearchHotelBean search= (SearchHotelBean) actionEvent.getComponent().getAttributes().get("search");
		if((search.getCiudad()!=null)||(search.getProvincia()!=null)||(search.getHotel()!=null)){
		Filter f=search.getFilter();
		switch (f) {
		case CITY:
			search.setHoteles( 					Filters.filterHotelsByDates(FactoryDAOImpl.loadInstance().getHotel().findHotelByCity(search.getCiudad()), search.getFechaEntrada(),search.getFechaSalida(), search.getPersonNumber()) 					);
			break;
		case STATE:
			search.setHoteles(
					
					
					Filters.filterHotelsByDates(FactoryDAOImpl.loadInstance().getHotel().findHotelByState(search.getProvincia()), search.getFechaEntrada(),search.getFechaSalida(), search.getPersonNumber())
					);
			break;
		case HOTEL:
			search.setHoteles(new ArrayList<HotelDTO>());
			search.getHoteles().add(search.getHotel());
			break;
		default:
			break;
		}
		
		addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, search.getHoteles().size() + " hoteles encontrados","Se han encontrado "+ search.getHoteles().size() + " hoteles" ));
		
		}
		else{
			addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,"Debe introducir alguna consulta" ,"Debe introducir alguna consulta" ));
		}
		
	}
	
	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static String generateConfirmationCode(String s){
		return Util.md5(s+SEED);
	}
	public void bookingPerform(ActionEvent actionEvent) throws DAOException, IOException{
		ReservaBean reserva= (ReservaBean) actionEvent.getComponent().getAttributes().get("booking");
		SearchHotelBean search= (SearchHotelBean) actionEvent.getComponent().getAttributes().get("search");
		UsuarioDTO u=(UsuarioDTO)actionEvent.getComponent().getAttributes().get("userlogin");
		
		
		ReservaDTO r=new ReservaDTO();
		r.setFechaIni(reserva.getFechaEntrada());
		r.setFechaFin(reserva.getFechaSalida());
		r.setHotel(reserva.getHotel());
		r.setPeticiones(reserva.getPeticiones());
		Map<Long, Integer> mapaHabitaciones = reserva.getNumHabitaciones();
		List<TipoHabitacionDTO> habitaciones = r.getHotel().getHabitaciones();
		for (TipoHabitacionDTO th : habitaciones) {
			
			HabitacionDTO hab=new HabitacionDTO();
			long hid=th.getTipoHabitacionId();
			Object ob=mapaHabitaciones.get(hid);
			int numhabs=Integer.valueOf(ob+"");
			if(numhabs>0){
			hab.setNumHabitaciones(numhabs);
			hab.setTipoHabitacion(th);
			hab.setHabitacionId(FactoryDAOImpl.loadInstance().getHabitacion().insertHabitacion(hab));
			r.getHabitaciones().add(hab);
			}
		}
		r.setUsuario(u);
		
		FactoryDAOImpl.loadInstance().getReserva().insertReserva(r);
		 FacesContext.getCurrentInstance().getExternalContext().redirect("default");
		
	}
	public void updateBookingPerform(ActionEvent actionEvent) throws DAOException{
		ReservasUsuarioBean reservasUsuario= (ReservasUsuarioBean) actionEvent.getComponent().getAttributes().get("reservasUsuario");
		List<ReservaDTO> reservas=reservasUsuario.getReservas();
		for (ReservaDTO r : reservas) {
			FactoryDAOImpl.loadInstance().getReserva().updateReserva(r);
		}
	}
	
	public void updateHotelPerform(ActionEvent actionEvent) throws DAOException{
		HotelDTO hotel= (HotelDTO) actionEvent.getComponent().getAttributes().get("hotel");
		List<PeriodoNoDisponibilidadDTO> pndshotel=hotel.getPeriodosNoDisponibilidad();
		for (PeriodoNoDisponibilidadDTO pndhotel : pndshotel) {
			FactoryDAOImpl.loadInstance().getPeriodoNoDisponibilidad().updatePeriodoNoDisponibilidad(pndhotel);
		}
		
		List<TipoHabitacionDTO> thl=hotel.getHabitaciones();
		for (TipoHabitacionDTO th : thl) {
			List<PeriodoNoDisponibilidadDTO> pndsHabitacion=th.getPeriodosDeNoDisponibilidad();
			for (PeriodoNoDisponibilidadDTO pnd : pndsHabitacion) {
				FactoryDAOImpl.loadInstance().getPeriodoNoDisponibilidad().updatePeriodoNoDisponibilidad(pnd);
			}
			List<OfertaDescuentoDTO> ofertas=th.getDescuentos();
			for (OfertaDescuentoDTO oferta : ofertas) {
				FactoryDAOImpl.loadInstance().getOfertaDescuento().updateOfertaDescuento(oferta);
			}
			
			FactoryDAOImpl.loadInstance().getTipoHabitacion().updateHabitacion(th);
		}
			FactoryDAOImpl.loadInstance().getHotel().updateHotel(hotel);

			addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados","Datos guardados" ));
	}
	  
		
}
