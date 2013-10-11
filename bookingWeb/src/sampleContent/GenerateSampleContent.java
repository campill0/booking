package sampleContent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.CiudadDTO;
import model.HotelDTO;
import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

public class GenerateSampleContent {
private static int NHOTELESCIUDAD=100;
private static int NHOTELESRESTO=1000;
	/**
	 * @param args
	 * @throws DAOException 
	 */
	public static void main(String[] args) throws DAOException {
		// TODO Auto-generated method stub
			/*
		dao.jpa.Util.updateQuery("DELETE FROM Usuario");
		dao.jpa.Util.updateQuery("DELETE FROM Tarjeta");
		dao.jpa.Util.updateQuery("DELETE FROM Habitacion");
		dao.jpa.Util.updateQuery("DELETE FROM Hotel");
		dao.jpa.Util.updateQuery("DELETE FROM Reserva");
		dao.jpa.Util.updateQuery("DELETE FROM Usuario");
		dao.jpa.Util.updateQuery("DELETE FROM Tarjeta");
		dao.jpa.Util.updateQuery("DELETE FROM PeriodoNoDisponibilidad");
		dao.jpa.Util.updateQuery("DELETE FROM TipoHabitacion");
		dao.jpa.Util.updateQuery("DELETE FROM OfertaDescuento");
		*/
		
		SampleContent sc= new SampleContent();
		List<HotelDTO> hoteles=new ArrayList<HotelDTO>();
		HotelDTO hotel = sc.randomHotel(null);
		List<CiudadDTO> ciudades= new ArrayList<CiudadDTO>();
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(4625));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(899));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(7270));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(4392));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(2773));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(853));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(5776));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(3187));
		ciudades.add(FactoryDAOImpl.loadInstance().getCiudad().getCiudad(6199));
		Date start=new Date();
		Date now;
		int nhoteles=0;	
		float time=0;
		float avg=0;
		float restante=0;
		float restanteTiempo=0;
		for (CiudadDTO ciudad : ciudades) {
					for (int i = 0; i < NHOTELESCIUDAD; i++) {
						sc.persistHotel( sc.randomHotel(ciudad));	
						nhoteles++;
						if(nhoteles%10 == 0){
						
						now = new Date();
						time=(float) ((now.getTime()-start.getTime())/1000);
						avg=time/nhoteles;
						restante=((NHOTELESCIUDAD*ciudades.size())+NHOTELESRESTO)-nhoteles;
						restanteTiempo=restante*avg/60;
						System.out.println("Quedan " + restante + " hoteles , tiempo restante aprox:" + restanteTiempo);
						System.out.println(nhoteles + " en " + time + " segundos a una velocidad de " + avg + " segundos por hotel" );
						}
					}
					
					
				}
				
				for (int i = 0; i < NHOTELESRESTO; i++) {
					sc.persistHotel( sc.randomHotel(null));
					nhoteles++;
					if(nhoteles%10 == 0){
					
					now = new Date();
					time=(float) ((now.getTime()-start.getTime())/1000);
					avg=time/nhoteles;
					restante=((NHOTELESCIUDAD*ciudades.size())+NHOTELESRESTO)-nhoteles;
					restanteTiempo=restante*avg/60;
					System.out.println("Quedan " + restante + " hoteles , tiempo restante aprox:" + restanteTiempo);
					System.out.println(nhoteles + " en " + time + " segundos a una velocidad de " + avg + " segundos por hotel" );
					}
				}
		/*
		 	
4625 murcia
899 Barcelona
7270 Valencia
4392 Madrid
2773 Granada
853 Palma de Mayorca
5776 Santa Cruz de Tenerife
3187 Donostia-San Sebastián
6199 Sevilla
		 
		 */
		
		
		System.out.println();
		
	}

}
