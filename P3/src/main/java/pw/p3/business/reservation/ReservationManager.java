package pw.p3.business.reservation;

import pw.p3.data.dao.*;
import pw.p3.business.circuit.CircuitDTO;
import pw.p3.data.Dificultad;
import pw.p3.data.Estado;
import java.util.ArrayList;

public class ReservationManager{

	public ReservationManager(){}
	
	public boolean crearPista (String nombre, String pista, Dificultad dificultad, Integer ninos, Integer adultos, Integer duracion, Integer descuento, LocalDate fecha) {		
		RIndividualCreator individualCreador = new RIndividualCreator();
		Integer precio;
		switch (duracion) {
			case 60:
				precio = 20;
			break;
			case 90:
				precio = 30;
			break;
			case 120:
				precio = 40;
			break;
		}
		DTORInfantil newReserva = individualCreador.creaRInf(usuario.getCorreo(), fecha, duracion, pista, precio, descuento, participantes, Dificultad.INFANTIL);
		DAOReserva reservaTabla = new DAOReserva();
		
		if(pistaTabla.escribirPistaInsert(pista) == 0) {
			System.out.println("Error. Pista no creada");
			System.out.println("-------------------------------------");
			System.out.println("");
			return false;
		}		
		System.out.println("Pista creada con exito");
		System.out.println("-------------------------------------");
		System.out.println("");
		return true;
	}
}
