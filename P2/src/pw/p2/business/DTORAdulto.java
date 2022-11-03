package pw.p2.business;
import java.time.LocalDate;

import pw.p2.data.Dificultad;
import pw.p2.data.Reserva;

/**
 * 
 * DTO de reservas de tipo adulto
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DTORAdulto extends Reserva{
	
	/* Atributos */
	
	private Integer adultos;
	private Dificultad tipo;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public DTORAdulto() {}
	
	/**
	 * Constructor parametrizado
	 * @param usuario usuario que hace la reserva
	 * @param fecha fecha de la reserva
	 * @param duracion duración de la reserva
	 * @param pista pista en la que se hace la reserva
	 * @param precio precio de la reserva
	 * @param descuento si es necesario descuento aplicado al precio
	 * @param adultos número de participantes
	 **/
	
	public DTORAdulto(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Dificultad tipo) {
		super(usuario, fecha, duracion, pista, precio, descuento);
		this.adultos = adultos;
		this.tipo = tipo;
	}
	
	/* Getters y setters */
	
	public int getPartipantes() {
		return adultos;
	}
	public void setParticipantes(int adultos) {
		this.adultos = adultos;
	}
	public Dificultad getTipo() {
		return tipo;
	}

	public void setTipo(Dificultad tipo) {
		this.tipo = tipo;
	}
	
	/* Otros metodos */
	
	/**
	 * Funcion toString 
	 **/
	
	public String toString() {
		String info = super.toString();
			info +=  ", participantes niños=0, participantes adultos=" + adultos + "] -> Reserva Adulto\n";
			return info;
	}
}
