package pw.p2.business;

import java.time.LocalDate;

import pw.p2.data.Dificultad;
import pw.p2.data.Reserva;

/**
 * 
 * DTO de las reservas de tipo familiar
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DTORFamiliar extends Reserva{
	
	/* Atributos */
	
	protected Integer ninos;
	protected Integer adultos;
	protected Dificultad tipo;
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public DTORFamiliar() {}
	
	/**
	 * Constructor parametrizado
	 * @param usuario Usuario que hace la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista en la que se hace la reserva
	 * @param precio Precio de la reserva
	 * @param descuento Descuento aplicado al precio
	 * @param adultos Numero de participantes adultos
	 * @param ninos Numero de participantes niños
	 * @param tipo Tipo de dificultad de reserva
	 **/
	
	public DTORFamiliar(String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer adultos, Integer ninos, Dificultad tipo) {
		super(usuario, fecha, duracion, pista, precio, descuento);
		this.ninos = ninos;
		this.adultos = adultos;
		this.tipo = tipo;
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
			info += ", participantes niños=" + ninos + ", participantes adultos=" + adultos + "] -> Reserva Familiar\n";
			return info;
	}
}
