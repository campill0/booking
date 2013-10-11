package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import model.TipoTarjetaDTO;


/**
 * Entity implementation class for Entity: Tarjeta
 *
 */
@Entity

public class Tarjeta implements Serializable {

	   
	@Id
	private String numero;
	@Enumerated(EnumType.STRING)
	private TipoTarjetaDTO tipo;
	private String entidad;
	private static final long serialVersionUID = 1L;

	public Tarjeta() {
		super();
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}   
	public TipoTarjetaDTO getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoTarjetaDTO tipo) {
		this.tipo = tipo;
	}   
	public String getEntidad() {
		return this.entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
   
}
