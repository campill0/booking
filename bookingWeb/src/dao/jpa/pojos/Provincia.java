package dao.jpa.pojos;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Provincia
 *
 */
@Entity

public class Provincia implements Serializable {

	
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ProvinciaSequence")
	@SequenceGenerator(allocationSize=1, name = "ProvinciaSequence")*/
	@Id
	private long provinciaId;
	@Column(unique=true)
	private String nombre;
	@OneToMany (cascade= CascadeType.MERGE)
	private List<Ciudad> ciudades;
	public List<Ciudad> getCiudades() {
		return ciudades;
	}
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	private static final long serialVersionUID = 1L;

	public Provincia() {
		super();
	}    
	public long getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(long provinciaId) {
		this.provinciaId = provinciaId;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   
}
