package pw.p3.business.reservation;

import pw.p3.data.dao.*;
import pw.p3.business.circuit.CircuitDTO;
import pw.p3.data.Dificultad;
import pw.p3.data.Estado;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 *  Clase que gestiona las reservas
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class ReservationManager{
	
	/* Constructores */

	/**
	 * Constructor por defecto
	 **/
	public ReservationManager(){}
	
	/**
	 * Funcion para crear reserva
	 * @param usuario nombre Nombre de usuario que reserva
	 * @param pista Pista en la que se hace la reserva 
	 * @param dificultad Tipo de dificultad de reserva
	 * @param ninos Numero de participantes niños
	 * @param adultos Numero de participantes adultos
	 * @param duracion Duracion de la reserva
	 * @param descuento Descuento aplicado al precio
	 * @param fecha Fecha de la reserva
	 **/
	
	public boolean crearReserva (String nombre, String pista, String dificultad, Integer ninos, Integer adultos, Integer duracion, Integer descuento, LocalDate fecha) {		
		RIndividualCreator individualCreador = new RIndividualCreator();
		Float precio=(float)0;
		switch (duracion) {
			case 60:
				precio = (float)20;
			break;
			case 90:
				precio = (float)30;
			break;
			case 120:
				precio = (float)40;
			break;
		}
		if(dificultad.equals("INFANTIL")){
			RInfantileDTO newReserva = individualCreador.creaRInf(0,nombre, fecha, duracion, pista, precio, descuento, ninos, Dificultad.INFANTIL);
			ReservationDAO reservaTabla = new ReservationDAO();
			if(reservaTabla.escribirReservaInfantilInsert(newReserva) == 0) {
				return false;
			}
		}else if(dificultad.equals("ADULTO")) {
			RAdultDTO newReserva = individualCreador.creaRAdu(0,nombre, fecha, duracion, pista, precio, descuento, adultos, Dificultad.ADULTO);
			ReservationDAO reservaTabla = new ReservationDAO();
			if(reservaTabla.escribirReservaAdultoInsert(newReserva) == 0) {
				return false;
			}
		}else if (dificultad.equals("FAMILIAR")) {
			RFamiliarDTO newReserva = individualCreador.creaRFam(0,nombre, fecha, duracion, pista, precio, descuento, ninos, adultos, Dificultad.FAMILIAR);
			ReservationDAO reservaTabla = new ReservationDAO();
			if(reservaTabla.escribirReservaFamiliarInsert(newReserva) == 0) {
			return false;
			}
		}
		return true;
	}

	public boolean crearReserva(String correoUser, String pista, String dificultad, Integer ninos, Integer adultos,
			Integer duracion, Integer descuento, LocalDate fecha, Integer idBono) {
		RIndividualCreator individualCreador = new RIndividualCreator();
		Float precio=(float)0;
		switch (duracion) {
			case 60:
				precio = (float)20;
			break;
			case 90:
				precio = (float)30;
			break;
			case 120:
				precio = (float)40;
			break;
		}
		if(dificultad.equals("INFANTIL")){
			RInfantileDTO newReserva = individualCreador.creaRInf(0,correoUser, fecha, duracion, pista, precio, descuento, ninos, Dificultad.INFANTIL);
			BonoDAO reservaTabla = new BonoDAO();
			if(reservaTabla.escribirReservaInfantilInsert(newReserva, idBono) == 0) {
				return false;
			}
		}else if(dificultad.equals("ADULTO")) {
			RAdultDTO newReserva = individualCreador.creaRAdu(0,correoUser, fecha, duracion, pista, precio, descuento, adultos, Dificultad.ADULTO);
			BonoDAO reservaTabla = new BonoDAO();
			if(reservaTabla.escribirReservaAdultoInsert(newReserva, idBono) == 0) {
				return false;
			}
		}else if (dificultad.equals("FAMILIAR")) {
			RFamiliarDTO newReserva = individualCreador.creaRFam(0,correoUser, fecha, duracion, pista, precio, descuento, ninos, adultos, Dificultad.FAMILIAR);
			BonoDAO reservaTabla = new BonoDAO();
			if(reservaTabla.escribirReservaFamiliarInsert(newReserva, idBono) == 0) {
				return false;
			}
			
		}
		return true;
	}
}
