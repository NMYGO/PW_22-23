package pw.p2.business;

import java.time.LocalDate;

import pw.p2.data.Dificultad;

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

public abstract class ReservaCreador {
	
	/* Metodos factoria para cada producto */
		
	/** 
	 * Crea una reserva infantil
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param ninos Integer con el numero de participantes niños de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 **/
	
	public abstract DTORInfantil creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo);
	
	/** 
	 * Crea una reserva familiar
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param adultos Integer con el numero de participantes adultos de la reserva
	 * @param ninos Integer con el numero de participantes niños de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 **/
	
	public abstract DTORFamiliar creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo);
	
	/** 
	 * Crea una reserva adulta
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param adultos Integer con el numero de participantes adultos de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 **/
	
	public abstract DTORAdulto creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo);
}
