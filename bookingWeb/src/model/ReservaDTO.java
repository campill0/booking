package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5637933029650150209L;
	private long reservaId;
	private Calendar fechaIni;
	private Calendar fechaFin;
	private int valoracion;
	private String peticiones;
	private String comentarios;
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	private List<HabitacionDTO> habitaciones;
	private UsuarioDTO usuario;
	private boolean cancelada;
	private HotelDTO hotel;
	public HotelDTO getHotel() {
		return hotel;
	}
	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}
	public boolean getOcupado(){
		return !fechaIni.after(Calendar.getInstance());
	}
	
	public int getPlazas(){
		int suma=0;
		for (HabitacionDTO hab : habitaciones) {
			suma=suma+(hab.getNumHabitaciones()*hab.getTipoHabitacion().getMaxPers());
		}
		return suma;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
public float getPrecio(){
	float suma=0F;
	for (HabitacionDTO hab : habitaciones) {
		suma=suma+hab.getPrecio(fechaIni,fechaFin);
	}
		return suma;
	}
	public ReservaDTO() {
		super();
		this.fechaIni = Calendar.getInstance();
		this.fechaFin = Calendar.getInstance();
		this.habitaciones= new ArrayList<HabitacionDTO>();
		this.valoracion = -1;
		}
	public Calendar getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Calendar fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Calendar getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getPeticiones() {
		return peticiones;
	}
	public void setPeticiones(String peticiones) {
		this.peticiones = peticiones;
	}
	
	public long getReservaId() {
		return reservaId;
	}
	public void setReservaId(long reservaId) {
		this.reservaId = reservaId;
	}
	public List<HabitacionDTO> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(List<HabitacionDTO> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	
	
}
