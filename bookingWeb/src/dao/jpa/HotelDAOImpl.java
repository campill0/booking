package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.*;

import dao.DAOException;
import dao.HotelDAO;
import dao.jpa.pojos.Ciudad;
import dao.jpa.pojos.Hotel;
import dao.jpa.pojos.Provincia;
import dao.jpa.pojos.TipoHabitacion;

public class HotelDAOImpl<T> implements HotelDAO{

@Override
public List findHotelByState(ProvinciaDTO hotelState) throws DAOException {
	// TODO Auto-generated method stub
	List<HotelDTO> lista= new ArrayList<HotelDTO>();
	List<CiudadDTO>ciudades = hotelState.getCiudades();
	List cities=new ArrayList();
	for (CiudadDTO c : ciudades) {
		cities.add(c.getCiudadId());
	}
	
	
	EntityManager em=FactoryDAOImpl.getEntityManager();
	
	
	Query query = em.createQuery("SELECT h FROM Hotel h WHERE h.ciudad.ciudadId IN :cities");
	query.setParameter("cities",cities);
	List <Hotel> rl = query.getResultList();
	for (Hotel hotel : rl) {
		HotelDTO h=castFromDAOImplToDTO(hotel);
		lista.add(h);
	}
	
	return lista;
	
}

@Override
public List findHotelByCity(CiudadDTO city) throws DAOException {
	// TODO Auto-generated method stub
	
	ArrayList<HotelDTO> lista= new ArrayList<HotelDTO>();
	Ciudad c = CiudadDAOImpl.castFromDTOToDAOImpl(city);
	EntityManager em=FactoryDAOImpl.getEntityManager();
	Query query = em.createQuery("SELECT h FROM Hotel h WHERE h.ciudad = :city");
	query.setParameter("city",c );
	List <Hotel> rl = query.getResultList();
	for (Hotel hotel : rl) {
		HotelDTO h=castFromDAOImplToDTO(hotel);
		lista.add(h);
	}
	
	return lista;
	
	
}


@Override
	public List findHotelByName(String hotelName) throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<HotelDTO> lista= new ArrayList<HotelDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT h FROM Hotel h WHERE h.nombre LIKE :nombre");
		query.setParameter("nombre", "%"+hotelName+"%");
		List <Hotel> rl = query.getResultList();
		for (Hotel hotel : rl) {
			HotelDTO h=castFromDAOImplToDTO(hotel);
			lista.add(h);
		}
		
		return lista;	
		
	}

	public List  findAllHotel() throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<HotelDTO> lista= new ArrayList<HotelDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT h FROM Hotel h");
		List <Hotel> rl = query.getResultList();
		for (Hotel hotel : rl) {
			HotelDTO h=castFromDAOImplToDTO(hotel);
			lista.add(h);
		}
		
		 return lista;
	}
	
	public long insertHotel(HotelDTO hotel) throws DAOException {
		// TODO Auto-generated method stub
		return ((Hotel)Util.persist(castFromDTOToDAOImpl(hotel))).getHotelId();
		}

	

	public void updateHotel(HotelDTO hotel) throws DAOException {
		// TODO Auto-generated method stub
		Util.merge(castFromDTOToDAOImpl(hotel));
	}

	public void deleteHotel(HotelDTO hotel) throws DAOException {
		// TODO Auto-generated method stub
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Hotel h = em.find(Hotel.class, hotel.getId());
		em.getTransaction().begin();
		em.remove(h);
		em.getTransaction().commit();
		
	}
	
	

	public HotelDTO getHotel(long id) throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<HotelDTO> lista= new ArrayList<HotelDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT h FROM Hotel h WHERE h.hotelId = :id");
		query.setParameter("id", id);
		Hotel hotel = (Hotel)query.getSingleResult();
		HotelDTO h=castFromDAOImplToDTO(hotel);
		return h;
		
	}
	

protected static HotelDTO castFromDAOImplToDTO(Hotel hotel){
	HotelDTO h=new HotelDTO();
	h.setDescripcion(hotel.getDescripcion());
	h.setDireccion(hotel.getDireccion());
	h.setEstrellas(hotel.getEstrellas());
	h.setNombre(hotel.getNombre());
	h.setId(hotel.getHotelId());
	h.setPeriodosNoDisponibilidad(PeriodoNoDisponibilidadDAOImpl.castFromDAOImplToDTO(hotel.getPeriodosND()) );
	h.setCiudad(CiudadDAOImpl.castFromDAOImplToDTO(hotel.getCiudad()) );
	List<TipoHabitacion> habitaciones=hotel.getHabitaciones();
	h.setHabitaciones(TipoHabitacionDAOImpl.castFromDAOImplToDTO(hotel.getHabitaciones()));
	return h;

}
protected static Hotel castFromDTOToDAOImpl(HotelDTO hotel){


	Hotel h=new Hotel();
	h.setDescripcion(hotel.getDescripcion());
	h.setDireccion(hotel.getDireccion());
	h.setEstrellas(hotel.getEstrellas());
	h.setNombre(hotel.getNombre());
	h.setHotelId(hotel.getId());
	h.setCiudad(CiudadDAOImpl.castFromDTOToDAOImpl(hotel.getCiudad()));
	h.setPeriodosND(PeriodoNoDisponibilidadDAOImpl.castFromDTOToDAOImpl(hotel.getPeriodosNoDisponibilidad()));
	h.setHabitaciones(TipoHabitacionDAOImpl.castFromDTOToDAOImpl((hotel.getHabitaciones())));
	return h;

}

protected static List<Hotel> castFromDTOToDAOImpl (List <HotelDTO> hoteles){
	List<Hotel> hotelLista= new ArrayList<Hotel>();
	
	for (HotelDTO hotelDTO : hoteles) {
		hotelLista.add(castFromDTOToDAOImpl(hotelDTO));
	}
	return hotelLista;
}

protected static List<HotelDTO> castFromDAOImplToDTO (List <Hotel> hoteles){
	List<HotelDTO> hotelLista= new ArrayList<HotelDTO>();
	
	for (Hotel hotel : hoteles) {
		hotelLista.add(castFromDAOImplToDTO(hotel));
	}
	return hotelLista;
}

@Override
public int findNumHotelsByCity(CiudadDTO city) throws DAOException {
	// TODO Auto-generated method stub
	return findHotelByCity(city).size();
}


	
}
