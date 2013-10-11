package view;

import java.io.Serializable;
import java.util.List;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import model.CiudadDTO;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import view.SearchHotelBean.Filter;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

public class ListaCiudadesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8914637134282130568L;

	private MapModel simpleModel;

	private Marker marker;

	public ListaCiudadesBean() throws DAOException {
		simpleModel = new DefaultMapModel();
		
		//Shared coordinates
		List<CiudadDTO> ciudades= FactoryDAOImpl.loadInstance().getCiudad().findAllCiudadWithHotels();
		for (CiudadDTO ciudad : ciudades) {
			LatLng coord = new LatLng(ciudad.getLatitud(), ciudad.getLongitud());
			simpleModel.addOverlay(new Marker(coord,ciudad.getNombre(),ciudad));
			
		}
		/*
		LatLng coord1 = new LatLng(36.879466, 30.667648);
		LatLng coord2 = new LatLng(36.883707, 30.689216);
		LatLng coord3 = new LatLng(36.879703, 30.706707);
		LatLng coord4 = new LatLng(36.885233, 30.702323);
		
		//Basic marker
		
		 
		 
		simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));
		simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));
		simpleModel.addOverlay(new Marker(coord4, "Kaleici"));
		*/
	}
	
	public MapModel getSimpleModel() {
		return simpleModel;
	}
	
	public void onMarkerSelect(OverlaySelectEvent event) throws DAOException {
		marker = (Marker) event.getOverlay();
		SearchHotelBean searchHotelBean = (SearchHotelBean) Util.accessBeanFromFacesContext("searchHotelBean", FacesContext.getCurrentInstance());
		searchHotelBean.setFilter(Filter.CITY);
		
		CiudadDTO c=(CiudadDTO)marker.getData();
		
		searchHotelBean.setCiudad(c);
		searchHotelBean.setHotel(null);
		searchHotelBean.setProvincia(null);
		searchHotelBean.setQuery(searchHotelBean.getCiudad().getNombre());
		int var=0;
		//searchHotelBean.setHoteles(FactoryDAOImpl.loadInstance().getHotel().findHotelByCity((CiudadDTO)marker.getData()));
	
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
					