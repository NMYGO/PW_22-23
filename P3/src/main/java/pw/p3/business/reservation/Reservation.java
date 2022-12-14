package pw.p3.business.reservation;

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
 **/

public abstract class Reservation {
	
	/* Atributos */
	
	protected int idReserva;
	protected String usuario;
	protected LocalDate fecha;
	protected int duracion;
	protected String pista;
	protected float precio;
	protected int descuento;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public Reservation() {}
	
	/**
	 * Constructor parametrizado
	 * @param idReserva Identificador de la reserva
	 * @param usuario Usuario de la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista de la reserva
	 * @param precio Precio de la reserva
	 * @param descuento Descuento de la reserva
	 **/
	
	public Reservation(Integer idReserva, String usuario, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento) {
		this.idReserva = idReserva;
		this.usuario = usuario;
		this.fecha = fecha;
		this.duracion = duracion;
		this.pista = pista;
		this.precio = precio;
		this.descuento = descuento;
	}
	
	/* Getters y setters */
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
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
	
	/* Otros metodos */
	
	/**
	 * Funcion toString 
	 **/
	
	@Override
	public String toString() {
		return "Reserva [usuario=" + usuario + ", fecha=" + fecha + ", duracion=" + duracion + ", pista=" + pista
				+ ", precio=" + precio + ", descuento=" + descuento;
	}
}
