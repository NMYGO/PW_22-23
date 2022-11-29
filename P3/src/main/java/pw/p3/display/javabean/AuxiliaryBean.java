package pw.p3.display.javabean;

import pw.p3.data.*;
import pw.p3.business.user.*;
import pw.p3.business.reservation.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class AuxiliaryBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Dificultad dificultadProximaReserva;
	private LocalDate fechaProximaReserva;
	private ArrayList<UserDTO> usuarios;
	private ArrayList<RInfantileDTO> reservasInfantil;
	private ArrayList<RAdultDTO> reservasAdulto;
	private ArrayList<RFamiliarDTO> reservasFamiliar;

	public LocalDate getFechaProximaReserva() {
		return fechaProximaReserva;
	}
	public void setFechaProximaReserva(LocalDate fechaProximaReserva) {
		this.fechaProximaReserva = fechaProximaReserva;
	}
	public Dificultad getDificultadProximaReserva() {
		return dificultadProximaReserva;
	}
	public void setDificultadProximaReserva(Dificultad dificultadProximaReserva) {
		this.dificultadProximaReserva = dificultadProximaReserva;
	}
	
}
