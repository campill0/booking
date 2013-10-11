package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.persistence.EntityTransaction;

import model.HotelDTO;

import org.junit.Ignore;
import org.junit.Test;

import dao.DAOException;
import dao.jpa.HotelDAOImpl;


public class HotelTest extends BasicoTest {
	protected HotelDAOImpl hoteles;
	protected ArrayList<HotelDTO> listaHoteles;
	protected ArrayList<HotelDTO> listaHoteles2;
	protected HotelDTO h1;
	protected String descripcionHotel="Un hotel muy bueno";
	protected String nombreHotel="Hotel silken 7 coronas";
	@Test
	public void setUp() {
	
	}
	@Test
	public void testInsertHotel() throws DAOException{
		/*hoteles=new HotelDAOImpl();
		listaHoteles=new ArrayList<HotelDTO>();
		listaHoteles2=new ArrayList<HotelDTO>();
		h1=new HotelDTO();
		h1.setDescripcion(descripcionHotel);
		h1.setDireccion("Una direccion");
		h1.setEstrellas(3);
		h1.setNombre(nombreHotel);
		listaHoteles = hoteles.findAllHotel();
		int tamInicial=listaHoteles.size();
		long hotelId = hoteles.insertHotel(h1);
		listaHoteles = hoteles.findAllHotel();
		//test1 el tamaño de la lista debe haber crecido en 1
		assertEquals(listaHoteles.size(),tamInicial+1);
		HotelDTO h2 = hoteles.getHotel(hotelId);
		// test2 el nombre del hotel que obtenemos debe coincidir con el que introducimos antes  
		assertEquals(h2.getNombre(), nombreHotel);
		hoteles.deleteHotel(h2);
		listaHoteles=hoteles.findAllHotel();
		assertEquals(listaHoteles.size(),tamInicial);
		
		
		*/
	}
	
	
	@Test
	public void testUpdateHotel() throws DAOException{
		hoteles=new HotelDAOImpl();
		listaHoteles=new ArrayList<HotelDTO>();
		h1=new HotelDTO();
		h1.setDescripcion(descripcionHotel);
		h1.setDireccion("Una direccion");
		h1.setEstrellas(3);
		h1.setNombre(nombreHotel);
		long hotelId = hoteles.insertHotel(h1);
		HotelDTO h2 = hoteles.getHotel(hotelId);
		String otronombre = "OTRONOMBRE";
		h2.setNombre(otronombre);
		hoteles.updateHotel(h2);
		HotelDTO h3 = hoteles.getHotel(h2.getId());
		assertEquals(h3.getNombre(), otronombre);
		
	}
	
	@Test
	public void testFindHotel() throws DAOException{
		/*
		hoteles= new HotelDAOImpl();
		ArrayList<HotelDTO> list1 = hoteles.findHotel("silken");
		ArrayList<HotelDTO> list2=hoteles.findHotel("7");
		ArrayList<HotelDTO> list3=hoteles.findHotel("coronas");
		ArrayList<HotelDTO> list4=hoteles.findHotel("OTRO");
		ArrayList<HotelDTO> list5=hoteles.findHotel("OTROaaa");
		
		System.out.println(" 1:" + list1.size() +"  2:" + list2.size() + "  3:" + list3.size() + " 4:" +list4.size() + " 5:" + list5.size());
	
		
		*/
		
	}


}
