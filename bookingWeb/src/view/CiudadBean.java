package view;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import dao.DAOException;
import dao.jpa.FactoryDAOImpl;



import model.CiudadDTO;
import model.ProvinciaDTO;

public class CiudadBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String provinciaElegida;
	private String ciudadElegida;
	private List<SelectItem> provincias ;//=new ArrayList<SelectItem>();
	private List<SelectItem> ciudades;
	public CiudadBean() {
		try {
		List<ProvinciaDTO>	provList=FactoryDAOImpl.loadInstance().getProvincia().findAllProvincia();
		provincias=castFromProvinciaDTOToSelectItem(provList);
		ciudades= castFromCiudadDTOToSelectItem(provList.get(0).getCiudades());
		} catch (DAOException e) { 			e.printStackTrace(); 		}
	}
	public String getProvinciaElegida() {		return provinciaElegida;	}
	public void setProvinciaElegida(String provinciaElegida) {		this.provinciaElegida = provinciaElegida;	}
	public String getCiudadElegida() {		return ciudadElegida;	}
	public void setCiudadElegida(String ciudadElegida) {		this.ciudadElegida = ciudadElegida;	}
	public List<SelectItem> getProvincias() {		return provincias;	}
	public void setProvincias(List<SelectItem> provincias) {		this.provincias = provincias;	}
	public List<SelectItem> getCiudades() {		return ciudades;	}
	public void setCiudades(List<SelectItem> ciudades) {		this.ciudades = ciudades;	}
	public SelectItem castFromCiudadDTOToSelectItem(CiudadDTO ciudad){return new SelectItem(ciudad.getCiudadId(),ciudad.getNombre());}
	public List<SelectItem> castFromCiudadDTOToSelectItem(List<CiudadDTO> ciudades){ 		List<SelectItem> cities= new ArrayList<SelectItem>(); 		for (CiudadDTO ciudadDTO : ciudades) { 			cities.add(castFromCiudadDTOToSelectItem(ciudadDTO));} 	return cities;}
	public SelectItem castFromProvinciaDTOToSelectItem(ProvinciaDTO provincia){return new SelectItem(provincia.getId(),provincia.getNombre());}
	public List<SelectItem> castFromProvinciaDTOToSelectItem(List<ProvinciaDTO> provincias){ 		List<SelectItem> zones= new ArrayList<SelectItem>();  		for (ProvinciaDTO provinciaDTO : provincias) { 			zones.add(castFromProvinciaDTOToSelectItem(provinciaDTO)); 		} 	return zones;}
	public static long getSerialversionuid() { 		return serialVersionUID; 	}
	public void handleCityChange2() throws NumberFormatException, DAOException {
		//FactoryDAOImpl.loadInstance().getCiudad().getCiudad(Long.parseLong(ciudadElegida));
		System.out.println("handleCityChange 2");
	}
	public void handleCityChange() throws NumberFormatException, DAOException {
		if(provinciaElegida!=null){
			ProvinciaDTO p=FactoryDAOImpl.loadInstance().getProvincia().getProvincia(Long.parseLong(provinciaElegida));
			ciudades=castFromCiudadDTOToSelectItem(p.getCiudades());
		}
		else {ciudades=new ArrayList<SelectItem>();}
		System.out.println("handleCityChange");
	}

    public void displayLocation() {
        FacesMessage msg = new FacesMessage("Selected", "City:" );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}

					