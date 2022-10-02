package pw.p1.factory_reserva;

import java.util.Date;

public abstract class RFamiliar extends Reserva{
	
	/* Atributos */
	
	protected int ninos;
	protected int adultos;
	
	/* Constructores */
	
	/**
	 * Constructor por defecto
	 * */
	public RFamiliar() {}
	
	/* Getters y setters */
	
	public int getNinos() {
		return ninos;
	}
	public void setninos(int nino) {
		this.ninos=nino;
	}
	
	public int getadultos() {
		return adultos;
	}
	public void setadultos(int adul) {
		this.adultos=adul;
	}
	
	/* Otros metodos */
	
	public String toString() {
		String info = super.toString();
			info += " -> Reserva Familiar";
			return info;
	}
}
