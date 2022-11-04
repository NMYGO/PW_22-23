package pw.p2.business;

import java.time.LocalDate;

import pw.p2.data.Dificultad;

/**
 * 
 * La factoria para crear una reserva bono
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class RBonoCreador extends ReservaCreador {

	/**
	 * Crea una reserva bono infantil
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param ninos Integer con el numero de participantes niños de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 **/
	
	@Override
	public DTORInfantil creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo) {
		DTORInfantil reserva = new DTORInfantil(usuario, fecha, duracion, pista, precio, descuento, ninos, tipo);
		return reserva;
	}
	
	/** 
	 * Crea una reserva bono familiar
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
	
	@Override
	public DTORFamiliar creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo) {
		DTORFamiliar reserva = new DTORFamiliar(usuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo);
		return reserva;
	}
	
	/** 
	 * Crea una reserva bono adulta
	 * @param usuario String con el nombre de usuario
	 * @param fecha LocalDate con la fecha de reserva
	 * @param duracion Integer con la duracion de la reserva
	 * @param pista String con el nombre de pista
	 * @param precio Float con el precio de la reserva
	 * @param descuento Integer con el descuento aplicado a la reserva
	 * @param adultos Integer con el numero de participantes adultos de la reserva
	 * @param tipo Enum Dificultad con el tipo de reserva
	 **/
	
	@Override
	public DTORAdulto creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo) {
		DTORAdulto reserva = new DTORAdulto(usuario, fecha, duracion, pista, precio, descuento, adultos, tipo);
		return reserva;
	}
}
