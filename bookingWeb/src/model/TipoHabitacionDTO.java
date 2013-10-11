package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TipoHabitacionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 164761659680414376L;
	public TipoHabitacionDTO() {
		super();
		this.periodosDeNoDisponibilidad = new ArrayList<PeriodoNoDisponibilidadDTO>();
		this.descuentos = new ArrayList<OfertaDescuentoDTO>();
		
	}
	private long tipoHabitacionId;
	private String categoria;
	private int maxPers;
	private float precio;
	private int numHabitaciones;
	private List<PeriodoNoDisponibilidadDTO> periodosDeNoDisponibilidad;
	private List<OfertaDescuentoDTO> descuentos;
	public List<PeriodoNoDisponibilidadDTO> getPeriodosDeNoDisponibilidad() {
		return periodosDeNoDisponibilidad;
	}
	public void setPeriodosDeNoDisponibilidad(
			List<PeriodoNoDisponibilidadDTO> periodosDeNoDisponibilidad) {
		this.periodosDeNoDisponibilidad = periodosDeNoDisponibilidad;
	}
	public void addOfertaDescuentoDTO(){descuentos.add(new OfertaDescuentoDTO());}
	public void removeOfertaDescuentoDTO(OfertaDescuentoDTO oferta){descuentos.remove(oferta);}
	public void addPeriodoNoDisponibilidadDTO(){
		periodosDeNoDisponibilidad.add(new PeriodoNoDisponibilidadDTO());}
	public void removePeriodoNoDisponibilidadDTO(PeriodoNoDisponibilidadDTO periodo){periodosDeNoDisponibilidad.remove(periodo);}
	public List<OfertaDescuentoDTO> getDescuentos() {
		return descuentos;
	}
	public float getPrecio(Calendar startDate,Calendar endDate){
	float suma=0F;
		Calendar sd=(Calendar) startDate.clone();
		Calendar ed=(Calendar) endDate.clone();

		for (; !sd.after(ed); sd.add(Calendar.DATE, 1)) {
			suma=suma+getPrecio(sd);
		    System.out.println();
		    // Do your job here.
		}
		
		
		return suma;
	}
	public float getPrecio(Calendar date){
		float porcentaje=0F;
		for (OfertaDescuentoDTO deal : descuentos) {
			 if (Util.isBetweenDates(deal.getFechaIni(),deal.getFechaFin(), date))
			 {
				porcentaje=porcentaje+ deal.getPorcentaje(); 
			 }
				
			}
		if(porcentaje>=100){return 0;}
		
		return precio-(precio*porcentaje/100);
		
	}
	public void setDescuentos(List<OfertaDescuentoDTO> descuentos) {
		this.descuentos = descuentos;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getMaxPers() {
		return maxPers;
	}
	public void setMaxPers(int maxPers) {
		this.maxPers = maxPers;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getNumHabitaciones() {
		return numHabitaciones;
	}
	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}
	
	public long getTipoHabitacionId() {
		return tipoHabitacionId;
	}
	public void setTipoHabitacionId(long tipoHabitacionId) {
		this.tipoHabitacionId = tipoHabitacionId;
	}
}
