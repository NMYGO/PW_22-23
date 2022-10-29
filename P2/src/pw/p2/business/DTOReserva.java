package pw.p2.business;

import pw.p2.data.Tipo;
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

public class DTOReserva {
	
	/* Atributos */
	
	protected String correo;
	protected LocalDate fecha;
	protected int duracion;
	protected String pista;
	protected float precio;
	protected int descuento;
	protected int idReserva;
	protected int idBono;
	protected int adultos;
	protected int niños;
	protected Tipo tipo;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public DTOReserva() {}
	
	/**
	 * Constructor parametrizado
	 * @param usuario Usuario de la reserva
	 * @param fecha Fecha de la reserva
	 * @param duracion Duracion de la reserva
	 * @param pista Pista de la reserva
	 * @param precio Precio de la reserva
	 * @param descuento Descuento de la reserva
	 * */
	
	public DTOReserva(String correo, LocalDate fecha, Integer duracion, String pista, Float precio, Integer descuento, Integer idReserva, Integer idBono, Integer adultos, Integer niños, Tipo tipo) {
		this.correo = correo;
		this.fecha = fecha;
		this.duracion = duracion;
		this.pista = pista;
		this.precio = precio;
		this.descuento = descuento;
		this.idReserva = idReserva; 
		this.idBono = idBono; 
		this.adultos = adultos; 
		this.niños = niños; 
		this.tipo = tipo;
	}
	
	/* Getters y setters */
	
	public String getCorreo() {
		return correo;
	}
	public void setcorreo(String correo) {
		this.correo=correo;
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
	public void setDur(Integer duracion) {
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
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	public int getDesc() {
		return descuento;
	}
	public void setDesc(Integer descuento) {
		this.descuento = descuento;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}
	public int getIdBono() {
		return idBono;
	}
	public void setIdBono(Integer idBono) {
		this.idBono = idBono;
	}
	public int getAdultos() {
		return adultos;
	}
	public void setAdultos(Integer adultos) {
		this.adultos = adultos;
	}
	public int getNiños() {
		return niños;
	}
	public void setNiños(Integer niños) {
		this.niños = niños;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	/* Otros metodos */
	
	/**
	 * Pasa los parámetros del objeto reserva a un string
	 */
	
	@Override
	public String toString() {
		return "DTOReserva [correo=" + correo + ", fecha=" + fecha + ", duracion=" + duracion + ", pista=" + pista
				+ ", precio=" + precio + ", descuento=" + descuento + ", idReserva=" + idReserva + ", idBono=" + idBono
				+ ", adultos=" + adultos + ", niños=" + niños + ", tipo=" + tipo + "]";
	}

}
