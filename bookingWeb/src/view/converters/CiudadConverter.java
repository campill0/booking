package view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import model.CiudadDTO;

import dao.DAOException;
import dao.jpa.CiudadDAOImpl;

import dao.jpa.FactoryDAOImpl;

public class CiudadConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		CiudadDTO c;
		try {
			c = FactoryDAOImpl.loadInstance().getCiudad().getCiudad(Long.parseLong(arg2));
			return c;
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
		CiudadDTO c=(CiudadDTO) arg2;
			return c.getCiudadId()+"";
		
		
	}

}
