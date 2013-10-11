package test;

import model.CiudadDTO;
import model.TarjetaDTO;
import model.TipoTarjetaDTO;
import model.UsuarioDTO;
import dao.CiudadDAO;
import dao.DAOException;
import dao.HotelDAO;
import dao.TarjetaDAO;
import dao.UsuarioDAO;
import dao.jpa.FactoryDAOImpl;
import dao.jpa.UniqueConstraintViolationException;
import dao.jpa.pojos.Usuario;

public class UsuarioTest extends BasicoTest {
	
	@org.junit.Test
	public void Test() throws DAOException, UniqueConstraintViolationException{
		FactoryDAOImpl f=(FactoryDAOImpl) FactoryDAOImpl.loadInstance();
		UsuarioDAO usuarios=f.getUsuario();
		CiudadDAO ciudades=f.getCiudad();
		TarjetaDAO tarjetas = f.getTarjeta();
		
		CiudadDTO c= new CiudadDTO();
		c.setCodigoPostal("3223");
		c.setNombre("Terabitia");
		ciudades.insertCiudad(c);
		UsuarioDTO u= new UsuarioDTO();
		u.setNombre("Francisco");
		u.setApellidos("Campillo Asensio");
		u.setMail("taspodrio@msn.com");
		u.setPassword("123456");
		
		u.setCiudad(c);
		
		TarjetaDTO t=new TarjetaDTO();
		t.setEntidad("La caixa");
		t.setNumero("42004558738045a2");
		t.setTipo(TipoTarjetaDTO.VISA);
		tarjetas.insertTarjeta(t);
		u.getTarjetas().add(t);
		usuarios.insertUsuario(u);
		System.out.println("blaha");
		
		
	
	}
	
}
