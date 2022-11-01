package pw.p2.business;

import java.time.LocalDate;

import pw.p2.data.Dificultad;
import pw.p2.data.Reserva;

/**
 * 
 * Clase de las reservas de tipo familiar
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class DTORFamiliar extends Reserva{
	
	/* Atributos */
	
	protected int ninos;
	protected int adultos;
	protected Dificultad tipo;
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public DTORFamiliar() {}
	
	/**
	 * Constructor parametrizado
	 * @param ninos Participantes niños de la reserva familiar
	 * @param adultos Participantes adultos de la reserva familiar
	 * */
	
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

	public String toString() {
		String info = super.toString();
			info += ", participantes niños=" + ninos + ", participantes adultos=" + adultos + "] -> Reserva Familiar\n";
			return info;
	}
}
