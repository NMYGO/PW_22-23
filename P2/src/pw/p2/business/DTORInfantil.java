package pw.p2.business;

import java.time.LocalDate;

import pw.p2.data.Reserva;
import pw.p2.data.Dificultad;

/**
 * 
 * DTO de las reservas de tipo infantil
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DTORInfantil extends Reserva{
	
	/* Atributos */
	
	private Integer ninos;
	private Dificultad tipo;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public DTORInfantil() {}
	
	/**
	 * Constructor parametrizado
	 * @param usuario Usuario que hace la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista en la que se hace la reserva
	 * @param precio Precio de la reserva
	 * @param descuento Descuento aplicado al precio
	 * @param ninos Numero de participantes niños
	 * @param tipo Tipo de dificultad de reserva
	 **/
	
	public DTORInfantil(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer ninos, Dificultad tipo) {
		super(usuario, fecha, duracion, pista, precio, descuento);
		this.ninos = ninos;
		this.tipo = tipo;
	}
	
	/* Getters y setters */
	
	public int getParticipantes() {
		return ninos;
	}
	public void setParticipantes(int ninos) {
		this.ninos = ninos;
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
			info += ", participantes niños=" + ninos+ ", participantes adultos=0] -> Reserva Infantil\n";
			return info;
	}
}
