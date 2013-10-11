package dao.jpa.pojos;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Habitacion
 *
 */
@Entity

public class Habitacion implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HabitacionSequence")
	@SequenceGenerator(allocationSize=1, name = "HabitacionSequence")
	private long habitacionId;
	@ManyToOne
	private TipoHabitacion tipoHabitacion;
	private int numHabitaciones;
	private static final long serialVersionUID = 1L;

	public Habitacion() {
		super();
	}   
	public long getHabitacionId() {
		return this.habitacionId;
	}

	public void setHabitacionId(long habitacionId) {
		this.habitacionId = habitacionId;
	}
	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}
	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	public int getNumHabitaciones() {
		return numHabitaciones;
	}
	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}
	
	
   
}
