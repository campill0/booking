package dao.jpa.pojos;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OfertaDescuento
 *
 */
@Entity
public class OfertaDescuento implements Serializable {

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaIni;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaFin;
	private float porcentaje;   

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="OfertaDescuentoSequence")
	@SequenceGenerator(allocationSize=1, name = "OfertaDescuentoSequence")
	private long ofertaDescuentoId;
	private static final long serialVersionUID = 1L;

	public OfertaDescuento() {
		super();
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
	public float getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}   
	public long getOfertaDescuentoId() {
		return this.ofertaDescuentoId;
	}

	public void setOfertaDescuentoId(long ofertaDescuentoId) {
		this.ofertaDescuentoId = ofertaDescuentoId;
	}
   
}
