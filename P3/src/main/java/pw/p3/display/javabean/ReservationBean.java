package pw.p3.display.javabean;

import java.time.LocalDate;

import pw.p3.data.Dificultad;

/**
 * 
 * Clase que implementa un bean de reservas
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class ReservationBean implements java.io.Serializable {

	/* Atributos */
	
	private static final long serialVersionUID = 1L;
	
	private Integer ninos;
	private Integer adultos;
	private Integer duracion;
	private LocalDate fecha;
	private String dificultad;
	
	/* Getters y Setters */
	
	public Integer getNinos() {
		return ninos;
	}
	public void setNinos(Integer nin) {
		this.ninos = nin;
	}
	public Integer getAdultos() {
		return adultos;
	}
	public void setAdultos(Integer adult) {
		this.adultos = adult;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer dur) {
		this.duracion = dur;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dif) {
		this.dificultad = dif;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate date) {
		this.fecha = date;
	}
}
