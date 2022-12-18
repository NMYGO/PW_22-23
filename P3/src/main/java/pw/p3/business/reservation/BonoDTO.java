package pw.p3.business.reservation;

import pw.p3.data.Dificultad;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * 
 * DTO Bono
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class BonoDTO{
	
	/* Atributos */
	
	protected Integer idBono;
	protected Integer sesion;
	protected String bUsuario;
	protected Dificultad tipo;
	protected LocalDate fcaducidad;
	public ArrayList<Reservation> arrayReservas = new ArrayList<Reservation>();//ELIMINAR
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public BonoDTO() {}
	
	/**
	 * Contructor parametrizado
	 * @param sesion Sesion nº de bono
	 * @param bUsuario Correo de usuario del bono
	 * @param tipo Tipo de bono
	 **/
	
	public BonoDTO(Integer sesion, String bUsuario, Dificultad tipo) {
		this.sesion = sesion;
		this.bUsuario = bUsuario;
		this.tipo = tipo;
		this.fcaducidad = LocalDate.now().plusYears(1);
	}
	
	/**
	 * Contructor parametrizado
	 * @param sesion Sesion nº de bono
	 * @param bUsuario Correo de usuario del bono
	 * @param tipo Tipo de bono
	 * @param fecha_de_caducidad Fecha de caducidad del bono
	 **/
	
	public BonoDTO(Integer sesion, String bUsuario, Dificultad tipo, LocalDate fcaducidad) {
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
	
	public ArrayList<Reservation> getArrayReservas() {
		return arrayReservas;
	}

	public void setArrayReservas(ArrayList<Reservation> arrayReservas) {
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
