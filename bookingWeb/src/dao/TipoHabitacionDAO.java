package dao;

import java.util.Calendar;
import java.util.List;

import model.CiudadDTO;
import model.ReservaDTO;
import model.TipoHabitacionDTO;


public interface TipoHabitacionDAO {
	   public long insertTipoHabitacion(TipoHabitacionDTO tipoHabitacion) throws DAOException;
	   public TipoHabitacionDTO getTipoHabitacion(long id) throws DAOException ;
	   public List<ReservaDTO> getReservas(TipoHabitacionDTO tipoHabitacion)throws DAOException;
	   public int getHabitacionesLibres(TipoHabitacionDTO th,Calendar fechaIni, Calendar fechaFin) throws DAOException;
	   public void updateHabitacion(TipoHabitacionDTO tipoHabitacion) throws DAOException ;
}
