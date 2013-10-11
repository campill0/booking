package view;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class Util {
	public static String baseUrl() {
		 
		 return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		 
		
		}

	/*
	 * devuelve el outcome correcto dependiendo de donde nos encontremos
	 * */
	public String outcome(String outcome,String section){
		String baseUrl=baseUrl();
		boolean matchHome=baseUrl.matches("/[a-z]*");
		boolean matchSection=baseUrl.matches("/[a-z]*/"+section);
		
		return "admin/newHotel";
	}
	public static String getLocaleString(String resourceName,String key, String defaultValue){
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, resourceName);
		String message="";
		try  {
			message= bundle.getString(key);
		}
		catch(java.util.MissingResourceException mre){
			message=defaultValue;	
		}
		return message;
		
	}
	public static long random(long min,long max){
		return  (min + (int)(Math.random() * ((max - min) + 1)));
	}
	public  String calendarToString(Calendar date){
		return date.get(Calendar.YEAR)+"/"+(date.get(Calendar.MONTH)+1)+"/"+date.get(Calendar.DATE);
	}
	public long daysBetween(Calendar startDate, Calendar endDate) {  
		  Calendar date = (Calendar) startDate.clone();  
		  long daysBetween = 0;  
		  while (date.before(endDate)) {  
		    date.add(Calendar.DAY_OF_MONTH, 1);  
		    daysBetween++;  
		  }  
		  return daysBetween;  
		} 
	
	public  static String md5(String s){
	       MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	       m.update(s.getBytes(),0,s.length());
	return new BigInteger(1,m.digest()).toString(16);
	      
}
	public static String capitalize(String s){
		if (s.length() == 0) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	public static Object accessBeanFromFacesContext(final String theBeanName, final FacesContext theFacesContext) {
		final Object returnObject = theFacesContext.getELContext().getELResolver().getValue(theFacesContext.getELContext(), null, theBeanName);
		if (returnObject == null) {
		System.err.println("Bean with name " + theBeanName + " was not found. Check the faces-config.xml file if the given bean name is ok."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return returnObject;
		}
}
