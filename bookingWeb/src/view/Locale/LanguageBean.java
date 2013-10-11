package view.Locale;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
 

public class LanguageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String localeCode;
	
	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("English", new Locale("en")); //label, value
		countries.put("Spanish", new Locale("es"));
		countries.put("German", new Locale("de"));
		countries.put("Italian", new Locale("it"));
		countries.put("Chinese", new Locale("zh"));
		countries.put("French", new Locale("fr"));
		
		
	}

	public LanguageBean() {
		super();
		localeCode="es";
		// TODO Auto-generated constructor stub
	}


	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	
	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
public void changeCountryLocaleCodeEs(){
	changeCountryLocaleCode("es");
}
public void changeCountryLocaleCodeEn(){
	changeCountryLocaleCode("en");
}
public void changeCountryLocaleCodeDe(){
	changeCountryLocaleCode("de");
}
public void changeCountryLocaleCodeIt(){
	changeCountryLocaleCode("it");
}
public void changeCountryLocaleCodeZh(){
	changeCountryLocaleCode("zh");
}
public void changeCountryLocaleCodeFr(){
	changeCountryLocaleCode("fr");
}


	public void changeCountryLocaleCode(String newLocaleValue){
System.out.println(newLocaleValue);
		for (Map.Entry<String, Object> entry : countries.entrySet()) {
	        
        	if(entry.getValue().toString().equals(newLocaleValue)){
        		
        		FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
        		setLocaleCode(newLocaleValue);
        	}
        }
	}
	
	public void countryLocaleCodeChanged(ValueChangeEvent e){
		
		String newLocaleValue = e.getNewValue().toString();
		
		//loop a map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
        
        	if(entry.getValue().toString().equals(newLocaleValue)){
        		
        		FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
        		
        	}
        }

	}

}