package dao.jpa;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import view.CiudadBean;

import model.CiudadDTO;
import model.HotelDTO;
import model.OfertaDescuentoDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.TipoHabitacionDTO;

import dao.DAOException;
import dao.jpa.pojos.OfertaDescuento;
import dao.jpa.pojos.PeriodoNoDisponibilidad;
import dao.jpa.pojos.TipoHabitacion;


public class Util {
	private enum Tipo {
		PERSIST, MERGE
	}
	public static void persistNewHotel(HotelDTO h) throws DAOException{
		List<PeriodoNoDisponibilidadDTO> periodosHotel = h.getPeriodosNoDisponibilidad();
 
		for (int i = 0; i < periodosHotel.size(); i++) {
			periodosHotel.set(i,  persistPeriodoDeNoDisponibilidad(periodosHotel.get(i)));
		}
		
		
		List<TipoHabitacionDTO> habitaciones=h.getHabitaciones();
		
		for (int i = 0; i < habitaciones.size(); i++) {
			TipoHabitacionDTO hab = habitaciones.get(i);
			List<PeriodoNoDisponibilidadDTO> periodosHabitacion=hab.getPeriodosDeNoDisponibilidad();
			periodosHabitacion=persistPeriodoDeNoDisponibilidad(periodosHabitacion);
			hab.setPeriodosDeNoDisponibilidad(periodosHabitacion);
			
			List<OfertaDescuentoDTO> ofertas=hab.getDescuentos();
			ofertas=persistOfertaDescuento(ofertas);
			hab.setDescuentos(ofertas);
			hab=persistTipoHabitacion(hab);
			habitaciones.set(i, hab);
			System.out.println();
		}
		h.setHabitaciones(habitaciones);
		FactoryDAOImpl.loadInstance().getHotel().insertHotel(h);
		
		
		
	}
	
public static void saveHotel(HotelDTO h) throws NumberFormatException, DAOException{
		
		
		
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
		
	}
	
	public static List<PeriodoNoDisponibilidadDTO> persistPeriodoDeNoDisponibilidad(List<PeriodoNoDisponibilidadDTO> pndl) throws DAOException{
		
		for (int i = 0; i < pndl.size(); i++) {
			PeriodoNoDisponibilidadDTO pnd = persistPeriodoDeNoDisponibilidad(pndl.get(i));
			pndl.set(i, pnd);
			System.out.println();
		}
			
		
		return pndl;
	}
public static List<OfertaDescuentoDTO> persistOfertaDescuento(List<OfertaDescuentoDTO> ofertas) throws DAOException{
		
		for (int i = 0; i < ofertas.size(); i++) {
			OfertaDescuentoDTO pnd = persistOfertaDescuento(ofertas.get(i));
			ofertas.set(i, pnd);
			System.out.println();
		}
			
		
		return ofertas;
	}
	
	public static OfertaDescuentoDTO persistOfertaDescuento(OfertaDescuentoDTO oferta) throws DAOException
	{
		OfertaDescuento oferta2=OfertaDescuentoDAOImpl.castFromDTOToDAOImpl(oferta);
		persist(oferta2);
		return OfertaDescuentoDAOImpl.castFromDAOImplToDTO(oferta2);
		
	}
	public static TipoHabitacionDTO persistTipoHabitacion(TipoHabitacionDTO tipoHab) throws DAOException
	{
		TipoHabitacion tipoHab2=TipoHabitacionDAOImpl.castFromDTOToDAOImpl(tipoHab);
		persist(tipoHab2);
		return TipoHabitacionDAOImpl.castFromDAOImplToDTO(tipoHab2);
		
	}
	public static PeriodoNoDisponibilidadDTO persistPeriodoDeNoDisponibilidad(PeriodoNoDisponibilidadDTO pnd) throws DAOException{
		PeriodoNoDisponibilidad pnd2=PeriodoNoDisponibilidadDAOImpl.castFromDTOToDAOImpl(pnd);
		persist(pnd2);
		return PeriodoNoDisponibilidadDAOImpl.castFromDAOImplToDTO(pnd2);
		
	}
	
	public static Object persist(Object ob) throws DAOException {
		return persistGeneric(ob, Tipo.PERSIST);
	}

	public static void merge(Object ob) throws DAOException {
		persistGeneric(ob, Tipo.MERGE);
	}

	private static Object persistGeneric(Object ob, Tipo tipo)
			throws DAOException {
		EntityManager em = FactoryDAOImpl.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			switch (tipo) {
			case MERGE:
				em.merge(ob); // Se guarda en la base de datos pero si ya existe se actualiza.
				break;

			case PERSIST:
				em.persist(ob); // Se guarda en la base de datos si ya existe se crea otro.
				break;
			default:
				break;
			}

			tx.commit();
		} catch (Exception e) {
			
			
			tx.rollback();
			em.close();
			
		//	throw new DAOException( 		"Hubo un problema en el update de " + ob.getClass().getCanonicalName());
		}
		em.close();
		return ob;
	}
	public static void updateQuery(String queryStr) throws DAOException {
		// TODO Auto-generated method stub
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery(queryStr);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		query.executeUpdate();
		
		try {
			
						tx.commit();
		} catch (Exception e) {
			
			
			tx.rollback();
			em.close();
			
			throw new DAOException( 		"Hubo un problema en el delete/update" );
		}
		em.close();
		
	}
}
