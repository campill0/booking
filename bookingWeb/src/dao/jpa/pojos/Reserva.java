package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import model.HabitacionDTO;
import model.TipoHabitacionDTO;




/**
 * Entity implementation class for Entity: Reserva
 *
 */
@Entity  
public class Reserva implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ReservaSequence")
	@SequenceGenerator(allocationSize=1, name = "ReservaSequence")
	private long reservaId;
	private String comentarios;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaIni;
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaFin;
	private int valoracion;
	@ManyToOne
	private Hotel hotel;
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	private boolean cancelada;
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	private String peticiones;
	//@ManyToOne
	//@Transient
	@ManyToMany
	private List<Habitacion> habitaciones;
	@ManyToOne
	private Usuario usuario;
	private static final long serialVersionUID = 1L;

	public Reserva() {
		super();
	}   
	public long getReservaId() {
		return this.reservaId;
	}

	public void setReservaId(long reservaId) {
		this.reservaId = reservaId;
	}   
	public Calendar getFechaIni() {
		return this.fechaIni;
	}

	public void setFechaIni(Calendar fechaIni) {
		this.fechaIni = fechaIni;
	}   
	public Calendar getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}   

	public int getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}   
	public String getPeticiones() {
		return this.peticiones;
	}

	public void setPeticiones(String peticiones) {
		this.peticiones = peticiones;
	}   

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
   
}
