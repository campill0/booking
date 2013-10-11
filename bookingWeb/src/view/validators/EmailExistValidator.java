package view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.validator.routines.EmailValidator;

import dao.jpa.FactoryDAOImpl;

public class EmailExistValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub
		EmailValidator ev= EmailValidator.getInstance();
		
		
		if(!ev.isValid(arg2.toString())){
			//<f:validateRegex pattern="[a-zA-Z0-9]+@[a-zA-Z]+.[a-zA-Z]{2,3}"></f:validateRegex>
			String message="La direccion de email no es correcta";
			FacesMessage msg = new FacesMessage(message, message);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
		if(emailExistInBD(arg2.toString())){
			String message="El email ya se encontraba en la base de datos";
			FacesMessage msg = new FacesMessage(message, message);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}	
	}
public boolean emailExistInBD(String mail){
		
		EntityManager em=FactoryDAOImpl.getEntityManager();
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.mail = :mail");
		query.setParameter("mail", mail);
		
		
		return (query.getResultList().size()>0);
		
		
	}

}
