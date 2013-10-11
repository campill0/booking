package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.TarjetaDTO;

import dao.DAOException;
import dao.TarjetaDAO;
import dao.jpa.pojos.Provincia;
import dao.jpa.pojos.Tarjeta;



public class TarjetaDAOImpl implements TarjetaDAO {


	public void insertTarjeta(TarjetaDTO tarjeta) throws DAOException {
		// TODO Auto-generated method stub
		Util.persist(castFromDTOToDAOImpl(tarjeta));
	
	}
	public void updateTarjeta(TarjetaDTO tarjeta) throws DAOException{
		Util.merge(castFromDTOToDAOImpl(tarjeta));
	}
	
	protected static Tarjeta castFromDTOToDAOImpl (TarjetaDTO tarjeta){
		Tarjeta r= new Tarjeta();
		r.setEntidad(tarjeta.getEntidad());
		r.setNumero(tarjeta.getNumero());
		r.setTipo(tarjeta.getTipo());
		return r;
		
	}
	
protected static TarjetaDTO castFromDAOImplToDTO (Tarjeta tarjeta){
	
	TarjetaDTO r= new TarjetaDTO();
	r.setEntidad(tarjeta.getEntidad());
	r.setNumero(tarjeta.getNumero());
	r.setTipo(tarjeta.getTipo());
	
	return r;
	

}

protected static List<Tarjeta> castFromDTOToDAOImpl(List<TarjetaDTO> tarjetasDTO){
	List<Tarjeta> tarjetasImpl=new ArrayList<Tarjeta>();
	for (TarjetaDTO tarjeta : tarjetasDTO) {
		tarjetasImpl.add(castFromDTOToDAOImpl(tarjeta));
	}
	return tarjetasImpl;
}

protected static List<TarjetaDTO> castFromDAOImplToDTO(List<Tarjeta> tarjetas){
	List<TarjetaDTO> tarjetasDTO=new ArrayList<TarjetaDTO>();
	for (Tarjeta tarjeta : tarjetas) {
		tarjetasDTO.add(castFromDAOImplToDTO(tarjeta));
	}
	return tarjetasDTO;
}

}
