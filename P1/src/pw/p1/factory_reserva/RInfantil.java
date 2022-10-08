package pw.p1.factory_reserva;

import java.time.LocalDate;

public class RInfantil extends Reserva{
	
	/* Atributos */
	
	private int ninos;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public RInfantil() {}
	
	/**
	 * Constructor parametrizado
	 * @param ninos Participantes niños de la reserva infantil
	 * */
	
	public RInfantil(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos) {
		super(usuario, fecha, duracion, pista, precio, descuento);
		this.ninos = ninos;
	}
	
	/* Getters y setters */
	
	public int getParticipantes() {
		return ninos;
	}
	public void setParticipantes(int ninos) {
		this.ninos = ninos;
	}
	
	/* Otros metodos */
	
	public String toString() {
		String info = super.toString();
			info += ", participantes niños=" + ninos+ ", participantes adultos=0] -> Reserva Infantil\n";
			return info;
	}
}
