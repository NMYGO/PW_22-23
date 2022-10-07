package pw.p1.factory_reserva;

import java.time.LocalDate;

public class RFamiliar extends Reserva{
	
	/* Atributos */
	
	protected int ninos;
	protected int adultos;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public RFamiliar() {}
	
	/**
	 * Constructor parametrizado
	 * @param ninos Participantes niños de la reserva familiar
	 * @param adultos Participantes adultos de la reserva familiar
	 * */
	
	public RFamiliar(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos, int adultos) {
		super(usuario, fecha, duracion, pista, precio, descuento);
		this.ninos = ninos;
		this.adultos = adultos;
	}
	
	/* Getters y setters */
	
	public int getNinos() {
		return ninos;
	}
	public void setninos(int ninos) {
		this.ninos = ninos;
	}
	
	public int getadultos() {
		return adultos;
	}
	public void setadultos(int adultos) {
		this.adultos = adultos;
	}
	
	/* Otros metodos */
	
	public String toString() {
		String info = super.toString();
			info += ", participantes niños=" + ninos + ", participantes adultos=" + adultos + "] -> Reserva Familiar\n";
			return info;
	}
}
