package model;

import java.io.Serializable;



public class CiudadDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7333701346833045534L;
	private long ciudadId;
	private String nombre;
	private String  codigoPostal;
	private float latitud;
	private float longitud;
	


	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(long ciudadId) {
		this.ciudadId = ciudadId;
	}
	
	public String toString() {
		return nombre + "(" + codigoPostal+ ") tostring";
	}
@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CiudadDTO)) {
		return false;
		}
		CiudadDTO ciudad = (CiudadDTO) obj;

		return (this.ciudadId == ciudad.ciudadId);

		}

}
