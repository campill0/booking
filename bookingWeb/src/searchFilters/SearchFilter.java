package searchFilters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.HabitacionDTO;
import model.HotelDTO;
import model.ReservaDTO;
import model.TipoHabitacionDTO;

public class SearchFilter {
public boolean entreFechas(Calendar startDate1,Calendar endDate1,Calendar startDate2,Calendar endDate2){return true;}
public Map<TipoHabitacionDTO, Integer> reservasEntreFecha(HotelDTO h,Calendar startDate,Calendar endDate,int numeroPersonas) throws DAOException{
	Map<TipoHabitacionDTO, Integer> habitacionesRestantes = new HashMap<TipoHabitacionDTO, Integer>();
	//FactoryDAOImpl.loadInstance().getTipoHabitacion().
	h.getHabitaciones();
	
	return habitacionesRestantes;
	
}



}
