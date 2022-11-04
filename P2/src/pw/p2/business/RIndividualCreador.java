package pw.p2.business;

import java.time.LocalDate;

import pw.p2.data.Dificultad;

/**
 * 
 * La factoria concreta que realiza la reserva individual
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class RIndividualCreador extends ReservaCreador{
	
	/**
	 * Crea una reserva individual infantil
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param ninos Integer con el numero de participantes niños de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 * @return Devuelve la reserva infantil realizada
	 **/
	
	@Override
	public DTORInfantil creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo) {
		DTORInfantil reserva = new DTORInfantil(usuario, fecha, duracion, pista, precio, descuento, ninos, tipo);
		return reserva;
	}
	
	/**
	 * Crea una reserva individual familiar
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param adultos Integer con el numero de participantes adultos de la reserva
	 * @param ninos Integer con el numero de participantes niños de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 * @return Devuelve la reserva familiar realizada
	 **/
	
	@Override
	public DTORFamiliar creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo) {
		DTORFamiliar reserva = new DTORFamiliar(usuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo);
		return reserva;
	}
	
	/**
	 * Crea una reserva individual adulta
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param adultos Integer con el numero de participantes adultos de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 * @return Devuelve la reserva adulto realizada
	 **/
	
	@Override
	public DTORAdulto creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo) {
		DTORAdulto reserva = new DTORAdulto(usuario, fecha, duracion, pista, precio, descuento, adultos, tipo);
		return reserva;
	}
}
