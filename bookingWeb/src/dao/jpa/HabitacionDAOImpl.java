package dao.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.HabitacionDTO;
import model.HotelDTO;
import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.ReservaDTO;
import model.TipoHabitacionDTO;

import dao.DAOException;
import dao.HabitacionDAO;
import dao.jpa.pojos.Habitacion;
import dao.jpa.pojos.Reserva;
import dao.jpa.pojos.TipoHabitacion;


public class HabitacionDAOImpl implements HabitacionDAO {

	public long insertHabitacion(HabitacionDTO Habitacion) throws DAOException {
		// TODO Auto-generated method stub
		return ((Habitacion)Util.persist(castFromDTOToDAOImpl(Habitacion))).getHabitacionId();
		
	}

	public HabitacionDTO getHabitacion(long id) throws DAOException {
		// TODO Auto-generated method stub
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT h FROM habitacion h WHERE h.habitacionId = :id");
		query.setParameter("id", id);
		Habitacion habitacion = (Habitacion)query.getSingleResult();
		HabitacionDTO hab=castFromDAOImplToDTO(habitacion);
		return hab;
	}
	
	

	
	protected static List<HabitacionDTO> castFromDAOImplToDTO(List<Habitacion> habitacionesImpl){
		List<HabitacionDTO> habitacionesDTO=new ArrayList<HabitacionDTO>();
		for (Habitacion habitacion : habitacionesImpl) {
			HabitacionDTO h = castFromDAOImplToDTO(habitacion);
			habitacionesDTO.add(h);
		}
		return habitacionesDTO;
	}
	protected static HabitacionDTO castFromDAOImplToDTO(Habitacion habitacion) {
		HabitacionDTO h=new HabitacionDTO();
		h.setNumHabitaciones(habitacion.getNumHabitaciones());
		h.setHabitacionId(habitacion.getHabitacionId());
		h.setTipoHabitacion(TipoHabitacionDAOImpl.castFromDAOImplToDTO(habitacion.getTipoHabitacion()));
		return h;
	}
	protected static  Habitacion castFromDTOToDAOImpl(HabitacionDTO habitacion){
		Habitacion h=new Habitacion();
		h.setNumHabitaciones(habitacion.getNumHabitaciones());
		h.setHabitacionId(habitacion.getHabitacionId());
		h.setTipoHabitacion(TipoHabitacionDAOImpl.castFromDTOToDAOImpl(habitacion.getTipoHabitacion()));
		return h;
	}
	protected static List<Habitacion> castFromDTOToDAOImpl(List<HabitacionDTO> habitacionesDTO){
		List<Habitacion> habitacionesImpl=new ArrayList<Habitacion>();
		for (HabitacionDTO habitacion : habitacionesDTO) {
			habitacionesImpl.add(castFromDTOToDAOImpl(habitacion));
		}
		return habitacionesImpl;
	}
	

}
