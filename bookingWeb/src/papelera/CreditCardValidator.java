package papelera;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.util.MessageFactory;

public class CreditCardValidator implements Validator {

	public final static String INVALID_MESSAGE_ID = "org.primefaces.optimus.validator.CreditCardValidator.INVALID";
	
	private static String creditCardRegex = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$.";

	public void validate(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		try {
			String creditCard = (String) object;
			if (!creditCard.matches(creditCardRegex)) {
				throw new ValidatorException(MessageFactory.getMessage(INVALID_MESSAGE_ID, FacesMessage.SEVERITY_ERROR,new Object[0]));	
			}
		} catch (ClassCastException e) {
			new FacesMessage(MessageFactory.getMessage(INVALID_MESSAGE_ID, FacesMessage.SEVERITY_ERROR,new Object[0]).getSummary());
			
			
		}
	}
}