package dao;

import dao.jpa.UniqueConstraintViolationException;
import model.ReservaDTO;
import model.UsuarioDTO;

public interface UsuarioDAO {
	public long insertUsuario(UsuarioDTO usuario) throws DAOException;
	public UsuarioDTO getUsuario(long id) throws DAOException;
	public void updateUsuario(UsuarioDTO u) throws DAOException;
	public UsuarioDTO getUsuario(String mail) throws DAOException;
	
}
