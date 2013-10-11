package dao;

import java.util.ArrayList;
import java.util.List;

import model.CiudadDTO;
import model.PeriodoNoDisponibilidadDTO;

import dao.jpa.pojos.Ciudad;
import dao.jpa.pojos.PeriodoNoDisponibilidad;



public interface PeriodoNoDisponibilidadDAO{
	 	 public long insertPeriodoNoDisponibilidad(PeriodoNoDisponibilidadDTO periodoND) throws DAOException;
	 	public void updatePeriodoNoDisponibilidad(PeriodoNoDisponibilidadDTO PeriodoNoDisponibilidad) throws DAOException;
	     


}
