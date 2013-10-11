package dao;

import model.ReservaDTO;
import model.TarjetaDTO;

public interface TarjetaDAO {
	public void insertTarjeta(TarjetaDTO tarjeta) throws DAOException;
	
}
