package pw.p2.business;

import pw.p2.data.Reserva;
import pw.p2.data.Dificultad;
import java.time.LocalDate;
import java.util.ArrayList;

public class DTOBono{
	
	/* Atributos */
	
	private int id;
	private int sesion;
	private String bUsuario;
	private Dificultad tipo;
	private LocalDate fcaducidad;
	public ArrayList<Reserva> arrayReservas = new ArrayList<Reserva>();
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public DTOBono() {}
	
	/**
	 * Contructor parametrizado
	 * @param id
	 * @param sesion
	 * @param bUsuario
	 * @param tipo
	 * @param fecha_de_caducidad
	 */
	
	public DTOBono(Integer sesion, String bUsuario, Dificultad tipo, LocalDate fcaducidad) {
		this.sesion = sesion;
		this.bUsuario = bUsuario;
		this.tipo = tipo;
		this.fcaducidad = fcaducidad;
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

	@Override
	public String toString() {
		return "Bono [id=" + id + ", sesion=" + sesion + ", bUser=" + bUsuario + ", tipo=" + tipo + ", fecha de caducidad=" + fcaducidad + "]\n";
	}
	
}
