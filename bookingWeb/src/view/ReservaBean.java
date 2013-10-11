package view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.DateSelectEvent;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.HabitacionDTO;
import model.HotelDTO;
import model.TipoHabitacionDTO;

public class ReservaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8837301123814308650L;
	private String peticiones;
	
	private HotelDTO hotel;
	private Calendar fechaEntrada;
	private Calendar fechaSalida;
	
	private Map<Long,Integer> numHabitaciones;
		public HotelDTO getHotel() {
		return hotel;
	}

			public float getTotalFactura() {
				
				float suma=0.0F;
				
				
				List<TipoHabitacionDTO> habitaciones = hotel.getHabitaciones();
				for (TipoHabitacionDTO hab : habitaciones) {
					long habid=hab.getTipoHabitacionId();
					int nhabs=0;
					Object ob=new Object();
					if(numHabitaciones.get(habid)!=null){
					 ob=numHabitaciones.get(habid);
					 if(!ob.equals("")){
					nhabs=Integer.parseInt( ob.toString());}	
					}
					
					suma=suma+( nhabs  * hab.getPrecio(fechaEntrada, fechaSalida));
				}
				return suma;
		}

		

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public ReservaBean() {
		
		super();
		testConstructor();
		/*
		numHabitaciones=new HashMap<Long,Integer>();
		SearchHotelBean shb = (SearchHotelBean) Util.accessBeanFromFacesContext("searchHotelBean", FacesContext.getCurrentInstance());
		hotel=shb.getHotel();
		fechaEntrada=shb.getFechaEntrada();
		fechaSalida=shb.getFechaSalida();
		List<TipoHabitacionDTO> habitaciones = hotel.getHabitaciones();
			for (TipoHabitacionDTO hab : habitaciones) {
				numHabitaciones.put(hab.getTipoHabitacionId(), 4);
			}
		 
		System.out.println("s");
		*/
		
		// TODO Auto-generated constructor stub
	}
	
	public void testConstructor(){
		numHabitaciones=new HashMap<Long,Integer>();
		/*
		try {
			hotel=FactoryDAOImpl.loadInstance().getHotel().getHotel(1087);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		SearchHotelBean s = (SearchHotelBean) Util.accessBeanFromFacesContext("searchHotelBean", FacesContext.getCurrentInstance());
		hotel=s.getHotel();
		fechaEntrada=s.getFechaEntrada();
		fechaSalida=s.getFechaSalida();
		List<TipoHabitacionDTO> habitaciones = hotel.getHabitaciones();
			for (TipoHabitacionDTO hab : habitaciones) {
				numHabitaciones.put(hab.getTipoHabitacionId(), 0);
			}
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

	public Map<Long, Integer> getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(Map<Long, Integer> numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}
	
	public String getTotalPrecio(){
	
		
		return  getTotalFactura()+"";	
	}


	private int nHabitaciones;
	
	public int getnHabitaciones() {
		return nHabitaciones;
	}

	public void setnHabitaciones(int nHabitaciones) {
		this.nHabitaciones = nHabitaciones;
	}

	
	public String getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(String peticiones) {
		this.peticiones = peticiones;
	}
	
	public void handleDateSelect(DateSelectEvent event) {  
      System.out.println("asdsa");
      System.out.println("asdsa");
		/*  FacesContext facesContext = FacesContext.getCurrentInstance();  
        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getDate())));*/  
    }  

}
