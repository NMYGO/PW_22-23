package pw.p1.factory_reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * La factoria abstracta para crear una reserva
 * */

public abstract class ReservaCreador {
	
	/* Metodos factoria para cada producto */
		
	/** 
	 * Crea una reserva infantil
	 *  */
	
	public abstract RInfantil creaRInf();
	
	/** 
	 * Crea una reserva familiar
	 *  */
	
	public abstract RFamiliar creaRFam();
	
	/** 
	 * Crea una reserva adulta
	 *  */
	
	public abstract RAdulto creaRAdu();
}
