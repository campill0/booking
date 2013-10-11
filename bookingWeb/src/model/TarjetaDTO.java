package model;

import java.io.Serializable;

public class TarjetaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5958467811049432482L;
	private String numero;
	private TipoTarjetaDTO tipo;
	private String entidad;
	public TarjetaDTO() {
		super();
		
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public TipoTarjetaDTO getTipo() {
		return tipo;
	}
	public void setTipo(TipoTarjetaDTO tipo) {
		this.tipo = tipo;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	
	

}
