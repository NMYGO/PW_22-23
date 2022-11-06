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
	 * Reserva individual infantil
	 */
	
	@Override
	public DTORInfantil creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo) {
		DTORInfantil reserva = new DTORInfantil(usuario, fecha, duracion, pista, precio, descuento, ninos, tipo);
		return reserva;
	}
	
	/**
	 * Reserva individual familiar
	 */
	
	@Override
	public DTORFamiliar creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo) {
		DTORFamiliar reserva = new DTORFamiliar(usuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo);
		return reserva;
	}
	
	/**
	 * Reserva individual adulta
	 */
	
	@Override
	public DTORAdulto creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo) {
		DTORAdulto reserva = new DTORAdulto(usuario, fecha, duracion, pista, precio, descuento, adultos, tipo);
		return reserva;
	}
}
