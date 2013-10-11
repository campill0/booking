package model;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="Bean")
@ViewScoped
public class HabitacionDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1546425648209913490L;
	private long HabitacionId;
	private int numHabitaciones;
	private TipoHabitacionDTO tipoHabitacion;
	public HabitacionDTO() {
		super();
		
	}
	public long getHabitacionId() {
		return HabitacionId;
	}
	public void setHabitacionId(long habitacionId) {
		HabitacionId = habitacionId;
	}
	public int getNumHabitaciones() {
		return numHabitaciones;
	}
	public float getPrecio(Calendar startDate,Calendar endDate){
		
		return numHabitaciones*tipoHabitacion.getPrecio(startDate,endDate);
	}
	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}
	public TipoHabitacionDTO getTipoHabitacion() {
		return tipoHabitacion;
	}
	public void setTipoHabitacion(TipoHabitacionDTO tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	

}
