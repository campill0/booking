package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import model.CiudadDTO;


/**
 * Entity implementation class for Entity: Ciudad
 *
 */
@Entity 
public class Ciudad implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Ciudad Sequence")
	@SequenceGenerator(allocationSize=1, name = "Ciudad Sequence")
	private long ciudadId;
	private String codigoPostal;
	private String nombre;
	private float latitud;
	private float longitud;
	private static final long serialVersionUID = 1L;
	
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float f) {
		this.longitud = f;
	}
	public long getCiudadId() {
		return ciudadId;
	}
	public void setCiudadId(long ciudadId) {
		this.ciudadId = ciudadId;
	}


	public Ciudad() {
		super();
	}   
	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   
}
