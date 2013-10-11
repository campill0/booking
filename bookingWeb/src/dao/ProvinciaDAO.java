package dao;

import java.util.ArrayList;
import java.util.List;

import model.CiudadDTO;
import model.HotelDTO;
import model.ProvinciaDTO;



public interface ProvinciaDAO {
		public List findProvinciaByName(String name) throws DAOException;
	
	    public  List findAllProvincia() throws DAOException;
	    public long insertProvincia(ProvinciaDTO provincia) throws DAOException;
	    public ProvinciaDTO getProvincia (long id) throws DAOException;
	    public ProvinciaDTO getProvinciabyCity (CiudadDTO city) throws DAOException;
	    
	   // public void updateProvincia(ProvinciaDTO provincia) throws DAOException;
	   // public void deleteProvincia(ProvinciaDTO provincia) throws DAOException;
	   // public ProvinciaDTO getProvincia (long id) throws DAOException;
	     


}
