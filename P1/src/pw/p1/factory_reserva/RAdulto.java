package pw.p1.factory_reserva;

import java.util.Date;

public class RAdulto extends Reserva{
	
	/* Atributos */
	
	int participantes;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public RAdulto() {}
	
	/* Getters y setters */
	
	public int getPartipantes() {
		return participantes;
	}
	public void setParticipantes(int part) {
		this.participantes=part;
	}
	
	/* Otros metodos */
	
	public String toString() {
		String info = super.toString();
			info += " -> Reserva Adulto\n";
			return info;
	}
}
