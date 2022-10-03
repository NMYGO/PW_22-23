package pw.p1.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase Usuario
 * */

public class Usuario {
	
	/* Atributos */
	
	private char nombre;
	private char apellidos;
	private LocalDate nacimiento;
	private LocalDate inscripcion = LocalDate.now();
	private char correo;
	
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
	
	public Usuario(Character nombre, Character apellidos, LocalDate nacimiento, Character correo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.correo = correo;
	}
	
	/* Getters y setters */

	public char getNombre() {
		return nombre;
	}

	public void setNombre(char nombre) {
		this.nombre = nombre;
	}

	public char getApellidos() {
		return apellidos;
	}

	public void setApellidos(char apellidos) {
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

	public char getCorreo() {
		return correo;
	}

	public void setCorreo(char correo) {
		this.correo = correo;
	}
	
	/* Otros metodos */

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos 
				+ ", nacimiento=" + nacimiento + ", inscripcion=" + inscripcion 
				+ ", correo=" + correo + "]";
	}
	
	public void calcularAntiguedad() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu");
		String ahora = (LocalDate.now()).format(formateador);
		String sInscripcion = inscripcion.format(formateador);
		int antiguedad = Integer.parseInt(ahora) - Integer.parseInt(sInscripcion);
			System.out.println("Nº de años registrado: " + antiguedad);
	}
}

