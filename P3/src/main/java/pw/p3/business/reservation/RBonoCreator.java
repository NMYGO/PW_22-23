package pw.p3.business.reservation;

import pw.p3.data.Dificultad;

import java.time.LocalDate;

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

public class RBonoCreator extends ReservationCreator {

	/**
	 * Reserva bono infantil
	 */
	
	@Override
	public RInfantileDTO creaRInf(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo) {
		RInfantileDTO reserva = new RInfantileDTO(usuario, fecha, duracion, pista, precio, descuento, ninos, tipo);
		return reserva;
	}
	
/**
 * Reserva bono familiar
 */
	
	@Override
	public RFamiliarDTO creaRFam(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo) {
		RFamiliarDTO reserva = new RFamiliarDTO(usuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo);
		return reserva;
	}
	
	/**
	 * Reserva bono adulta
	 */
	
	@Override
	public RAdultDTO creaRAdu(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo) {
		RAdultDTO reserva = new RAdultDTO(usuario, fecha, duracion, pista, precio, descuento, adultos, tipo);
		return reserva;
	}
}
