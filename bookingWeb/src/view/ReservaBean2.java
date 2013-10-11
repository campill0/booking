package view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.DateSelectEvent;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.HabitacionDTO;
import model.HotelDTO;
import model.TipoHabitacionDTO;

public class ReservaBean2 implements Serializable {

	private static final long serialVersionUID = -5289200387895945930L;
	private Map<Long,Integer> numHabitaciones;
	private int nHabitaciones;
	private List<Long> habitaciones;
	public List<Long> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<Long> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public ReservaBean2(){
		super();
		numHabitaciones= new HashMap<Long, Integer>();
		habitaciones= new ArrayList<Long>();
		habitaciones.add(1L);
		habitaciones.add(2L);
		habitaciones.add(3L);
		for (Long hab : habitaciones) {
			numHabitaciones.put(hab,2);
		}
		
		
	}
	public Map<Long, Integer> getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(Map<Long, Integer> numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}
public int getnHabitaciones() {
		return nHabitaciones;
	}

	public void setnHabitaciones(int nHabitaciones) {
		this.nHabitaciones = nHabitaciones;
	}

	

}
