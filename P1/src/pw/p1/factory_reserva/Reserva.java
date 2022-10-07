package pw.p1.factory_reserva;

import java.time.LocalDate;
import java.util.ArrayList;

import pw.p1.classes.Kart;
import pw.p1.classes.Pista.Dificultad;

public abstract class Reserva {
	
	/* Atributos */
	
	protected String usuario;
	protected LocalDate fecha;
	protected int duracion;
	protected String pista;
	protected float precio;
	protected int descuento;
	
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
	
	public Reserva(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento) {
		this.usuario = usuario;
		this.fecha = fecha;
		this.duracion = duracion;
		this.pista = pista;
		this.precio = precio;
		this.descuento = descuento;
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
	public void setDur(int dura) {
		this.duracion=dura;
	}
	
	public String getPista() {
		return pista;
	}
	public void setPista(String pist) {
		this.pista=pist;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(int prec) {
		this.precio=prec;
	}
	
	public int getDesc() {
		return descuento;
	}
	public void setDesc(int desc) {
		this.descuento=desc;
	}
	
	/* Otros metodos */
	
	@Override
	public String toString() {
		return "Reserva [usuario=" + usuario + ", fecha=" + fecha + ", duracion=" + duracion + ", pista=" + pista
				+ ", precio=" + precio + ", descuento=" + descuento;
	}
}
