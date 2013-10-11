package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import dao.DAOException;
import dao.jpa.FactoryDAOImpl;

import model.ReservaDTO;

public class ReservasUsuarioBean implements Serializable{
	
	private List<ReservaDTO> reservas;
	private List<ReservaDTO> reservasOcupadas;
	public List<ReservaDTO> getReservasOcupadas() {
		return reservasOcupadas;
	}


	public void setReservasOcupadas(List<ReservaDTO> reservasOcupadas) {
		this.reservasOcupadas = reservasOcupadas;
	}


	public List<ReservaDTO> getReservas() {
		return reservas;
	}


	public void setReservas(List<ReservaDTO> reservas) {
		this.reservas = reservas;
	}
	
	public ReservasUsuarioBean() {
		super();
		// TODO Auto-generated constructor stub
		
		LoginBean l = (LoginBean) Util.accessBeanFromFacesContext("loginBean", FacesContext.getCurrentInstance());
		if(l.getUsuario()!=null){
		try {
			reservas=FactoryDAOImpl.loadInstance().getReserva().findReservaByUser(l.getUsuario());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reservasOcupadas=new ArrayList<ReservaDTO>();
		for (ReservaDTO r : reservas) {
			if(r.getOcupado()&& (!r.isCancelada())){
				reservasOcupadas.add(r);
			}
		}
		
		}
	}
	
	

}
