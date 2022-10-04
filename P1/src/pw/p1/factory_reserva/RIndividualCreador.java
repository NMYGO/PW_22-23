package pw.p1.factory_reserva;

/**
 * La factoria concreta que realiza la reserva individual
 * */

public class RIndividualCreador extends ReservaCreador{
	
	@Override
	public RInfantil creaRInf() {
		RInfantil reserva = new RInfantil();
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
