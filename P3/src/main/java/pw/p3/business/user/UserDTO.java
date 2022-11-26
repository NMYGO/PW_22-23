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
	//protected String password;
	protected Boolean administrador;
	
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
	
	public UserDTO(String nombre, String apellidos, LocalDate nacimiento, String correo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.inscripcion = LocalDate.now();
		this.antiguedad = calcularAntiguedad(this.inscripcion);
		this.correo = correo;
	}
	
	public UserDTO(String nombre, String apellidos, LocalDate nacimiento, LocalDate inscripcion, String correo, Boolean administrador) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.inscripcion = inscripcion;
		this.antiguedad = calcularAntiguedad(this.inscripcion);
		this.correo = correo;
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
	public Boolean getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	
	/* Otros metodos */

	/**
	 * Funcion toString 
	 **/
	
	@Override
	public String toString() {
		return "UserDTO [nombre=" + nombre + ", apellidos=" + apellidos + ", nacimiento=" + nacimiento
				+ ", inscripcion=" + inscripcion + ", antiguedad=" + antiguedad + ", correo=" + correo
				+ ", administrador=" + administrador + "]";
	}
	
	/**
	 * Funcion para calcular la antiguedad de un usuario ¿¿ESTO ESTA BIEN AQUI??
	 **/
	
	private Integer calcularAntiguedad(LocalDate inscripcion) {
		DateTimeFormatter formateadorYY = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter formateadorMM = DateTimeFormatter.ofPattern("MM");
		String ahoraYY = (LocalDate.now()).format(formateadorYY);
		String sInscripcionYY = inscripcion.format(formateadorYY);
		String ahoraMM = (LocalDate.now()).format(formateadorMM);
		String sInscripcionMM = inscripcion.format(formateadorMM);
		int antiguedad = 12*(Integer.parseInt(ahoraYY) - Integer.parseInt(sInscripcionYY)) + (Integer.parseInt(ahoraMM) - Integer.parseInt(sInscripcionMM));
		return antiguedad;
	}
}
