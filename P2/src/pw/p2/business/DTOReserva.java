package pw.p1.factory_reserva;

import java.time.LocalDate;

/**
 * 
 * Clase común para todas las reservas individuales y de bonos
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public abstract class Reserva {
	
	/* Atributos */
	
	protected String usuario;
	protected LocalDate fecha;
	protected int duracion;
	protected String pista;
	protected float precio;
	protected int descuento;
	protected int idReserva;
	protected int idBono;
	protected int adultos;
	protected int niños;
	protected int dificultad;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public Reserva() {}
	
	/**
	 * Constructor parametrizado
	 * @param usuario Usuario de la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista de la reserva
	 * @param precio Precio de la reserva
	 * @param descuento Descuento de la reserva
	 * */
	
	public Reserva(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int idReserva, int idBono, int adultos, int niños, int dificultad) {
		this.usuario = usuario;
		this.fecha = fecha;
		this.duracion = duracion;
		this.pista = pista;
		this.precio = precio;
		this.descuento = descuento;
		this.idReserva = idReserva; 
		this.idBono = idBono; 
		this.adultos = adultos; 
		this.niños = niños; 
		this.dificultad = dificultad;
	}
	
	/* Getters y setters */
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String user) {
		this.usuario=user;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate date) {
		this.fecha=date;
	}
	
	public int getDur() {
		return duracion;
	}
	public void setDur(int duracion) {
		this.duracion = duracion;
	}
	
	public String getPista() {
		return pista;
	}
	public void setPista(String pista) {
		this.pista = pista;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public int getDesc() {
		return descuento;
	}
	public void setDesc(int descuento) {
		this.descuento = descuento;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idREserva = idReserva;
	}
	public int getIdBono() {
		return idBono;
	}
	public void setIdBono(int idBono) {
		this.idBono = idBono;
	}
	public int getAdultos() {
		return adultos;
	}
	public void setAdultos(int adultos) {
		this.adultos = adultos;
	}
	public int getNiños() {
		return niños;
	}
	public void setNiños(int niños) {
		this.niños = niños;
	}
	public int getDificultad() {
		return dificultad;
	}
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
	
	/* Otros metodos */
	
	@Override
	public String toString() {
		return "Reserva [usuario=" + usuario + ", fecha=" + fecha + ", duracion=" + duracion + ", pista=" + pista
				+ ", precio=" + precio + ", descuento=" + descuento;
	}
}
