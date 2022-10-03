package pw.p1.factory_reserva;

import java.util.Date;

public class RInfantil extends Reserva{
	
	/* Atributos */
	
	private int participantes;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public RInfantil() {}
	
	/* Getters y setters */
	
	public int getParticipantes() {
		return participantes;
	}
	public void setParticipantes(int part) {
		this.participantes=part;
	}
	
	/* Otros metodos */
	
	public String toString() {
		String info = super.toString();
			info += " -> Reserva Infantil";
			return info;
	}
}
