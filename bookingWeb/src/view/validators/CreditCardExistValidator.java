package view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.validator.routines.CreditCardValidator;

import dao.jpa.FactoryDAOImpl;

public class CreditCardExistValidator implements Validator {
private static final String REGEX="^4[0-9]{12}(?:[0-9]{3})?$";
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
		CreditCardValidator ccv= new CreditCardValidator();
		
		
		

		if(
				(!ccv.VISA_VALIDATOR.isValid(arg2.toString()))			&&
				(!ccv.MASTERCARD_VALIDATOR.isValid(arg2.toString())))			{
			String message="El numero no es un numero valido para visa o mastercard";
			FacesMessage msg = new FacesMessage(message, message);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		
		if(creditCardExistInBD(arg2.toString())){
			String message="La tarjeta de credito ya se encontraba en la base de datos";
			FacesMessage msg = new FacesMessage(message, message);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}	
	}
public boolean creditCardExistInBD(String numero){
		
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT t FROM Tarjeta t WHERE t.numero = :numero");
		query.setParameter("numero", numero);
		
		
		return (query.getResultList().size()>0);
		
		
	}

}
