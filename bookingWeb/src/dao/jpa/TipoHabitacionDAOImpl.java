package dao.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import model.HotelDTO;
import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.ReservaDTO;
import model.TipoHabitacionDTO;
import view.*;
import dao.DAOException;
import dao.TipoHabitacionDAO;
import dao.jpa.pojos.Reserva;
import dao.jpa.pojos.TipoHabitacion;



public class TipoHabitacionDAOImpl implements TipoHabitacionDAO{
	protected static List<TipoHabitacionDTO> castFromDAOImplToDTO(List<TipoHabitacion> habitacionesImpl){
		List<TipoHabitacionDTO> habitacionesDTO=new ArrayList<TipoHabitacionDTO>();
		for (TipoHabitacion tipoHabitacion : habitacionesImpl) {
			TipoHabitacionDTO h = castFromDAOImplToDTO(tipoHabitacion);
			habitacionesDTO.add(h);
		}
		return habitacionesDTO;
	}
	protected static TipoHabitacionDTO castFromDAOImplToDTO(TipoHabitacion tipoHabitacion) {
		TipoHabitacionDTO h=new TipoHabitacionDTO();
		h.setCategoria(tipoHabitacion.getCategoria());
		h.setMaxPers(tipoHabitacion.getMaxPers());
		h.setNumHabitaciones(tipoHabitacion.getNumHabitaciones());
		h.setPrecio(tipoHabitacion.getPrecio());
		List <OfertaDescuentoDTO> descuentos=OfertaDescuentoDAOImpl.castFromDAOImplToDTO( tipoHabitacion.getDescuentos() );
		h.setDescuentos(descuentos);
		List <PeriodoNoDisponibilidadDTO> periodoND=PeriodoNoDisponibilidadDAOImpl.castFromDAOImplToDTO( tipoHabitacion.getPeriodosND() );
		h.setPeriodosDeNoDisponibilidad(periodoND);
		
		h.setTipoHabitacionId(tipoHabitacion.getTipoHabitacionId());
		return h;
	}
	protected static  TipoHabitacion castFromDTOToDAOImpl(TipoHabitacionDTO tipoHabitacion){
		TipoHabitacion h=new TipoHabitacion();
		h.setCategoria(tipoHabitacion.getCategoria());
		h.setMaxPers(tipoHabitacion.getMaxPers());
		h.setNumHabitaciones(tipoHabitacion.getNumHabitaciones());
		h.setPrecio(tipoHabitacion.getPrecio());
		h.setDescuentos(OfertaDescuentoDAOImpl.castFromDTOToDAOImpl( tipoHabitacion.getDescuentos()));
		h.setPeriodosND(PeriodoNoDisponibilidadDAOImpl.castFromDTOToDAOImpl(tipoHabitacion.getPeriodosDeNoDisponibilidad()));
		h.setTipoHabitacionId(tipoHabitacion.getTipoHabitacionId());
		
		
		return h;
	}
	protected static List<TipoHabitacion> castFromDTOToDAOImpl(List<TipoHabitacionDTO> tipoHabitacionesDTO){
		List<TipoHabitacion> habitacionesImpl=new ArrayList<TipoHabitacion>();
		for (TipoHabitacionDTO tipoHabitacion : tipoHabitacionesDTO) {
			habitacionesImpl.add(castFromDTOToDAOImpl(tipoHabitacion));
		}
		return habitacionesImpl;
	}
	public long insertTipoHabitacion(TipoHabitacionDTO tipoHabitacion) throws DAOException {
		// TODO Auto-generated method stub
		return ((TipoHabitacion)Util.persist(castFromDTOToDAOImpl(tipoHabitacion))).getTipoHabitacionId();
		
	}
	public TipoHabitacionDTO getTipoHabitacion(long id) throws DAOException {
		// TODO Auto-generated method stub
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT th FROM TipoHabitacion th WHERE th.tipoHabitacionId = :id");
		query.setParameter("id", id);
		TipoHabitacion tipoHabitacion = (TipoHabitacion)query.getSingleResult();
		TipoHabitacionDTO hab=castFromDAOImplToDTO(tipoHabitacion);
		return hab;
		
	}
	public List<ReservaDTO> getReservas(TipoHabitacionDTO tipoHabitacion)throws DAOException{
		//TODO Este metodo falla porque he cambiado la multiplicidad de tipoHabitacion a habitaciones ademas no tiene sentido.
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT r FROM Reserva r WHERE r.tipoHabitacion = :tipoHabitacion");
		query.setParameter("tipoHabitacion", TipoHabitacionDAOImpl.castFromDTOToDAOImpl(tipoHabitacion));
		List<Reserva> reservas=query.getResultList();
		List<ReservaDTO> lista=ReservaDAOImpl.castFromDAOImplToDTO(reservas);
		return lista; 
	}
	
	public int getHabitacionesLibres(TipoHabitacionDTO tipoHabitacion,Calendar fechaIni, Calendar fechaFin) throws DAOException {
		// TODO Auto-generated method stub
		TipoHabitacion th=TipoHabitacionDAOImpl.castFromDTOToDAOImpl(tipoHabitacion);
		
		EntityManager em=FactoryDAOImpl.getEntityManager();
		String comprobarFecha="AND  ((:fechaIni BETWEEN r.fechaIni AND r.fechaFin) OR  (:fechaFin BETWEEN r.fechaIni AND r.fechaFin) OR ((:fechaIni <= r.fechaIni) AND (:fechaFin >= r.fechaFin)  ) )";
		Query query = em.createQuery("SELECT h.numHabitaciones, r.fechaIni,r.fechaFin FROM Reserva r join r.habitaciones h WHERE h.tipoHabitacion = :tipoHabitacion "+ comprobarFecha);
		
		query.setParameter("tipoHabitacion", th);
		
		query.setParameter("fechaIni", fechaIni, TemporalType.DATE);
		query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
		int suma=0;
		List<Object[]> rl = query.getResultList();
		for (Object[] object : rl) {
			object.toString();
			Calendar start=(Calendar)object[1];
			Calendar end=(Calendar)object[2];
			suma=suma+ (Integer)object[0]*tipoHabitacion.getMaxPers();
			
			System.out.println(calendarToString(start) + " " + calendarToString(end) + " " + calendarToString(fechaIni) + " " + calendarToString(fechaFin));
		}
		/*
		
		for (Integer numhab : rl) {
			suma=suma+ numhab*tipoHabitacion.getMaxPers();
		}
		
		return tipoHabitacion.getNumHabitaciones()*tipoHabitacion.getMaxPers()-suma;
		
		*/
		int plazas=tipoHabitacion.getNumHabitaciones()*tipoHabitacion.getMaxPers()-suma;
		return plazas;
	}
	public  String calendarToString(Calendar date){
		return date.get(Calendar.YEAR)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.DATE);
	}
	public void updateHabitacion(TipoHabitacionDTO tipoHabitacion) throws DAOException {
		// TODO Auto-generated method stub
		Util.merge(castFromDTOToDAOImpl(tipoHabitacion));
	}

}
