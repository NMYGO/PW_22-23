package pw.p1.factory_reserva;

import java.time.LocalDate;

/**
 * La factoria concreta que realiza la reserva individual
 * */

public class RIndividualCreador extends ReservaCreador{
	
	/**
	 * Crea una reserva individual infantil
	 **/
	
	@Override
	public RInfantil creaRInf(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos) {
		RInfantil reserva = new RInfantil(usuario, fecha, duracion, pista, precio, descuento, ninos);
		return reserva;
	}
	
	/**
	 * Crea una reserva individual familiar
	 **/
	
	@Override
	public RFamiliar creaRFam(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos, int adultos) {
		RFamiliar reserva = new RFamiliar(usuario, fecha, duracion, pista, precio, descuento, ninos, adultos);
		return reserva;
	}
	
	/**
	 * Crea una reserva individual adulta
	 **/
	
	@Override
	public RAdulto creaRAdu(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int adultos) {
		RAdulto reserva = new RAdulto(usuario, fecha, duracion, pista, precio, descuento, adultos);
		return reserva;
	}
}
