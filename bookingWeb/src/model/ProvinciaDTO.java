package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7759937368188170300L;
	private long id;
	private String nombre;
	private List<CiudadDTO> ciudades;
	
	
	
	public ProvinciaDTO() {
		super();
		this.ciudades= new ArrayList<CiudadDTO>();
	}

	public String toString(){
        return this.getNombre();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CiudadDTO> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<CiudadDTO> ciudades) {
		this.ciudades = ciudades;
	}
	
	  public void handleProvinciaChange() {  
		  ciudades=getCiudades();
	    }  
	 
	
}
