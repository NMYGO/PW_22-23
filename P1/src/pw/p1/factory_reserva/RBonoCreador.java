package pw.p1.factory_reserva;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * La factoria concreta que realiza la reserva bono
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */
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
	 * Contructor parametrizado
	 * @param id
	 * @param sesion
	 * @param bUsuario
	 * @param tipo
	 */
	
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSesion() {
		return sesion;
	}
	
	public void setSesion(int sesion) {
		this.sesion = sesion;
	}
	
	public String getbUsuario() {
		return bUsuario;
	}
	
	public void setbUsuario(String bUsuario) {
		this.bUsuario = bUsuario;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public ArrayList<Reserva> getArrayReservas() {
		return arrayReservas;
	}

	public void setArrayReservas(ArrayList<Reserva> arrayReservas) {
		this.arrayReservas = arrayReservas;
	}
	
	/* Otros metodos */

	@Override
	public String toString() {
		return "Bono [id=" + id + ", sesion=" + sesion + ", bUser=" + bUsuario + ", tipo=" + tipo + "]\n";
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
