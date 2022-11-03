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
 **/

public class DTOKart {
	
	/* Atributos */
	
	protected Integer id;	
	protected Boolean tipo; //TRUE -> NIÑO - FALSE -> ADULTO	
	protected Estado estado;
	protected String nombrePista;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 **/
	public DTOKart() {}
	
	/**
	 * Constructor parametrizado
	 * @param id Identificador del kart
	 * @param tipo Tipo de kart
	 * @param estado Estado del kart
	 **/
	
	public DTOKart(Integer id, Boolean tipo, Estado estado, String pista) {
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.nombrePista = pista;
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
	
	public String getNombrePista() {
		return nombrePista;
	}
	
	public void setNombrePista(String pista) {
		this.nombrePista=pista;
	}
	
	/* Otros metodos */
	
	/**
	 * Funcion toString 
	 **/
	
	@Override
	public String toString() {
		return "Kart [id=" + id + ", tipo=" + tipo + ", estado=" + estado + "]\n";
	}
}
