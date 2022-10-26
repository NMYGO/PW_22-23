package pw.p2.data;
import java.time.LocalDate;

/**
 * 
 * Clase de reservas de tipo adulto
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

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
	 * @param usuario usuario que hace la reserva
	 * @param fecha fecha de la reserva
	 * @param duracion duración de la reserva
	 * @param pista pista en la que se hace la reserva
	 * @param precio precio de la reserva
	 * @param descuento si es necesario descuento aplicado al precio
	 * @param adultos número de participantes
	 */
	
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
			info +=  ", participantes niños=0, participantes adultos=" + adultos + "] -> Reserva Adulto\n";
			return info;
	}
}
