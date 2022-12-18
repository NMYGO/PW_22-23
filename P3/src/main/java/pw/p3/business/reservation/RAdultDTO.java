package pw.p3.business.reservation;

import pw.p3.data.Dificultad;

import java.time.LocalDate;

/**
 * 
 * DTO Reserva Adulto
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class RAdultDTO extends Reservation{
	
	/* Atributos */
	
	private Integer adultos;
	private Dificultad tipo;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public RAdultDTO() {}
	
	/**
	 * Constructor parametrizado
	 * @param idReserva Identificador de la reserva
	 * @param usuario Usuario que hace la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista en la que se hace la reserva
	 * @param precio Precio de la reserva
	 * @param descuento Descuento aplicado al precio
	 * @param adultos Numero de participantes adultos
	 * @param tipo Tipo de dificultad de reserva
	 **/
	
	public RAdultDTO(Integer idReserva, String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo) {
		super(idReserva, usuario, fecha, duracion, pista, precio, descuento);
		this.adultos = adultos;
		this.tipo = tipo;
	}
	
	/* Getters y setters */
	
	public int getPartipantes() {
		return adultos;
	}
	public void setParticipantes(int adultos) {
		this.adultos = adultos;
	}
	public Dificultad getTipo() {
		return tipo;
	}

	public void setTipo(Dificultad tipo) {
		this.tipo = tipo;
	}
	
	/* Otros metodos */
	
	/**
	 * Funcion toString 
	 **/
	
	public String toString() {
		String info = super.toString();
			info +=  ", participantes niños=0, participantes adultos=" + adultos + "] -> Reserva Adulto\n";
			return info;
	}
}
