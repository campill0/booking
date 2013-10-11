package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import model.CiudadDTO;
import model.HabitacionDTO;
import model.TipoHabitacionDTO;

import dao.jpa.pojos.Ciudad;



/**
 * Entity implementation class for Entity: Hotel
 *
 */
@Entity
public class Hotel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HotelSequence")
	@SequenceGenerator(allocationSize=1, name = "HotelSequence")
	private long hotelId;
	private String nombre;
	private String descripcion;
	private String direccion;
	@OneToMany
	private List<TipoHabitacion> habitaciones;
	private int estrellas;
	@OneToMany
	private List <PeriodoNoDisponibilidad> periodosND;
	@ManyToOne
	private Ciudad ciudad;
	private static final long serialVersionUID = 1L;
	
	public Hotel() {
		super();
	}   
	
	public List<TipoHabitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<TipoHabitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}


	public List<PeriodoNoDisponibilidad> getPeriodosND() {
		return periodosND;
	}

	public void setPeriodosND(List<PeriodoNoDisponibilidad> periodosND) {
		this.periodosND = periodosND;
	}

	
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


 
	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}   
	public int getEstrellas() {
		return this.estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
   
}
