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
	
	public Dificultad getDificultadProximaReserva() {
		return dificultadProximaReserva;
	}
	public void setDificultadProximaReserva(Dificultad dificultadProximaReserva) {
		this.dificultadProximaReserva = dificultadProximaReserva;
	}
	public LocalDate getFechaProximaReserva() {
		return fechaProximaReserva;
	}
	public void setFechaProximaReserva(LocalDate fechaProximaReserva) {
		this.fechaProximaReserva = fechaProximaReserva;
	}
	public ArrayList<UserDTO> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<UserDTO> usuarios) {
		this.usuarios = usuarios;
	}
	public ArrayList<RInfantileDTO> getReservasInfantil() {
		return reservasInfantil;
	}
	public void setReservasInfantil(ArrayList<RInfantileDTO> reservasInfantil) {
		this.reservasInfantil = reservasInfantil;
	}
	public ArrayList<RAdultDTO> getReservasAdulto() {
		return reservasAdulto;
	}
	public void setReservasAdulto(ArrayList<RAdultDTO> reservasAdulto) {
		this.reservasAdulto = reservasAdulto;
	}
	public ArrayList<RFamiliarDTO> getReservasFamiliar() {
		return reservasFamiliar;
	}
	public void setReservasFamiliar(ArrayList<RFamiliarDTO> reservasFamiliar) {
		this.reservasFamiliar = reservasFamiliar;
	}
}
