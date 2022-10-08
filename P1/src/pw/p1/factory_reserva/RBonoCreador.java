package pw.p1.factory_reserva;

import java.time.LocalDate;
import java.util.ArrayList;

public class RBonoCreador extends ReservaCreador {
	public enum Tipo {
		INFANTIL, FAMILIAR, ADULTO
	};
	
	/* Atributos */
	private int id;
	private int sesion;
	private String bUsuario;
	private Tipo tipo;
	public ArrayList<Reserva> arrayReservas = new ArrayList<Reserva>();
	/**
	 * Funciones get y set de los atributos
	 */
	public int getId() {
		return id;
	}
	public void setId(int ID) {
		this.id=ID;
	}
	public int getSesion() {
		return sesion;
	}
	public void setSesion(int ses) {
		this.sesion=ses;
	}
	public String getUsuario() {
		return bUsuario;
	}
	public void setUsuario(String user) {
		this.bUsuario=user;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo type) {
		this.tipo=type;
	}
	/**
	 * Crea una reserva infantil
	 */
	@Override
	public RInfantil creaRInf(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos) {
		RInfantil reserva = new RInfantil(usuario, fecha, duracion, pista, precio, descuento, ninos);
		//usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva familiar
	 *  */
	@Override
	public RFamiliar creaRFam(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos, int adultos) {
		RFamiliar reserva = new RFamiliar(usuario, fecha, duracion, pista, precio, descuento, ninos, adultos);
		//usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva adulta
	 *  */
	@Override
	public RAdulto creaRAdu(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int adultos) {
		RAdulto reserva = new RAdulto(usuario, fecha, duracion, pista, precio, descuento, adultos);
		//usuario=reserva.getUsuario();		
		return reserva;
	}
}
