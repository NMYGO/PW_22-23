package pw.p2.business;

import pw.p2.data.Estado;

/**
 * 
 * DTO Kart
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class DTOKart {
	
	/* Atributos */
	
	private int id;	
	private boolean tipo; //TRUE -> NIÑO - FALSE -> ADULTO	
	private Estado estado;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public DTOKart() {}
	
	/**
	 * Constructor parametrizado
	 * @param id Identificador del kart
	 * @param tipo Tipo de kart
	 * @param estado Estado del kart
	 * */
	
	public DTOKart(Integer id, Boolean tipo, Estado estado) {
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
	}
	
	/* Getters y setters */
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isTipo() {
		return tipo;
	}
	
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/* Otros metodos */
	/**
	 * Funcion para convertir los parámetros del kart a un string
	 */
	@Override
	public String toString() {
		return "Kart [id=" + id + ", tipo=" + tipo + ", estado=" + estado + "]\n";
	}
}
