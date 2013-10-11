package dao;


import java.util.Calendar;
import java.util.List;


import model.HabitacionDTO;
import model.ReservaDTO;
import model.TipoHabitacionDTO;

public interface HabitacionDAO {

	public long insertHabitacion(HabitacionDTO Habitacion) throws DAOException;
	public HabitacionDTO getHabitacion(long id) throws DAOException ;
	 
}
