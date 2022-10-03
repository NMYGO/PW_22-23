package pw.p1.factory_reserva;

public class RBonoCreador extends ReservaCreador {
	int id;
	int sesion;
	int usuario;
	/**
	 * Crea una reserva infantil
	 */
	@Override
	public RInfantil creaRInf() {
		RInfantil reserva = new RInfantil();
		usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva familiar
	 *  */
	@Override
	public RFamiliar creaRFam() {
		RFamiliar reserva = new RFamiliar();
		usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva adulta
	 *  */
	@Override
	public RAdulto creaRAdu() {
		RAdulto reserva = new RAdulto();
		usuario=reserva.getUsuario();		
		return reserva;
	}
}
