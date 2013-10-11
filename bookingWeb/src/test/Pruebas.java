package test;

import model.CiudadDTO;
import dao.jpa.pojos.Ciudad;

public class Pruebas {
	
	public static Ciudad castToCiudad(CiudadDTO ciudad){
		Ciudad c= new Ciudad();
		c.setCodigoPostal(ciudad.getCodigoPostal());
		c.setNombre(ciudad.getNombre());
		
		return c;
	}
	
	public static void main(String[] args) {
		CiudadDTO c1= new CiudadDTO();
		c1.setCodigoPostal("asad");
		c1.setNombre("abaran");
		
		Ciudad c2= new Ciudad();
		c2.setCodigoPostal("sad");
		c2.setNombre("asda");
		
		//CiudadDTO c3 = (CiudadDTO) c2;
		Ciudad c4=castToCiudad(c1);
		
		CiudadDTO c5=(CiudadDTO)c1;
		System.out.println("Blahaha");
		
		
	}
	
	

}
