package pw.p1.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Clase Usuario
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class Usuario {
	
	/* Atributos */
	
	private String nombre;
	private String apellidos;
	private LocalDate nacimiento;
	private LocalDate inscripcion;
	private String correo;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public Usuario() {}
	
	/**
	 * Constructor parametrizado
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param nacimiento Nacimiento del usuario
	 * @param correo Correo unico del usuario
	 * */
	
	public Usuario(String nombre, String apellidos, LocalDate nacimiento, String correo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.inscripcion = LocalDate.now();
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
	 * Pasa los atributos del objeto usuario a un string
	 */

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos 
				+ ", nacimiento=" + nacimiento + ", inscripcion=" + inscripcion 
				+ ", correo=" + correo + "]\n";
	}
	
	/**
	 * Calcula la antiguedad del usuario
	 */
	
	public void calcularAntiguedad() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu");
		String ahora = (LocalDate.now()).format(formateador);
		String sInscripcion = inscripcion.format(formateador);
		int antiguedad = Integer.parseInt(ahora) - Integer.parseInt(sInscripcion);
			System.out.println("Nº de años registrado: " + antiguedad);
	}
}

