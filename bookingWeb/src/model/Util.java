package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.faces.context.FacesContext;

public class Util {

	public static boolean isBetweenDates(Calendar startDate, Calendar endDate,Calendar date) {  
		 
		return ((date.after(startDate) && date.before(endDate)) || (date.equals(startDate) || (date.equals(endDate))));
		} 
	
	
}
