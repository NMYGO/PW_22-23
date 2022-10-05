package pw.p1.factory_reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
				+ ", precio=" + precio + ", descuento=" + descuento + "]";
	}
}
