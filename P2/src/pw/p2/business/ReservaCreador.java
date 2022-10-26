package pw.p2.business;

import pw.p2.data.RInfantil;
import pw.p2.data.RFamiliar;
import pw.p2.data.RAdulto;
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
 */

public abstract class ReservaCreador {
	
	/* Metodos factoria para cada producto */
		
	/** 
	 * Crea una reserva infantil
	 *  */
	
	public abstract RInfantil creaRInf(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos);
	
	/** 
	 * Crea una reserva familiar
	 *  */
	
	public abstract RFamiliar creaRFam(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos, int adultos);
	
	/** 
	 * Crea una reserva adulta
	 *  */
	
	public abstract RAdulto creaRAdu(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int adultos);
}
