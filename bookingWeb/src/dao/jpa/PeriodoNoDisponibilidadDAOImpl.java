package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.PeriodoNoDisponibilidadDTO;

import dao.DAOException;
import dao.PeriodoNoDisponibilidadDAO;
import dao.jpa.pojos.PeriodoNoDisponibilidad;




public class PeriodoNoDisponibilidadDAOImpl implements PeriodoNoDisponibilidadDAO {

	public long insertPeriodoNoDisponibilidad(
//			return ((PeriodoNoDisponibilidad) Util.persist(castFromDTOToDAOImpl(periodoND))).getPeriodoID();
		PeriodoNoDisponibilidadDTO periodoND) throws DAOException {
		PeriodoNoDisponibilidad pnd = castFromDTOToDAOImpl(periodoND);
		pnd=(PeriodoNoDisponibilidad) Util.persist(pnd);
		return pnd.getPeriodoID();
	
	}
	
	public void updatePeriodoNoDisponibilidad(PeriodoNoDisponibilidadDTO PeriodoNoDisponibilidad) throws DAOException {
		// TODO Auto-generated method stub
		Util.merge(castFromDTOToDAOImpl(PeriodoNoDisponibilidad));
	}

	protected static PeriodoNoDisponibilidad castFromDTOToDAOImpl(
		PeriodoNoDisponibilidadDTO periodoND) {
		PeriodoNoDisponibilidad per=new PeriodoNoDisponibilidad();
		per.setFechaFin(periodoND.getFechaFin());
		per.setFechaIni(periodoND.getFechaIni());
		per.setMotivo(periodoND.getMotivo());
		if(periodoND.getPeriodoID()!=0){per.setPeriodoID(periodoND.getPeriodoID());		}
		return per;
	}

	
	protected static List<PeriodoNoDisponibilidadDTO> castFromDAOImplToDTO(List<PeriodoNoDisponibilidad> periodosNDImpl){
		List<PeriodoNoDisponibilidadDTO> periodosNDDTO=new ArrayList<PeriodoNoDisponibilidadDTO>();
		for (PeriodoNoDisponibilidad periodoND : periodosNDImpl) {
			PeriodoNoDisponibilidadDTO pnd = castFromDAOImplToDTO(periodoND);
			periodosNDDTO.add(pnd);
		}
		return periodosNDDTO;
	}


	protected static PeriodoNoDisponibilidadDTO castFromDAOImplToDTO(
			PeriodoNoDisponibilidad periodoND) {
		PeriodoNoDisponibilidadDTO pnd=new PeriodoNoDisponibilidadDTO();
		pnd.setFechaFin(periodoND.getFechaFin());
		pnd.setFechaIni(periodoND.getFechaIni());
		pnd.setMotivo(periodoND.getMotivo());
		pnd.setPeriodoID(periodoND.getPeriodoID());
		return pnd;
	}
	protected static List<PeriodoNoDisponibilidad> castFromDTOToDAOImpl(List<PeriodoNoDisponibilidadDTO> periodosNDDTO){
		List<PeriodoNoDisponibilidad> periodosNDImpl=new ArrayList<PeriodoNoDisponibilidad>();
		for (PeriodoNoDisponibilidadDTO periodoND : periodosNDDTO) {
			PeriodoNoDisponibilidad pnd = castFromDTOToDAOImpl(periodoND);
			pnd.setPeriodoID(periodoND.getPeriodoID());
			periodosNDImpl.add(pnd);
		}
		return periodosNDImpl;
	}

	


	
	
	

}
