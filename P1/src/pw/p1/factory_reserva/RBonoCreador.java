package pw.p1.factory_reserva;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * La factoria concreta que realiza la reserva bono
 * */

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
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public RBonoCreador() {}
	
	/**
	 * Constructor parametrizado
	 * @param id Identificador del kart
	 * @param tipo Tipo de kart
	 * @param estado Estado del kart
	 * */
	
	public RBonoCreador(Integer id, Integer sesion, String bUsuario, Tipo tipo) {
		this.id = id;
		this.sesion = sesion;
		this.bUsuario = bUsuario;
		this.tipo = tipo;
	}
	
	/* Getters y setters */
	
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
	
	/* Otros metodos */
	
	@Override
	public String toString() {
		return "Bono [id=" + id + ", sesion=" + sesion + ", bUsuario=" + bUsuario + ", tipo=" + tipo + "] :"
				+ ", arrayReservas=\n" + arrayReservas;
	}
	
	/**
	 * Crea una reserva bono infantil
	 **/
	
	@Override
	public RInfantil creaRInf(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos) {
		RInfantil reserva = new RInfantil(usuario, fecha, duracion, pista, precio, descuento, ninos);
		//usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva bono familiar
	 **/
	
	@Override
	public RFamiliar creaRFam(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int ninos, int adultos) {
		RFamiliar reserva = new RFamiliar(usuario, fecha, duracion, pista, precio, descuento, ninos, adultos);
		//usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva bono adulta
	 **/
	
	@Override
	public RAdulto creaRAdu(String usuario, LocalDate fecha, int duracion, String pista, float precio, int descuento, int adultos) {
		RAdulto reserva = new RAdulto(usuario, fecha, duracion, pista, precio, descuento, adultos);
		//usuario=reserva.getUsuario();		
		return reserva;
	}
}
