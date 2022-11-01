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
 */

public abstract class ReservaCreador {
	
	/* Metodos factoria para cada producto */
		
	/** 
	 * Crea una reserva infantil
	 *  */
	
	public abstract DTORInfantil creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo);
	
	/** 
	 * Crea una reserva familiar
	 *  */
	
	public abstract DTORFamiliar creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo);
	
	/** 
	 * Crea una reserva adulta
	 *  */
	
	public abstract DTORAdulto creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo);
}
