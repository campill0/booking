package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.CiudadDTO;
import model.HotelDTO;
import model.ProvinciaDTO;

import dao.CiudadDAO;
import dao.DAOException;
import dao.jpa.pojos.Ciudad;
import dao.jpa.pojos.Hotel;
import dao.jpa.pojos.Provincia;
import dao.jpa.pojos.Usuario;


public class CiudadDAOImpl implements CiudadDAO {

	
	
	protected static List<CiudadDTO> castFromDAOImplToDTO(List<Ciudad> ciudadesImpl){
		List<CiudadDTO> ciudadesDTO=new ArrayList<CiudadDTO>();
		for (Ciudad ciudad : ciudadesImpl) {
			CiudadDTO c = castFromDAOImplToDTO(ciudad);
			ciudadesDTO.add(c);
		}
		return ciudadesDTO;
	}
	protected static CiudadDTO castFromDAOImplToDTO(Ciudad ciudad) {
		CiudadDTO c=new CiudadDTO();
		c.setCiudadId(ciudad.getCiudadId());
		c.setCodigoPostal(ciudad.getCodigoPostal());
		c.setNombre(ciudad.getNombre());

		c.setLatitud(ciudad.getLatitud());
		c.setLongitud(ciudad.getLongitud());
	
		return c;
	}
	protected static  Ciudad castFromDTOToDAOImpl(CiudadDTO ciudad){
		Ciudad c=new Ciudad();
		c.setCiudadId(ciudad.getCiudadId());
		c.setCodigoPostal(ciudad.getCodigoPostal());
		c.setNombre(ciudad.getNombre());

		c.setLatitud(ciudad.getLatitud());
		c.setLongitud(ciudad.getLongitud());
		return c;
	}
	protected static List<Ciudad> castFromDTOToDAOImpl(List<CiudadDTO> ciudadesDTO){
		List<Ciudad> ciudadesImpl=new ArrayList<Ciudad>();
		for (CiudadDTO ciudad : ciudadesDTO) {
			ciudadesImpl.add(castFromDTOToDAOImpl(ciudad));
		}
		return ciudadesImpl;
	}
	public long insertCiudad(CiudadDTO ciudad) throws DAOException {
		// TODO Auto-generated method stub
		
		return ((Ciudad)Util.persist(castFromDTOToDAOImpl(ciudad))).getCiudadId();
		
		
	}
	
	public void updateCiudad(CiudadDTO ciudad) throws DAOException {
		// TODO Auto-generated method stub
		Util.merge(castFromDTOToDAOImpl(ciudad));
		
	}
	@Override
	public CiudadDTO getCiudad(long id) throws DAOException {
		// TODO Auto-generated method stub
		
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT c FROM Ciudad c WHERE c.ciudadId = :id");
		query.setParameter("id", id);
		Ciudad ciudad = (Ciudad)query.getSingleResult();
		CiudadDTO c=castFromDAOImplToDTO(ciudad);
		return c;

	}

	@Override
	public List findCiudadByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<CiudadDTO> lista= new ArrayList<CiudadDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombre LIKE :nombre");
		query.setParameter("nombre", "%"+name+"%").setMaxResults(20);
		List <Ciudad> rl = query.getResultList();
		for (Ciudad ciudad : rl) {
			CiudadDTO h=castFromDAOImplToDTO(ciudad);
			lista.add(h);
		}
		
		return lista;

	}
	@Override
	public List findCiudadWithHotelByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<CiudadDTO> lista= new ArrayList<CiudadDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT distinct(c) FROM Hotel h join h.ciudad c WHERE c.nombre LIKE :nombre");
		query.setParameter("nombre", "%"+name+"%");
		List <Ciudad> rl = query.getResultList();
		for (Ciudad ciudad : rl) {
			CiudadDTO h=castFromDAOImplToDTO(ciudad);
			lista.add(h);
		}
		
		return lista;
	}
	@Override
	public List findAllCiudad() throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<CiudadDTO> lista= new ArrayList<CiudadDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT c FROM Ciudad c");
		List <Ciudad> rl = query.getResultList();
		for (Ciudad ciudad : rl) {
			CiudadDTO h=castFromDAOImplToDTO(ciudad);
			lista.add(h);
		}
		
		 return lista;
	}
	@Override
	public List findAllCiudadCiudadId() throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<Long> lista= new ArrayList<Long>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT c.ciudadId FROM Ciudad c");
		 List<Long>  rl = query.getResultList();
		for (Long ciudadid : rl) {
			lista.add(ciudadid);
		}
		 return lista;
	}
	@Override
	public List findAllCiudadWithHotels() throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<CiudadDTO> lista= new ArrayList<CiudadDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT distinct(c) FROM Hotel h join h.ciudad c");
		List <Ciudad> rl = query.getResultList();
		for (Ciudad ciudad : rl) {
			CiudadDTO h=castFromDAOImplToDTO(ciudad);
			lista.add(h);
		}
		
		 return lista;
	}
	
	
		


}
