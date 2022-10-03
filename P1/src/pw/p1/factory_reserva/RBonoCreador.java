package pw.p1.factory_reserva;

public class RBonoCreador extends ReservaCreador {
	
	/**
	 * Crea una reserva infantil
	 */
	@Override
	public RInfantil creaRInf() {
		RInfantil reserva = new RInfantil();
		reserva.setDesc(0);
		reserva.setDur(0);
		reserva.setFecha(null);
		reserva.setParticipantes(0);
		reserva.setPista(0);
		reserva.setPrecio(0);
		reserva.setUsuario(0);
		return reserva;
	}
	
	/** 
	 * Crea una reserva familiar
	 *  */
	@Override
	public RFamiliar creaRFam() {
		RFamiliar reserva = new RFamiliar();
		reserva.setDesc(0);
		reserva.setDur(0);
		reserva.setFecha(null);
		reserva.setadultos(1);
		reserva.setninos(0);
		reserva.setPista(0);
		reserva.setPrecio(0);
		reserva.setUsuario(0);
		return reserva;
	}
	
	/** 
	 * Crea una reserva adulta
	 *  */
	@Override
	public RAdulto creaRAdu() {
		RAdulto reserva = new RAdulto();
		reserva.setDesc(0);
		reserva.setDur(0);
		reserva.setFecha(null);
		reserva.setParticipantes(0);
		reserva.setPista(0);
		reserva.setPrecio(0);
		reserva.setUsuario(0);
		return reserva;
	}
}
