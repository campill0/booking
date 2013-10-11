package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.CiudadDTO;
import model.ProvinciaDTO;
import model.ProvinciaDTO;
import model.ReservaDTO;
import model.TipoHabitacionDTO;

import dao.DAOException;
import dao.ProvinciaDAO;
import dao.jpa.CiudadDAOImpl;
import dao.jpa.pojos.Ciudad;
import dao.jpa.pojos.Provincia;
import dao.jpa.pojos.OfertaDescuento;
import dao.jpa.pojos.Provincia;
import dao.jpa.pojos.Reserva;

public class ProvinciaDAOImpl implements ProvinciaDAO {
	
	public List findAllProvincia() throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<ProvinciaDTO> lista= new ArrayList<ProvinciaDTO>();
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Provincia p");
		List <Provincia> rl = query.getResultList();
		for (Provincia provincia : rl) {
			ProvinciaDTO p=castFromDAOImplToDTO(provincia);
			lista.add(p);
		}
		
		 return lista;
	}

	public List<CiudadDTO> getCiudades(ProvinciaDTO provincia)throws DAOException{
		//TODO Creo que no sirve para nada
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT c FROM Ciudad c WHERE c. = :tipoHabitacion");
		query.setParameter("tipoHabitacion", ProvinciaDAOImpl.castFromDTOToDAOImpl(provincia));
		List<Ciudad> ciudades=query.getResultList();
		List<CiudadDTO> lista=CiudadDAOImpl.castFromDAOImplToDTO(ciudades);
		return lista;
	}
	
	public long insertProvincia(ProvinciaDTO provincia) throws DAOException {
		// TODO Auto-generated method stub
		
		return ((Provincia)Util.persist(castFromDTOToDAOImpl(provincia))).getProvinciaId();
	}
	
	public void updateProvincia(ProvinciaDTO provincia) throws DAOException {
		// TODO Auto-generated method stub
		
		Util.merge(castFromDTOToDAOImpl(provincia));
	}

protected static Provincia castFromDTOToDAOImpl (ProvinciaDTO provincia){
	Provincia p=new Provincia();
	p.setNombre(provincia.getNombre());
	p.setProvinciaId(provincia.getId());
	p.setCiudades(CiudadDAOImpl.castFromDTOToDAOImpl(provincia.getCiudades()));
	return p;
}
protected static ProvinciaDTO castFromDAOImplToDTO (Provincia provincia){
	ProvinciaDTO p=new ProvinciaDTO();
	p.setNombre(provincia.getNombre());
	p.setId(provincia.getProvinciaId());
	p.setCiudades(CiudadDAOImpl.castFromDAOImplToDTO(provincia.getCiudades()));
	return p;
}
protected static List<Provincia> castFromDTOToDAOImpl (List<ProvinciaDTO> provincias){
	List<Provincia> provinciaLista = new ArrayList<Provincia>();
	
	for (ProvinciaDTO provinciaDTO : provincias) {
	provinciaLista.add(castFromDTOToDAOImpl(provinciaDTO));	
	}
	return provinciaLista;
}
protected static List<ProvinciaDTO> castFromDAOImplToDTO (List<Provincia> provincias){
	List<ProvinciaDTO>  provinciaLista= new ArrayList<ProvinciaDTO>();

	for (Provincia provincia : provincias) {
	provinciaLista.add(castFromDAOImplToDTO(provincia));
}
	return provinciaLista;
}

@Override
public ProvinciaDTO getProvincia(long id) throws DAOException {
	// TODO Auto-generated method stub
	
	EntityManager em=FactoryDAOImpl.getEntityManager();
	Query query = em.createQuery("SELECT p FROM Provincia p WHERE p.provinciaId = :id");
	query.setParameter("id", id);
	Provincia Provincia = (Provincia)query.getSingleResult();
	ProvinciaDTO p=castFromDAOImplToDTO(Provincia);
	return p;

}

@Override
public List findProvinciaByName(String name) throws DAOException {
	// TODO Auto-generated method stub
	ArrayList<ProvinciaDTO> lista= new ArrayList<ProvinciaDTO>();
	EntityManager em=FactoryDAOImpl.getEntityManager();
	Query query = em.createQuery("SELECT p FROM Provincia p WHERE p.nombre LIKE :nombre");
	query.setParameter("nombre", "%"+name+"%");
	List <Provincia> rl = query.getResultList();
	for (Provincia provincia : rl) {
		ProvinciaDTO p=castFromDAOImplToDTO(provincia);
		lista.add(p);
	}
	
	return lista;

}

@Override
public ProvinciaDTO getProvinciabyCity(CiudadDTO city) throws DAOException {
	// TODO Auto-generated method stub
	EntityManager em=FactoryDAOImpl.getEntityManager();
	Query query = em.createQuery("SELECT p FROM Provincia p join p.ciudades c WHERE c.ciudadId = :id");
	query.setParameter("id", city.getCiudadId());
	Object var = query.getSingleResult();
	Provincia Provincia = (Provincia)var;
	ProvinciaDTO p=castFromDAOImplToDTO(Provincia);
	return p;
}
}
