package pw.p3.business.reservation;

import pw.p3.data.Dificultad;

import java.time.LocalDate;

/**
 * 
 *  La factoria abstracta para crear una reserva
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public abstract class ReservationCreator {
	
	/* Metodos factoria para cada producto */
		
	/**
	 * Reserva infantil
	 * @param usuario Usuario que realiza la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista de la reserva
	 * @param precio Precio de l reserva
	 * @param descuento Descuento aplicado a la reserva
	 * @param ninos Participantes niños de la reserva
	 * @param tipo Tipo de la reserva
	 * @return
	 */
	
	public abstract RInfantileDTO creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo);
	
	/**
	 * Reserva familiar
	 * @param usuario Usuario que realiza la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista de la reserva
	 * @param precio Precio de l reserva
	 * @param descuento Descuento aplicado a la reserva
	 * @param adultos Participantes adultos de la reserva
	 * @param ninos Participantes niños de la reserva
	 * @param tipo Tipo de la reserva
	 * @return
	 */
	
	public abstract RFamiliarDTO creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo);
	
	/**
	 * Reserva adulta
	 * @param usuario Usuario que realiza la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista de la reserva
	 * @param precio Precio de l reserva
	 * @param descuento Descuento aplicado a la reserva
	 * @param adultos Participantes adultos de la reserva
	 * @param tipo Tipo de la reserva
	 * @return
	 */
	
	public abstract RAdultDTO creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo);
}
