package pw.p3.business.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDTO {
	
	/* Atributos */
	
	protected String nombre;
	protected String apellidos;
	protected LocalDate nacimiento;
	protected LocalDate inscripcion;
	protected Integer antiguedad;
	protected String correo;
	protected String password;
	protected Boolean administrador;
	protected Integer nreservas;
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public UserDTO() {}
	
	/**
	 * Constructor parametrizado
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param nacimiento Nacimiento del usuario
	 * @param correo Correo unico del usuario
	 **/
	
	public UserDTO(String nombre, String apellidos, LocalDate nacimiento, String correo, String pass) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.inscripcion = LocalDate.now();
		this.correo = correo;
		this.password = pass;
	}
	
	public UserDTO(String nombre, String apellidos, LocalDate nacimiento, LocalDate inscripcion, String correo, String pass, Boolean administrador) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.inscripcion = inscripcion;
		this.correo = correo;
		this.password = pass;
		this.administrador = administrador;
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
	public Integer getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String pass) {
		this.password = pass;
	}
	public Boolean getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	public Integer getNreservas() {
		return nreservas;
	}

	public void setNreservas(Integer nreservas) {
		this.nreservas = nreservas;
	}
	
	/* Otros metodos */

	/**
	 * Funcion toString 
	 **/
	
	@Override
	public String toString() {
		return "UserDTO [nombre=" + nombre + ", apellidos=" + apellidos + ", nacimiento=" + nacimiento
				+ ", inscripcion=" + inscripcion + ", correo=" + correo + ", administrador=" + administrador + "]";
	}
}
