package pw.p2.business;

import pw.p2.data.Pista;
import pw.p2.data.Kart;
import pw.p2.data.Dificultad;
import pw.p2.data.Estado;
import java.util.ArrayList;

public class DTOPista {
	
	/* Atributos */
	
	protected String nombre;	
	protected boolean estado;	//TRUE -> RESERVADO - FALSE -> DISPONIBLE
	protected Dificultad dificultad;
	protected int maxkarts;
	protected ArrayList<Kart> lkart;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public DTOPista() {}
	
	/**
	 * Constructor parametrizado
	 * @param nombre Nombre unico de la pista
	 * @param estado Estado de la pista
	 * @param dificultad Dificultad de la pista
	 * @param maxkarts Maximos karts de la pista
	 * */
	
	public DTOPista(String nombre, Boolean estado, Dificultad dificultad, Integer maxkarts) {
		this.nombre = nombre;
		this.estado = estado;
		this.dificultad = dificultad;
		this.maxkarts = maxkarts;
		this.lkart = new ArrayList<Kart>();
	}
	
	/* Getters y setters */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}

	public int getMaxkarts() {
		return maxkarts;
	}

	public void setMaxkarts(int maxkarts) {
		this.maxkarts = maxkarts;
	}

	public ArrayList<Kart> getLkart() {
		return lkart;
	}

	public void setLkart(ArrayList<Kart> lkart) {
		this.lkart = lkart;
	}
	
	/* Otros metodos */
	
	/**
	 * Pasa los parámetros del objeto pista a un string
	 */
	
	@Override
	public String toString() {
		return "Pista [nombre=" + nombre + ", estado=" + estado + ", "
				+ "dificultad=" + dificultad + ", maxkarts=" + maxkarts 
				+ "]\n";
	}
	
}
