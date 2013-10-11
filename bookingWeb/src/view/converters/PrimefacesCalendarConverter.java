package view.converters;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.apache.commons.lang.StringUtils;

public class PrimefacesCalendarConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Calendar calendar = null;
		Date convertedToDate;

		if (!StringUtils.isBlank(value)){
			convertedToDate = convertToDate(context, (org.primefaces.component.calendar.Calendar)component, value);
			calendar = Calendar.getInstance();
			calendar.setTime(convertedToDate);
		}
		
		return calendar;
	}

	protected Date convertToDate(FacesContext context, org.primefaces.component.calendar.Calendar pfCalendarComponent, String submittedValue) {
		Locale locale = pfCalendarComponent.calculateLocale(context);
		SimpleDateFormat format = new SimpleDateFormat(pfCalendarComponent.getPattern(), locale);
		format.setTimeZone(pfCalendarComponent.calculateTimeZone());
		
		try {
			return format.parse(submittedValue);
		} catch (ParseException e) {
			throw new ConverterException(e);
		}
	}
	
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String convertedValue = null;
		
		
		if (value != null){
			convertedValue = convertToString(context, (org.primefaces.component.calendar.Calendar)component, value);
		}
		
		return convertedValue;
	}

	protected String convertToString(FacesContext context, org.primefaces.component.calendar.Calendar pfCalendarComponent, Object value) {
		String pattern = pfCalendarComponent.getPattern(); 
		if(pattern==null){
			pattern="yyyy/MM/dd";
		}
		Locale component = pfCalendarComponent.calculateLocale(context);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern,component);

		dateFormat.setTimeZone(pfCalendarComponent.calculateTimeZone());

		return dateFormat.format(((Calendar)value).getTime());
	}
	
}
