package dao;

import java.util.List;

import model.HotelDTO;
import model.ReservaDTO;
import model.UsuarioDTO;


public interface ReservaDAO {
	public long insertReserva(ReservaDTO reserva) throws DAOException;
	public ReservaDTO getReserva(long id) throws DAOException ;
	public List<ReservaDTO> findReservaByHotel(HotelDTO h) throws DAOException ;
	public List<ReservaDTO> findReservaByUser(UsuarioDTO u) throws DAOException;
	public void updateReserva(ReservaDTO reserva) throws DAOException;
}
