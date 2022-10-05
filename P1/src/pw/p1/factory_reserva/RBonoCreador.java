package pw.p1.factory_reserva;


public class RBonoCreador extends ReservaCreador {
	public enum Tipo {
		INFANTIL, FAMILIAR, ADULTO
	};
	
	/* Atributos */
	private int id;
	private int sesion;
	private int usuario;
	private Tipo tipo;
	/**
	 * Funciones get y set de los atributos
	 */
	public int getId() {
		return id;
	}
	public void setId(int ID) {
		this.id=ID;
	}
	public int getSesion() {
		return sesion;
	}
	public void setSesion(int ses) {
		this.sesion=ses;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int user) {
		this.usuario=user;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo type) {
		this.tipo=type;
	}
	/**
	 * Crea una reserva infantil
	 */
	@Override
	public RInfantil creaRInf() {
		RInfantil reserva = new RInfantil();
		//usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva familiar
	 *  */
	@Override
	public RFamiliar creaRFam() {
		RFamiliar reserva = new RFamiliar();
		//usuario=reserva.getUsuario();
		return reserva;
	}
	
	/** 
	 * Crea una reserva adulta
	 *  */
	@Override
	public RAdulto creaRAdu() {
		RAdulto reserva = new RAdulto();
		//usuario=reserva.getUsuario();		
		return reserva;
	}
}
