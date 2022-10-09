package pw.p1.other;

import pw.p1.classes.*;
import pw.p1.classes.Kart.Estado;
import java.util.Scanner;

/**
 * Clase que gestiona distintas funcionalidades del main
 * @author 
 * */

public class MainMiscelanea {
	
	/**
	 * Funcion que imprime el menu de opciones del main
	 * @param 
	 * @return void
	 */
	
	public static void menu () {
		System.out.println("1. Crear Pista");
        System.out.println("2. Crear Kart");
        System.out.println("3. Asociar Karts a Pistas");
        System.out.println("4. Listar Pistas en mantenimiento");
        System.out.println("5. Listar pistas libres");
        System.out.println("--------------------------------------------");
        System.out.println("6. Registrar Usuario");
        System.out.println("7. Modificar Usuario");
        System.out.println("8. Listar Usuarios");
        System.out.println("--------------------------------------------");
        System.out.println("9. Hacer Reserva individual");
        System.out.println("10. Hacer Reserva con bono");
        System.out.println("11. Listar Reservas futuras individuales");
        System.out.println("12. Listar Reservas futuras con bono");
        System.out.println("13. Consultar Reserva especifica");
        System.out.println("--------------------------------------------");
        System.out.println("14. Listar Karts");
        System.out.println("15. Modificar Reserva");
        System.out.println("16. Eliminar Reserva");
        System.out.println("--------------------------------------------");
        System.out.println("0. Salir");
        System.out.println("");
	}
	
	/**
	 * Funcion que comprueba que un usuario esta registrado
	 * @param GestorUsuarios
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve un booleano
	 */
	
	public static boolean login (GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Funcion que restaura las pistas al finalizar el programa
	 * @param GestorPistas
	 * @return void
	 */
	
	public static void restaurar (GestorPistas GestorPistas_) {
		for (int i = 0; i < GestorPistas_.arrayKarts.size(); i++) {
			if (GestorPistas_.arrayKarts.get(i).getEstado() == Estado.RESERVADO) {
				GestorPistas_.arrayKarts.get(i).setEstado(Estado.DISPONIBLE);
			}
		}
		
		for (int i = 0; i < GestorPistas_.arrayPistas.size(); i++) {
			if (GestorPistas_.arrayPistas.get(i).isEstado()) {
				GestorPistas_.arrayPistas.get(i).setEstado(false);
			}
		}
	}
	
	public static void reservaIndividual (GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("0. Reserva individual infantil");
        System.out.println("1. Reserva individual familiar");
        System.out.println("2. Reserva individual adulto");
        	System.out.println("");
        System.out.println("Elija una opcion escribiendo su numero");
        int option_= Integer.parseInt(scan_.nextLine());
        if(option_ == 0) {  	
        	GestorReservas_.ReservaIndividualInfantil(GestorReservas_, GestorPistas_, GestorUsuarios_, option_, scan_);	            
        }else if(option_ == 1) {
        	GestorReservas_.ReservaIndividualFamiliar(GestorReservas_, GestorPistas_, GestorUsuarios_, option_, scan_);
        }else if(option_ == 2) {
        	GestorReservas_.ReservaIndividualAdulto(GestorReservas_, GestorPistas_, GestorUsuarios_, option_, scan_);
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}
	
	public static void reservaBono (GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("0. Reserva bono infantil");
        System.out.println("1. Reserva bono familiar");
        System.out.println("2. Reserva bono adulto");
        	System.out.println("");
        System.out.println("Elija una opcion escribiendo su numero");
        int option_= Integer.parseInt(scan_.nextLine());
        if(option_ == 0) {  	
        	GestorReservas_.ReservaBonoInfantil(GestorReservas_, GestorPistas_, GestorUsuarios_, scan_);	            
        }else if(option_ == 1) {
        	GestorReservas_.ReservaBonoFamiliar(GestorReservas_, GestorPistas_, GestorUsuarios_, scan_);
        }else if(option_ == 2) {
        	GestorReservas_.ReservaBonoAdulto(GestorReservas_, GestorPistas_, GestorUsuarios_, scan_);
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}
	
	public static void listarKartsDisponibles (GestorPistas GestorPistas_) {
		int nodisponible = 0;
		for(int i = 0; i < GestorPistas_.arrayKarts.size(); i++) {
			if(GestorPistas_.arrayKarts.get(i).getEstado() == Estado.DISPONIBLE) {
				System.out.println(GestorPistas_.arrayKarts.get(i).toString());
			}else if(GestorPistas_.arrayKarts.get(i).getEstado() == Estado.RESERVADO){
				System.out.println(GestorPistas_.arrayKarts.get(i).toString());
			}else {
				System.out.println(GestorPistas_.arrayKarts.get(i).toString());
			}
		}
		if(GestorPistas_.arrayKarts.size() == nodisponible) {
			System.out.println("No hay karts disponibles");
			System.out.println("");
		}
	}
}