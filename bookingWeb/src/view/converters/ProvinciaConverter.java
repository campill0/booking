package view.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import model.ProvinciaDTO;
import dao.DAOException;
import dao.jpa.FactoryDAOImpl;


public class ProvinciaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		ProvinciaDTO p;
		try {
			p = FactoryDAOImpl.loadInstance().getProvincia().getProvincia(Long.parseLong(arg2));
			return p;
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
		ProvinciaDTO p = (ProvinciaDTO) arg2;
		return p.getId()+"";
	}

}
