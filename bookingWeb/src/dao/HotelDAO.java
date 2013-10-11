package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.CiudadDTO;
import model.HotelDTO;
import model.ProvinciaDTO;

import dao.jpa.pojos.Reserva;


public interface HotelDAO {
	 	public List findHotelByName(String hotelName) throws DAOException;
	 	public List findHotelByState(ProvinciaDTO hotelState) throws DAOException;
	 	public List findHotelByCity(CiudadDTO city) throws DAOException;
	 	public int findNumHotelsByCity(CiudadDTO city) throws DAOException;
	    public  List findAllHotel() throws DAOException;
	    public long insertHotel(HotelDTO hotel) throws DAOException;
	    public void updateHotel(HotelDTO hotel) throws DAOException;
	    public void deleteHotel(HotelDTO hotel) throws DAOException;
	    public HotelDTO getHotel (long id) throws DAOException;
	//    public List<Reserva> getReservas(HotelDTO hotel)throws DAOException;



}
