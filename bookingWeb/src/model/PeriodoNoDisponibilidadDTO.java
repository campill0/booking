package model;

import java.io.Serializable;
import java.util.Calendar;

public class PeriodoNoDisponibilidadDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6683831154634756184L;
	private long periodoID;
	private Calendar fechaIni;
	private Calendar fechaFin;
	private String motivo;
	public PeriodoNoDisponibilidadDTO() {
		super();
		this.fechaFin=Calendar.getInstance();
		this.fechaIni=Calendar.getInstance();
	}
	public long getPeriodoID() {
		return periodoID;
	}
	public void setPeriodoID(long periodoID) {
		this.periodoID = periodoID;
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
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
	
}
