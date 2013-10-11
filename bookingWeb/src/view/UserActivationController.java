package view;

import javax.persistence.NoResultException;

import model.UsuarioDTO;
import dao.DAOException;
import dao.jpa.FactoryDAOImpl;



public class UserActivationController {
	private String confirmationCode;

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	
	public String performActivation(String activationCode,String userid) throws NumberFormatException, DAOException{
		UsuarioDTO user;
		try {
		user=FactoryDAOImpl.loadInstance().getUsuario().getUsuario(Long.parseLong(userid));
		}
	catch (NoResultException e){
		return "USERNOTFOUND";
	}
		if(!user.isBloqueado()){return "ALREADYACTIVATED";}
		boolean match= (ActionsController.generateConfirmationCode(user.getMail()).compareTo(activationCode)==0);
		
		if (match){
			user.setBloqueado(false);
			FactoryDAOImpl.loadInstance().getUsuario().updateUsuario(user);
			
			String str=		"User id:" + userid+ " Activation code" + activationCode;
			System.out.println(str);
			return "OK";
		}
		else{ return "INVALIDCODE";}
		
	}

}
