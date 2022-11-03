package pw.p2.business;

import java.time.LocalDate;

/**
 * 
 * DTO usuario
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DTOUsuario {
	
	/* Atributos */
	
	protected String nombre;
	protected String apellidos;
	protected LocalDate nacimiento;
	protected LocalDate inscripcion;
	protected String correo;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public DTOUsuario() {}
	
	/**
	 * Constructor parametrizado
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param nacimiento Nacimiento del usuario
	 * @param correo Correo unico del usuario
	 **/
	
	public DTOUsuario(String nombre, String apellidos, LocalDate nacimiento, String correo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.inscripcion = LocalDate.now();
		this.correo = correo;
	}
	
	public DTOUsuario(String nombre, String apellidos, LocalDate nacimiento, LocalDate inscripcion, String correo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.inscripcion = inscripcion;
		this.correo = correo;
	}
	
	/* Getters y setters */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	public LocalDate getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(LocalDate inscripcion) {
		this.inscripcion = inscripcion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/* Otros metodos */
	
	/**
	 * Funcion toString 
	 **/
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos 
				+ ", nacimiento=" + nacimiento + ", inscripcion=" + inscripcion 
				+ ", correo=" + correo + "]\n";
	}	
}
