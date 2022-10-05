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
	
	public boolean ReservaIndividualInfantil(Usuario usuario, Pista pista, int nParticipantes, Scanner scan_, GestorPistas GestorPistas_) {
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			ArrayList<Kart> dkart = pista.consultarKartsDisponibles(GestorPistas_.arrayKarts);
			int karts = 0;
			for (int i = 0;i < dkart.size() ; i++) {
				if ((dkart.get(i)).isTipo() == false) {
					karts++;
				}
			}
			if(karts>=nParticipantes) {
				RIndividualCreador individualCreador = new RIndividualCreador();
				RInfantil reserva = individualCreador.creaRInf();
				System.out.println("Introduzca la duración de la reserva (30/60/90 minutos)");
				int tiempo = Integer.parseInt(scan_.nextLine());
				System.out.println("Introduzca la fecha de la reserva");
				LocalDate fecha = LocalDate.parse(scan_.nextLine());
				reserva.setDur(tiempo);
				reserva.setFecha(fecha);
				reserva.setParticipantes(nParticipantes);
				reserva.setUsuario(usuario.getNombre());
				reserva.setPista(pista.getNombre());
				switch(tiempo) {
				case 30:
					reserva.setPrecio(20);
					break;
				case 60:
					reserva.setPrecio(30);
					break;
				case 90:
					reserva.setPrecio(40);
					break;
				}
				if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
					reserva.setDesc(10);
				}
				else reserva.setDesc(0);
			}
			else {
				System.out.println("No hay suficientes karts en esa pista para los participantes.\n");
				return false;
			}
		}
		else {
			System.out.println("No se puede hacer una reserva para adultos con un usuario menor de edad.\n");
			return false;
		}
		
		return true;
	}
	
	
	
	public boolean ReservaIndividualAdulto(Usuario usuario, Pista pista, int nParticipantes, Scanner scan_, GestorPistas GestorPistas_) {
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			ArrayList<Kart> dkart = pista.consultarKartsDisponibles(GestorPistas_.arrayKarts);
			int karts = 0;
			for (int i = 0;i < dkart.size() ; i++) {
				if ((dkart.get(i)).isTipo() == false) {
					karts++;
				}
			}
			if(karts>=nParticipantes) {
				RIndividualCreador individualCreador = new RIndividualCreador();
				RAdulto reserva = individualCreador.creaRAdu();
				System.out.println("Introduzca la duración de la reserva (30/60/90 minutos)");
				int tiempo = Integer.parseInt(scan_.nextLine());
				System.out.println("Introduzca la fecha de la reserva");
				LocalDate fecha = LocalDate.parse(scan_.nextLine());
				reserva.setDur(tiempo);
				reserva.setFecha(fecha);
				reserva.setParticipantes(nParticipantes);
				reserva.setUsuario(usuario.getNombre());
				reserva.setPista(pista.getNombre());
				switch(tiempo) {
				case 30:
					reserva.setPrecio(20);
					break;
				case 60:
					reserva.setPrecio(30);
					break;
				case 90:
					reserva.setPrecio(40);
					break;
				}
				if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
					reserva.setDesc(10);
				}
				else reserva.setDesc(0);
			}
			else {
				System.out.println("No hay suficientes karts en esa pista para los participantes.\n");
				return false;
			}
		}
		else {
			System.out.println("No se puede hacer una reserva para adultos con un usuario menor de edad.\n");
			return false;
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
