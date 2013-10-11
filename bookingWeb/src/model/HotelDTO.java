package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;

import dao.jpa.FactoryDAOImpl;

public class HotelDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6751422316006254222L;
	/**
	 * 
	 */
	
	private long id;
	private String nombre;
	private int estrellas;
	private String descripcion;
	private String direccion;
	private CiudadDTO ciudad;
	private List<TipoHabitacionDTO> habitaciones;
	
	private List<PeriodoNoDisponibilidadDTO> periodosNoDisponibilidad;

	public HotelDTO() {
		super();
		
		this.periodosNoDisponibilidad = new ArrayList<PeriodoNoDisponibilidadDTO>();
		this.habitaciones = new ArrayList<TipoHabitacionDTO>();
		

	}

	
	

	public String toString() {
		return this.nombre;
	}

	public void addPeriodoNoDisponibilidadDTO(){
		
		periodosNoDisponibilidad.add(new PeriodoNoDisponibilidadDTO());
		System.out.println("a");
		}
	public void removePeriodoNoDisponibilidadDTO(PeriodoNoDisponibilidadDTO periodo){
		periodosNoDisponibilidad.remove(periodo);
		}
		
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public CiudadDTO getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}

	public List<TipoHabitacionDTO> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<TipoHabitacionDTO> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public void addTipoHabitacion(){
		
		habitaciones.add(new TipoHabitacionDTO());
	}
	public void deleteTipoHabitacion(TipoHabitacionDTO thab){
		
		habitaciones.remove(thab);
	}

	public List<PeriodoNoDisponibilidadDTO> getPeriodosNoDisponibilidad() {
		return periodosNoDisponibilidad;
	}

	public void setPeriodosNoDisponibilidad(
			List<PeriodoNoDisponibilidadDTO> periodosNoDisponibilidad) {
		this.periodosNoDisponibilidad = periodosNoDisponibilidad;
	}

}
