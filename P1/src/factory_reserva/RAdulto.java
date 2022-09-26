package factory_reserva;

import java.util.Date;

public abstract class RAdulto extends ReservaAbs{
	int participantes;
	
	public RAdulto() {
		
	}
	
	public int getPartipantes() {
		return participantes;
	}
	public void setParticipantes(int part) {
		this.participantes=part;
	}
}
