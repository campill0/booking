package dao;

import java.util.ArrayList;
import java.util.List;

import model.CiudadDTO;


public interface CiudadDAO {
	 	//public ArrayList<CiudadDTO> findCiudad(String ciudadNombre) throws DAOException;
	    public  List findAllCiudad() throws DAOException;
	    public  List findAllCiudadWithHotels() throws DAOException;
	    public  List findAllCiudadCiudadId() throws DAOException;
	    public long insertCiudad(CiudadDTO ciudad) throws DAOException;
	    public CiudadDTO getCiudad(long id) throws DAOException;
	    public List findCiudadByName(String name) throws DAOException;
	    public List findCiudadWithHotelByName(String name) throws DAOException;
	    
	    //public void updateCiudad(CiudadDTO ciudad) throws DAOException;
	    //public void deleteCiudad(CiudadDTO ciudad) throws DAOException;
	    //public CiudadDTO getCiudad (long id) throws DAOException;
	    //public List findAllCiudad() throws DAOException;


}
