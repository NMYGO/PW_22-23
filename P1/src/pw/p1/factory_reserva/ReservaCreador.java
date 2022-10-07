package pw.p1.factory_reserva;

import java.time.LocalDate;

/**
 * La factoria abstracta para crear una reserva
 * */

public abstract class ReservaCreador {
	
	/* Metodos factoria para cada producto */
		
	/** 
	 * Crea una reserva infantil
	 *  */
	
	public abstract RInfantil creaRInf(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos);
	
	/** 
	 * Crea una reserva familiar
	 *  */
	
	public abstract RFamiliar creaRFam();
	
	/** 
	 * Crea una reserva adulta
	 *  */
	
	public abstract RAdulto creaRAdu();
}
