package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Habitacion
 *
 */
@Entity
public class TipoHabitacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TipoHabitacionSequence")
	@SequenceGenerator(allocationSize=1, name = "TipoHabitacionSequence")
	private long tipoHabitacionId;
	private String categoria;
	private int maxPers;
	private float precio;
	private int numHabitaciones;
	@OneToMany
	private List<PeriodoNoDisponibilidad> periodosND;
	@OneToMany
	private List<OfertaDescuento> descuentos;
	
	
	private static final long serialVersionUID = 1L;

	public TipoHabitacion() {
		super();
	}   

	public long getTipoHabitacionId() {
		return tipoHabitacionId;
	}

	public void setTipoHabitacionId(long tipoHabitacionId) {
		this.tipoHabitacionId = tipoHabitacionId;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}   
	public int getMaxPers() {
		return this.maxPers;
	}

	public void setMaxPers(int maxPers) {
		this.maxPers = maxPers;
	}   
	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}   
	public int getNumHabitaciones() {
		return this.numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}   
	public List<PeriodoNoDisponibilidad> getPeriodosND() {
		return this.periodosND;
	}

	public void setPeriodosND(List<PeriodoNoDisponibilidad> periodosND) {
		this.periodosND = periodosND;
	}   
	public List<OfertaDescuento> getDescuentos() {
		return this.descuentos;
	}

	public void setDescuentos(List<OfertaDescuento> descuentos) {
		this.descuentos = descuentos;
	}
   
}
