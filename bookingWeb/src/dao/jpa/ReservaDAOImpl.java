package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.HotelDTO;
import model.ReservaDTO;
import model.UsuarioDTO;

import dao.DAOException;
import dao.ReservaDAO;
import dao.jpa.pojos.Provincia;
import dao.jpa.pojos.Reserva;
import dao.jpa.pojos.Usuario;

public class ReservaDAOImpl implements ReservaDAO {

	public long insertReserva(ReservaDTO reserva) throws DAOException {
		// TODO Auto-generated method stub
		return ((Reserva)Util.persist(castFromDTOToDAOImpl(reserva))).getReservaId();
	}
	public void updateReserva(ReservaDTO reserva) throws DAOException {
		// TODO Auto-generated method stub
		Util.merge(castFromDTOToDAOImpl(reserva));
	}
	protected static Reserva castFromDTOToDAOImpl (ReservaDTO reserva){
		Reserva r= new Reserva();
		r.setComentarios(reserva.getComentarios());
		r.setReservaId(reserva.getReservaId());
		r.setFechaFin(reserva.getFechaFin());
		r.setFechaIni(reserva.getFechaIni());
		r.setHabitaciones(HabitacionDAOImpl.castFromDTOToDAOImpl(reserva.getHabitaciones()));
		r.setPeticiones(reserva.getPeticiones());
		r.setValoracion(reserva.getValoracion());
		r.setUsuario( UsuarioDAOImpl.castFromDTOToDAOImpl(reserva.getUsuario()));
		r.setCancelada(reserva.isCancelada());
		r.setHotel(HotelDAOImpl.castFromDTOToDAOImpl(reserva.getHotel()));
		return r;
		
	}
	
protected static ReservaDTO castFromDAOImplToDTO (Reserva reserva){
	
	ReservaDTO r= new ReservaDTO();
	r.setComentarios(reserva.getComentarios());
	r.setReservaId(reserva.getReservaId());
	r.setFechaFin(reserva.getFechaFin());
	r.setFechaIni(reserva.getFechaIni());
	r.setHabitaciones(HabitacionDAOImpl.castFromDAOImplToDTO(reserva.getHabitaciones()));
	r.setPeticiones(reserva.getPeticiones());
	r.setValoracion(reserva.getValoracion());
	r.setUsuario(UsuarioDAOImpl.castFromDAOImplToDTO(reserva.getUsuario()));
	r.setCancelada(reserva.isCancelada());
	r.setHotel(HotelDAOImpl.castFromDAOImplToDTO(reserva.getHotel()));
	return r;
	

}

protected static List<Reserva> castFromDTOToDAOImpl(List<ReservaDTO> reservasDTO){
	List<Reserva> reservasImpl=new ArrayList<Reserva>();
	for (ReservaDTO reserva : reservasDTO) {
		reservasImpl.add(castFromDTOToDAOImpl(reserva));
	}
	return reservasImpl;
}

protected static List<ReservaDTO> castFromDAOImplToDTO(List<Reserva> reservas){
	List<ReservaDTO> reservasDTO=new ArrayList<ReservaDTO>();
	for (Reserva reserva : reservas) {
		reservasDTO.add(castFromDAOImplToDTO(reserva));
	}
	return reservasDTO;
}

public ReservaDTO getReserva(long id) throws DAOException {
	// TODO Auto-generated method stub
	EntityManager em=FactoryDAOImpl.getEntityManager();
	Query query = em.createQuery("SELECT r FROM Reserva r WHERE r.reservaId = :id");
	query.setParameter("id", id);
	Reserva reserva = (Reserva)query.getSingleResult();
	ReservaDTO r=castFromDAOImplToDTO(reserva);
	
	
	return r;
	
}

public List<ReservaDTO> findReservaByHotel(HotelDTO h) throws DAOException {
	// TODO Auto-generated method stub
	List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
	EntityManager em=FactoryDAOImpl.getEntityManager();
	Query query = em.createQuery("SELECT r FROM Reserva r WHERE r.hotel = :hotel");
	query.setParameter("hotel", h);
	List <Reserva> rl = query.getResultList();
	for (Reserva reserva : rl) {
		reservas.add(castFromDAOImplToDTO(reserva));
		
	}
	
	return reservas;
	
}
public List<ReservaDTO> findReservaByUser(UsuarioDTO u) throws DAOException {
	// TODO Auto-generated method stub
	Usuario usuario=UsuarioDAOImpl.castFromDTOToDAOImpl(u);
	List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
	EntityManager em=FactoryDAOImpl.getEntityManager();
	Query query = em.createQuery("SELECT r FROM Reserva r WHERE r.usuario = :usuario");
	query.setParameter("usuario", usuario);
	List <Reserva> rl = query.getResultList();
	for (Reserva reserva : rl) {
		reservas.add(castFromDAOImplToDTO(reserva));
		
	}
	
	return reservas;
	
}



}
