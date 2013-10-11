package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.OfertaDescuentoDTO;

import dao.DAOException;
import dao.OfertaDescuentoDAO;
import dao.jpa.pojos.Hotel;
import dao.jpa.pojos.OfertaDescuento;

public class OfertaDescuentoDAOImpl implements OfertaDescuentoDAO {

	public long insertOfertaDescuento(OfertaDescuentoDTO ofertaDescuento) 			throws DAOException {
		// TODO Auto-generated method stub
		
		return ((OfertaDescuento)Util.persist(castFromDTOToDAOImpl(ofertaDescuento))).getOfertaDescuentoId();
		
	
		
	}

	protected static OfertaDescuento castFromDTOToDAOImpl(
			OfertaDescuentoDTO ofertaDescuento) {
		// TODO Auto-generated method stub
		OfertaDescuento od=new OfertaDescuento();
		od.setFechaFin(ofertaDescuento.getFechaFin());
		od.setFechaIni(ofertaDescuento.getFechaIni());
		od.setOfertaDescuentoId(ofertaDescuento.getOfertaDescuentoId());
		od.setPorcentaje(ofertaDescuento.getPorcentaje());
		return od;
	}
	
	protected static List<OfertaDescuentoDTO> castFromDAOImplToDTO(List<OfertaDescuento> ofertasDescuentoImpl){
		List<OfertaDescuentoDTO> ofertasDTO=new ArrayList<OfertaDescuentoDTO>();
		for (OfertaDescuento ofertaDescuento : ofertasDescuentoImpl) {
			OfertaDescuentoDTO od = castFromDAOImplToDTO(ofertaDescuento);
			ofertasDTO.add(od);
		}
		return ofertasDTO;
	}
	protected static OfertaDescuentoDTO castFromDAOImplToDTO(OfertaDescuento ofertaDescuento) {
		OfertaDescuentoDTO od=new OfertaDescuentoDTO();
		od.setFechaFin(ofertaDescuento.getFechaFin());
		od.setFechaIni(ofertaDescuento.getFechaIni());
		od.setOfertaDescuentoId(ofertaDescuento.getOfertaDescuentoId());
		od.setPorcentaje(ofertaDescuento.getPorcentaje());
		
		return od;
	}
	
	protected static List<OfertaDescuento> castFromDTOToDAOImpl(List<OfertaDescuentoDTO> ofertasDescuentoDTO){
		List<OfertaDescuento> ofertasDescuentoImpl=new ArrayList<OfertaDescuento>();
		for (OfertaDescuentoDTO ofertaDescuento : ofertasDescuentoDTO) {
			
			ofertasDescuentoImpl.add(castFromDTOToDAOImpl(ofertaDescuento));
		}
		return ofertasDescuentoImpl;
	}
	
	public void updateOfertaDescuento(OfertaDescuentoDTO ofertaDescuento) throws DAOException {
		// TODO Auto-generated method stub
		Util.merge(castFromDTOToDAOImpl(ofertaDescuento));
		
		
	}

}
