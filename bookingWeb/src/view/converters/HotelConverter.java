package view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import model.HotelDTO;
import model.ProvinciaDTO;
import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

public class HotelConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		HotelDTO h;
		try {
			h = FactoryDAOImpl.loadInstance().getHotel().getHotel(Long.parseLong(arg2));
			return h;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		HotelDTO h=(HotelDTO)arg2;
		return h.getId()+"";
	}

}
