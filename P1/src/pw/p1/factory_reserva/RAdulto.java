package pw.p1.factory_reserva;

import java.time.LocalDate;

public class RAdulto extends Reserva{
	
	/* Atributos */
	
	int adultos;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public RAdulto() {}
	
	/**
	 * Constructor parametrizado
	 * @param ninos Participantes niños de la reserva infantil
	 * */
	
	public RAdulto(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int adultos) {
		super(usuario, fecha, duracion, pista, precio, descuento);
		this.adultos = adultos;
	}
	
	/* Getters y setters */
	
	public int getPartipantes() {
		return adultos;
	}
	public void setParticipantes(int adultos) {
		this.adultos = adultos;
	}
	
	/* Otros metodos */
	
	public String toString() {
		String info = super.toString();
			info += ", participantes=" + adultos + "] -> Reserva Adulto\n";
			return info;
	}
}
