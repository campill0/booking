package view;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;



import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.HotelDTO;

public class ManageHotelsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 79159600085619668L;
private List<HotelDTO> hoteles;
private HotelDTO hotel;

public List<HotelDTO> getHoteles() {
	return hoteles;
}
public void setHoteles(List<HotelDTO> hoteles) {
	this.hoteles = hoteles;
}
public HotelDTO initializeHotelbyId(long hotelId) {
	
	
	if (hotelId==0){return new HotelDTO();}
	else{
	try {
		return  FactoryDAOImpl.loadInstance().getHotel().getHotel(hotelId);
		
	} catch (DAOException e) {
		// TODO Auto-generated catch block
		return new HotelDTO();
	}
	
	}
	
}
public HotelDTO getHotel() {
	return hotel;
}
public void setHotel(HotelDTO hotel) {
	this.hotel = hotel;
}
public ManageHotelsBean() {
	super();
	try {
		
		hoteles=FactoryDAOImpl.loadInstance().getHotel().findAllHotel();
		
		hotel= new HotelDTO();
	} catch (DAOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



}
