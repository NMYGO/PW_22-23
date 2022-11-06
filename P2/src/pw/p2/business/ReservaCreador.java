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
	 * Reserva infantil
	 * @param usuario
	 * @param fecha
	 * @param duracion
	 * @param pista
	 * @param precio
	 * @param descuento
	 * @param ninos
	 * @param tipo
	 * @return
	 */
	
	public abstract DTORInfantil creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo);
	
	/**
	 * Reserva familiar
	 * @param usuario
	 * @param fecha
	 * @param duracion
	 * @param pista
	 * @param precio
	 * @param descuento
	 * @param adultos
	 * @param ninos
	 * @param tipo
	 * @return
	 */
	
	public abstract DTORFamiliar creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo);
	
	/**
	 * Reserva adulta
	 * @param usuario
	 * @param fecha
	 * @param duracion
	 * @param pista
	 * @param precio
	 * @param descuento
	 * @param adultos
	 * @param tipo
	 * @return
	 */
	
	public abstract DTORAdulto creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo);
}
