package pw.p3.business.reservation;

import pw.p3.data.Dificultad;

import java.time.LocalDate;

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

public class RIndividualCreator extends ReservationCreator{
	
	/**
	 * Reserva individual infantil
	 */
	
	@Override
	public RInfantileDTO creaRInf(Integer idReserva, String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo) {
		RInfantileDTO reserva = new RInfantileDTO(idReserva, usuario, fecha, duracion, pista, precio, descuento, ninos, tipo);
		return reserva;
	}
	
	/**
	 * Reserva individual familiar
	 */
	
	@Override
	public RFamiliarDTO creaRFam(Integer idReserva, String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo) {
		RFamiliarDTO reserva = new RFamiliarDTO(idReserva, usuario, fecha, duracion, pista, precio, descuento, adultos, ninos, tipo);
		return reserva;
	}
	
	/**
	 * Reserva individual adulta
	 */
	
	@Override
	public RAdultDTO creaRAdu(Integer idReserva, String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo) {
		RAdultDTO reserva = new RAdultDTO(idReserva, usuario, fecha, duracion, pista, precio, descuento, adultos, tipo);
		return reserva;
	}
}
