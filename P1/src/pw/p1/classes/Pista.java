package pw.p1.classes;

import java.util.ArrayList;

import pw.p1.classes.Kart.Estado;

/**
 * Clase Pista
 * */

public class Pista {
		
	public enum Dificultad {
		INFANTIL, FAMILIAR, ADULTO
	};
	
	/* Atributos */
	
	private String nombre;	
	private boolean estado;	//TRUE -> RESERVADO - FALSE -> DISPONIBLE
	private Dificultad dificultad;
	private int maxkarts;
	private ArrayList<Kart> lkart;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public Pista() {}
	
	/**
	 * Constructor parametrizado
	 * @param nombre Nombre unico de la pista
	 * @param estado Estado de la pista
	 * @param dificultad Dificultad de la pista
	 * @param maxkarts Maximos karts de la pista
	 * */
	
	public Pista(String nombre, Boolean estado, Dificultad dificultad, Integer maxkarts) {
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

	public void setEstado(boolean estado) {
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

	@Override
	public String toString() {
		return "Pista [nombre=" + nombre + ", estado=" + estado + ", "
				+ "dificultad=" + dificultad + ", maxkarts=" + maxkarts 
				+ "]\n";
	}
	
	public ArrayList<Kart> consultarKartsDisponibles(ArrayList<Kart> arrayKarts) {
		ArrayList<Kart> dkart = new ArrayList<Kart>();
		for(int i = 0; i < arrayKarts.size(); i++) {
			if((arrayKarts.get(i)).getEstado() == Kart.Estado.DISPONIBLE) {
				dkart.add(arrayKarts.get(i));
			}
		}
		return dkart;
	}

	public boolean asociarKartAPista(Kart kart, Pista pista) {
		if(pista.getMaxkarts() == pista.lkart.size()) {
			return false;
		}
		if(pista.getDificultad() == Dificultad.INFANTIL && kart.isTipo()) {
			kart.setEstado(Estado.RESERVADO);
			pista.lkart.add(kart);
		}else if(pista.getDificultad() == Dificultad.ADULTO && !kart.isTipo()) {
			kart.setEstado(Estado.RESERVADO);
			pista.lkart.add(kart);
		}else if(pista.getDificultad() == Dificultad.FAMILIAR) {
			kart.setEstado(Estado.RESERVADO);
			pista.lkart.add(kart);
		}else {
			return false;
		}
		return true;
	}
}
