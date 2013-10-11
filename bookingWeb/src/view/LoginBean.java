package view;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;
import model.UsuarioDTO;

public class LoginBean {
	private UsuarioDTO usuario;
	private String loginstr;
	private String password;


	public String getLoginstr() {
		return loginstr;
	}

	public void setLoginstr(String loginstr) {
		this.loginstr = loginstr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginBean() {
		super();
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public boolean isLogged(){return (usuario!=null);}
	public String loginName(){
		if(usuario!=null){
		return usuario.getNombre();	
		}
		else{
			return "invitado";
		}
		
		}
	public String login() throws IOException{
		
	
		if (!isLogged())
		try {
			UsuarioDTO u=FactoryDAOImpl.loadInstance().getUsuario().getUsuario(loginstr);
			if(u!=null){	
				if(u.getPassword().equals(password)){
					usuario=u;
					 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
						
				        externalContext.redirect("default.faces");
					//addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", Util.getLocaleString("msg", "welcome", "Wellcome") + u.getNombre() ));	
				}else{
					addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, Util.getLocaleString("msg", "incorrect_password", "Incorrect password"), Util.getLocaleString("msg", "incorrect_password","Incorrect password")));
				}
			}
			else{
				addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, Util.getLocaleString("msg", "user_no_exists", "User no exists"), Util.getLocaleString("msg", "user_no_exists", "User no exists") + ":" + loginstr));
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			//addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", Util.getLocaleString("msg", "welcome", "Wellcome") + u.getNombre() ));
		}
		
		
		
			return "ok";
	}
	

	public void logout() throws IOException{
		usuario=null;
		
		
		 ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	       
	        externalContext.redirect("default.faces");
	        externalContext.invalidateSession();
		
	}
	public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	

}
