package dao;

import model.OfertaDescuentoDTO;
import model.TipoHabitacionDTO;

public interface OfertaDescuentoDAO {
	public long insertOfertaDescuento(OfertaDescuentoDTO ofertaDescuento) throws DAOException;
	public void updateOfertaDescuento(OfertaDescuentoDTO ofertaDescuento) throws DAOException ;
}
