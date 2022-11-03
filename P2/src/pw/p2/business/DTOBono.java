package pw.p2.business;

import pw.p2.data.Reserva;
import pw.p2.data.Dificultad;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * DTO de bonos
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DTOBono{
	
	/* Atributos */
	
	protected Integer idBono;
	protected Integer sesion;
	protected String bUsuario;
	protected Dificultad tipo;
	protected LocalDate fcaducidad;
	public ArrayList<Reserva> arrayReservas = new ArrayList<Reserva>();//ELIMINAR
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public DTOBono() {}
	
	/**
	 * Contructor parametrizado
	 * @param id
	 * @param sesion
	 * @param bUsuario
	 * @param tipo
	 **/
	
	public DTOBono(Integer sesion, String bUsuario, Dificultad tipo) {
		this.sesion = sesion;
		this.bUsuario = bUsuario;
		this.tipo = tipo;
		this.fcaducidad = LocalDate.now().plusYears(1);
	}
	
	/**
	 * Contructor parametrizado
	 * @param id
	 * @param sesion
	 * @param bUsuario
	 * @param tipo
	 * @param fecha_de_caducidad
	 **/
	
	public DTOBono(Integer sesion, String bUsuario, Dificultad tipo, LocalDate fcaducidad) {
		this.sesion = sesion;
		this.bUsuario = bUsuario;
		this.tipo = tipo;
		this.fcaducidad = fcaducidad;
	}
	
	/* Getters y setters */
	
	public int getId() {
		return idBono;
	}
	
	public void setId(int idBono) {
		this.idBono = idBono;
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
	
	public Dificultad getTipo() {
		return tipo;
	}
	
	public void setTipo(Dificultad tipo) {
		this.tipo = tipo;
	}
	
	public LocalDate getFcaducidad() {
		return fcaducidad;
	}

	public void setFcaducidad(LocalDate fcaducidad) {
		this.fcaducidad = fcaducidad;
	}
	
	public ArrayList<Reserva> getArrayReservas() {
		return arrayReservas;
	}

	public void setArrayReservas(ArrayList<Reserva> arrayReservas) {
		this.arrayReservas = arrayReservas;
	}
	
	/* Otros metodos */
	
	/**
	 * Funcion toString 
	 **/
	
	@Override
	public String toString() {
		return "Bono [id=" + idBono + ", sesion=" + sesion + ", bUser=" + bUsuario + ", tipo=" + tipo + ", fecha de caducidad=" + fcaducidad + "]\n";
	}
}
