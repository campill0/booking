package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import model.UsuarioDTO;

import dao.DAOException;
import dao.UsuarioDAO;
import dao.jpa.pojos.Provincia;
import dao.jpa.pojos.Usuario;






public class UsuarioDAOImpl implements UsuarioDAO {

	public long insertUsuario(UsuarioDTO usuario) throws DAOException {
		
	
		return ((Usuario)Util.persist(castFromDTOToDAOImpl(usuario))).getUsuarioId();
		}
	
	protected static Usuario castFromDTOToDAOImpl (UsuarioDTO usuario){
	Usuario u= new Usuario();
	u.setApellidos(usuario.getApellidos());
	u.setCiudad(CiudadDAOImpl.castFromDTOToDAOImpl( usuario.getCiudad()));
	u.setMail(usuario.getMail());
	u.setBloqueado(usuario.isBloqueado());
	u.setNombre(usuario.getNombre());
	u.setPassword(usuario.getPassword());
	if(usuario.getUsuarioId()!=0){u.setUsuarioId(usuario.getUsuarioId());}
	u.setTarjetas(TarjetaDAOImpl.castFromDTOToDAOImpl( usuario.getTarjetas()));
	
	return u;
	
	}
	protected static List<UsuarioDTO> castFromDAOImplToDTO(List<Usuario> usuariosImpl){
		List<UsuarioDTO> usuariosDTO=new ArrayList<UsuarioDTO>();
		for (Usuario usuario : usuariosImpl) {
			UsuarioDTO u = castFromDAOImplToDTO(usuario);
			usuariosDTO.add(u);
		}
		return usuariosDTO;
	}
	
	protected static List<Usuario> castFromDTOToDAOImpl(List<UsuarioDTO> usuariosDTO){
		List<Usuario> usuariosImpl=new ArrayList<Usuario>();
		for (UsuarioDTO usuario : usuariosDTO) {
			Usuario u = castFromDTOToDAOImpl(usuario);
			usuariosImpl.add(u);
		}
		return usuariosImpl;
	}
	
	protected static UsuarioDTO castFromDAOImplToDTO (Usuario usuario){
		UsuarioDTO u= new UsuarioDTO();
		u.setApellidos(usuario.getApellidos());
		u.setCiudad(CiudadDAOImpl.castFromDAOImplToDTO( usuario.getCiudad()));
		u.setMail(usuario.getMail());
		u.setBloqueado(usuario.isBloqueado());
		u.setNombre(usuario.getNombre());
		u.setPassword(usuario.getPassword());
		u.setUsuarioId(usuario.getUsuarioId());
		u.setTarjetas(TarjetaDAOImpl.castFromDAOImplToDTO( usuario.getTarjetas()));
		return u;
		
	}
	
	public UsuarioDTO getUsuario(long id)  {
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarioId = :id");
		query.setParameter("id", id); 
		Object result = query.getSingleResult();
		Usuario usuario = (Usuario)result;
		UsuarioDTO hab=castFromDAOImplToDTO(usuario);
		return hab;
		
		
		
	}
	
	public UsuarioDTO getUsuario(String mail)  {
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.mail = :mail");
		query.setParameter("mail", mail); 
		Object result;
		try{
		 result = query.getSingleResult();}
		catch(Exception nre){
			return null;
		}
		Usuario usuario = (Usuario)result;
		UsuarioDTO hab=castFromDAOImplToDTO(usuario);
		return hab;
		
		
		
	}
	public void updateUsuario(UsuarioDTO usuario) throws DAOException {
		Util.merge(castFromDTOToDAOImpl(usuario));
	}

	
	

}
