package factory_reserva;

import java.util.Date;

public abstract class RFamiliar extends ReservaAbs{
	protected int ninos;
	protected int adultos;
	
	public RFamiliar() {
		
	}
	
	public int getNinos() {
		return ninos;
	}
	public void setninos(int nino) {
		this.ninos=nino;
	}
	
	public int getadultos() {
		return adultos;
	}
	public void setadultos(int adul) {
		this.adultos=adul;
	}
}
