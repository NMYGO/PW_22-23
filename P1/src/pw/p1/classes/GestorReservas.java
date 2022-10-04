package pw.p1.classes;
import pw.p1.factory_reserva.*;	//Para usar todas las clases de pw.p1.factory_reserva

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorReservas {

	/* Atributos */
	ArrayList<RBonoCreador> bonos = new ArrayList<RBonoCreador>();
	/**
	 * Constructor por defecto
	 **/	
	public GestorReservas(){}
	
	/**
	 * Realiza una reserva individual
	 **/
	
	public boolean ReservaIndividualInfantil(Usuario usuario, Pista pista, int nparticipantes) {
		if((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			if((pista.getDificultad() == Pista.Dificultad.INFANTIL) && (pista.isEstado() == false)) {
				Pista kpista = new Pista();
				ArrayList<Kart> dkart = kpista.consultarKartsDisponibles();
				int infantilkart = 0;
				for (int i = 0;i < dkart.size() ; i++) {
					if ((dkart.get(i)).isTipo() == true) {
						infantilkart ++;
					}
				}
				if(infantilkart <= nparticipantes) {
					if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
						
					}
				}
			}
		}
		
		return true;
	}
	
	public boolean ReservaBono(Usuario usuario, Pista pista, int nparticipantes, int cBono) {
		boolean encontrado=false;
		for (int i = 0; i < bonos.size(); i++)
			if (cBono == bonos.get(i).getId())
				encontrado=true;
		if(!encontrado) {
			System.out.println("El bono usado no existe\n");
			return false;
		}
			
		return true;
	}
	
}
