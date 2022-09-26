package factory_reserva;

import java.util.Date;

public abstract class RInfantil extends ReservaAbs{
	protected int participantes;
	
	public RInfantil() {
	}
	
	public int getPartipantes() {
		return participantes;
	}
	public void setParticipantes(int part) {
		this.participantes=part;
	}
}
