package model;

import java.io.Serializable;
import java.util.Calendar;

public class OfertaDescuentoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4975941603445788123L;
	private long ofertaDescuentoId;
	private Calendar fechaIni;
	private Calendar fechaFin;
	private float porcentaje;
	
	public OfertaDescuentoDTO() {
		super();
		this.fechaFin=Calendar.getInstance();
		this.fechaIni=Calendar.getInstance();
	}
	public long getOfertaDescuentoId() {
		return ofertaDescuentoId;
	}
	public void setOfertaDescuentoId(long ofertaDescuentoId) {
		this.ofertaDescuentoId = ofertaDescuentoId;
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
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}
