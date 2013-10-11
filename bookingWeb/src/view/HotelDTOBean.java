package view;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.HotelDTO;

public class HotelDTOBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 761609700059782606L;
	private HotelDTO hotel;

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public HotelDTOBean() {
		super();
		String hotelId=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hotelid");
		
		if(hotelId!=null){
		try {
			hotel=FactoryDAOImpl.loadInstance().getHotel().getHotel(Long.parseLong(hotelId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		else{
			hotel=new HotelDTO();
		}
		// TODO Auto-generated constructor stub
	}
	

}
