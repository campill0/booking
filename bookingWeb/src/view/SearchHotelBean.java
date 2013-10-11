package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.CiudadDTO;
import model.HotelDTO;
import model.ProvinciaDTO;

public class SearchHotelBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -937621743743621857L;
	public  static enum Filter{CITY,STATE,HOTEL};
	private Filter filter; 
	public boolean isResults(){
		if (hoteles==null)return false;
		if (hoteles.size()==0)return false;
		return true;
	
	}
	

	public boolean getFilterCity(){
		return (filter==Filter.CITY);
	}
	public boolean getFilterState(){
		return (filter==Filter.STATE);
	}
	
	public boolean getFilterHotel(){
		return (filter==Filter.HOTEL);
	}
	
	private String query;
	private CiudadDTO ciudad;
	private ProvinciaDTO provincia;
	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}
	private HotelDTO hotel;
	private int personNumber;
	private Calendar fechaEntrada;
	private Calendar fechaSalida;
	private List<HotelDTO> hoteles;
	
	public SearchHotelBean() {
		super();
		hoteles=new ArrayList<HotelDTO>();
		fechaEntrada=Calendar.getInstance();
		fechaSalida=Calendar.getInstance();
		query="";
		personNumber=1;
		filter=Filter.CITY;
		
		// TODO Auto-generated constructor stub
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void changeHotel(){
		
		HotelDTO h = (HotelDTO) Util.accessBeanFromFacesContext("hotel", FacesContext.getCurrentInstance());
		hotel=h;
	}

	

	

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}

	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Calendar fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public List<HotelDTO> getHoteles() {
		return hoteles;
	}

	public void setHoteles(List<HotelDTO> hoteles) {
		this.hoteles = hoteles;
	}
	 public List searchCompleteCity(String query) throws DAOException {
		 if(query.length()>=3){
		        List<CiudadDTO> ciudades = FactoryDAOImpl.loadInstance().getCiudad().findCiudadWithHotelByName(query);
		        
		        return ciudades;
		        
		        } 
		 else{
				return new ArrayList();
			} 
		       
	 }
	 public List searchCompleteState(String query) throws DAOException {
		 return FactoryDAOImpl.loadInstance().getProvincia().findProvinciaByName(query);
	 }
	 public List searchCompleteHotel(String query) throws DAOException {
		 
		 return FactoryDAOImpl.loadInstance().getHotel().findHotelByName(query);
	 }
	 public List searchCityComplete (String city) throws DAOException{
		 if(city.length()>=3){
		        List<CiudadDTO> ciudades = FactoryDAOImpl.loadInstance().getCiudad().findCiudadByName(city);
		        
		        return ciudades;
		        
		        }  
		 return new ArrayList<HotelDTO>();
	 }
	 public List searchComplete(String query) throws DAOException {
		 
		 
		 
		 
		switch (filter) {
		case CITY:
			if(query.length()>=3){
		        List<CiudadDTO> ciudades = FactoryDAOImpl.loadInstance().getCiudad().findCiudadWithHotelByName(query);
		        
		        return ciudades;
		        
		        }  
		         
		        
		        
		     return new ArrayList();
		      		        
		case STATE:
			
			return FactoryDAOImpl.loadInstance().getProvincia().findProvinciaByName(query);
			
	        
		case HOTEL:
			List hotels=FactoryDAOImpl.loadInstance().getHotel().findHotelByName(query);
			 return hotels;
		default:
			return new ArrayList();
			
		}
		
		 


	
	 }
	 public void radioChanged (ValueChangeEvent e){
		 Object var = e.getNewValue();
		
	 }

	public CiudadDTO getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}
	public void nada(){
		int uno=1;
	}
	

}
