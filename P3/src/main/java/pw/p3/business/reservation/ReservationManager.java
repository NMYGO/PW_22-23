package pw.p3.business.reservation;

import pw.p3.data.dao.*;
import pw.p3.data.Dificultad;
import java.time.LocalDate;

public class ReservationManager{

	public ReservationManager(){}
	
	public boolean crearPista (String nombre, String pista, String dificultad, Integer ninos, Integer adultos, Integer duracion, Integer descuento, LocalDate fecha) {		
		RIndividualCreator individualCreador = new RIndividualCreator();
		Float precio=(float)0;
		switch (duracion) {
			case 60:
				precio = (float) 20;
			break;
			case 90:
				precio = (float) 30;
			break;
			case 120:
				precio = (float) 40;
			break;
		}
		
		if(dificultad.equals("INFANTIL")) {
			RInfantileDTO newReserva = individualCreador.creaRInf(0 ,nombre, fecha, duracion, pista, precio, descuento, ninos, Dificultad.INFANTIL);
			ReservationDAO reservaTabla = new ReservationDAO();
			if(reservaTabla.escribirReservaInfantilInsert(newReserva) == 0) {
				return false;
			}
		}else if (dificultad.equals("ADULTO")) {
			RAdultDTO newReserva = individualCreador.creaRAdu(0, nombre, fecha, duracion, pista, precio, descuento, adultos, Dificultad.ADULTO);
			ReservationDAO reservaTabla = new ReservationDAO();
			if(reservaTabla.escribirReservaAdultoInsert(newReserva) == 0) {
				return false;
			}
		}else {
			RFamiliarDTO newReserva = individualCreador.creaRFam(0, nombre, fecha, duracion, pista, precio, descuento, ninos, adultos, Dificultad.FAMILIAR);
			ReservationDAO reservaTabla = new ReservationDAO();
			if(reservaTabla.escribirReservaFamiliarInsert(newReserva) == 0) {
				return false;
			}
			
		}
		return true;
	}
}
