package pw.p1.factory_reserva;

import java.time.LocalDate;

/**
 * La factoria concreta que realiza la reserva individual
 * */

public class RIndividualCreador extends ReservaCreador{
	
	@Override
	public RInfantil creaRInf(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos) {
		RInfantil reserva = new RInfantil(usuario, fecha, duracion, pista, precio, descuento, ninos);
		return reserva;
	}
	
	@Override
	public RFamiliar creaRFam() {
		RFamiliar reserva = new RFamiliar();
		return reserva;
	}
	
	@Override
	public RAdulto creaRAdu() {
		RAdulto reserva = new RAdulto();
		return reserva;
	}
}
