package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.HotelDTO;
import model.PeriodoNoDisponibilidadDTO;
import model.TipoHabitacionDTO;

public class Filters {
	
	public static List<HotelDTO> filterHotelsByDates(List<HotelDTO> hoteles, Calendar startDate,Calendar endDate,int numeroPersonas) throws DAOException{
		
		List<HotelDTO> hl=new ArrayList<HotelDTO>();
		
		for (HotelDTO hotel : hoteles) {
		if(isDisponible(hotel, startDate, endDate)){
			int sumaPlazasLibres=0;
			List<TipoHabitacionDTO> habitaciones = hotel.getHabitaciones();
			boolean hayHabitaciones=false;
			for (TipoHabitacionDTO tipoHabitacion : habitaciones) {
				
				if(isDisponible(tipoHabitacion, startDate, endDate)){
					hayHabitaciones=true;
					int plazasLibres=FactoryDAOImpl.loadInstance().getTipoHabitacion().getHabitacionesLibres(tipoHabitacion, startDate, endDate);
					sumaPlazasLibres=sumaPlazasLibres+plazasLibres;
				} 
			}
			if(hayHabitaciones && (sumaPlazasLibres>=numeroPersonas || numeroPersonas==0)){hl.add(hotel);}
		}
		
		
		}
		
		return hl;
	}
	
	
	
	
	
	public static boolean afterOrEqual(Calendar date1,Calendar date2){
		return date1.after(date2)||date1.equals(date2);
	}
	public static boolean beforeOrEqual(Calendar date1,Calendar date2){
		return afterOrEqual(date2, date1);
	}
	
	public static boolean between(Calendar date,Calendar start,Calendar end){
		
		return (afterOrEqual(date,start) && beforeOrEqual(date, end)) ;// date >= start
	}
	public static boolean isDisponible (TipoHabitacionDTO tipoHabitacion,Calendar fechaIni, Calendar fechaFin) throws DAOException {
		return isDisponible(tipoHabitacion.getPeriodosDeNoDisponibilidad(), fechaIni, fechaFin);
	}
	
	public static boolean isDisponible (HotelDTO hotel,Calendar fechaIni, Calendar fechaFin) throws DAOException {
		return isDisponible(hotel.getPeriodosNoDisponibilidad(), fechaIni, fechaFin);
	}
	public static boolean isDisponible (List<PeriodoNoDisponibilidadDTO> pnds,Calendar fechaIni, Calendar fechaFin) throws DAOException {
		for (PeriodoNoDisponibilidadDTO pnd : pnds) {
			Calendar sd=pnd.getFechaIni(); //sd=start date
			Calendar ed=pnd.getFechaFin();//ed=end date
			
		boolean nodisponible=	((between( fechaIni ,sd , ed)) || 	(  between(fechaFin,sd,ed) || 		((beforeOrEqual(fechaIni, ed) ) && (afterOrEqual(fechaFin, ed))  ) ));
				return !nodisponible;
		}
		return true;
		
	}
	

}
