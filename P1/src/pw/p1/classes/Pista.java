package pw.p1.classes;

import java.util.ArrayList;

/**
 * Clase Pista
 * */

public class Pista {
		
	public enum Dificultad {
		INFANTIL, FAMILIAR, ADULTO
	};
	
	/* Atributos */
	
	private char nombre;	
	private boolean estado;	//TRUE -> RESERVADO Si
	private Dificultad dificultad;
	private int maxkarts;
	private ArrayList<Kart> lkart = new ArrayList<Kart>();
	
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
	
	public Pista(Character nombre, Boolean estado, Dificultad dificultad, Integer maxkarts) {
		this.nombre = nombre;
		this.estado = estado;
		this.dificultad = dificultad;
		this.maxkarts = maxkarts;
	}
	
	/* Getters y setters */

	public char getNombre() {
		return nombre;
	}

	public void setNombre(char nombre) {
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
				+ ", lkart=" + lkart + "]";
	}
	
	public ArrayList<Kart> consultarKartsDisponibles() {
		ArrayList<Kart> dkart = new ArrayList<Kart>();
		for(int i = 0; i < lkart.size(); i++) {
			if((lkart.get(i)).getEstado() == Kart.Estado.DISPONIBLE) {
				dkart.add(lkart.get(i));
			}
		}
		return dkart;
	}

	public void asociarKartAPista(Kart kart) {
		if(this.getDificultad() == Dificultad.INFANTIL && kart.isTipo()) {
			lkart.add(kart);
		}else if(this.getDificultad() == Dificultad.ADULTO && !kart.isTipo()) {
			lkart.add(kart);
		}else if(this.getDificultad() == Dificultad.FAMILIAR) {
			lkart.add(kart);
		}
	}
}
