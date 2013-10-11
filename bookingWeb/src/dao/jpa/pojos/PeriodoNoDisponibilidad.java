package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: PeriodoNoDisponibilidad
 *
 */
@Entity
public class PeriodoNoDisponibilidad implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PeriodoSequence")
	@SequenceGenerator(allocationSize=1, name = "PeriodoSequence")
	private long periodoID;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaIni;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaFin;
	private String motivo;
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

	private static final long serialVersionUID = 1L;

	public PeriodoNoDisponibilidad() {
		super();
	}   
	
	
}
