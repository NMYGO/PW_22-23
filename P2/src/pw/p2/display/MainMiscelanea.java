package pw.p2.display;

import pw.p2.business.*;
import pw.p2.data.Estado;
import pw.p2.data.DAO.DAOKart;
import pw.p2.data.DAO.DAOPista;
import pw.p2.data.DAO.DAOUsuario;
import pw.p2.data.Dificultad;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Clase que gestiona distintas funcionalidades del main
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class MainMiscelanea {
	
	/**
	 * Funcion que imprime el menu principal de opciones del main
	 * @param 
	 * @return void
	 */
	
	public static void menuPrincipal () {
		System.out.println("1. MENU DE PISTAS.");
        System.out.println("2. MENU DE USUARIOS.");
        System.out.println("3. MENU DE RESERVAS.");
        System.out.println("4. MENU DE KARTS.");
        System.out.println("--------------------------------------------");
        System.out.println("0. Salir");
        System.out.println("");
	}
	
	/**
	 * Funcion que imprime el menu de pistas del main
	 * @param 
	 * @return void
	 */
	
	public static void menuPistas () {
		System.out.println("-------------------MENU DE PISTAS-------------------");
		System.out.println("1. Crear Pista");
        System.out.println("2. Listar Pistas en mantenimiento");
        System.out.println("3. Listar pistas libres");
        System.out.println("--------------------------------------------");
        System.out.println("0. Salir a menu principal.");
        System.out.println("");
	}
	
	/**
	 * Funcion que imprime el menu de usuarios del main
	 * @param 
	 * @return void
	 */
	
	public static void menuUsuarios () {
		System.out.println("-------------------MENU DE USUARIOS-------------------");
		System.out.println("1. Registrar Usuario");
        System.out.println("2. Modificar Usuario");
        System.out.println("3. Listar Usuarios");
        System.out.println("-------------------------------------------");
        System.out.println("0. Salir a menu principal.");
        System.out.println("");
	}
	
	/**
	 * Funcion que imprime el menu de reservas del main
	 * @param 
	 * @return void
	 */
	
	public static void menuReservas () {
		System.out.println("-------------------MENU DE RESERVAS-------------------");
		System.out.println("1. Hacer Reserva individual");
        System.out.println("2. Hacer Reserva con bono");
        System.out.println("3. Modificar Reserva");
        System.out.println("4. Eliminar Reserva");
        System.out.println("5. Listar Reservas futuras individuales");
        System.out.println("6. Listar Reservas futuras con bono");
        System.out.println("7. Consultar Reserva especifica");
        System.out.println("--------------------------------------------");
        System.out.println("0. Salir a menu principal.");
        System.out.println("");
	}
	
	/**
	 * Funcion que imprime el menu de karts del main
	 * @param 
	 * @return void
	 */
	
	public static void menuKarts () {
		System.out.println("-------------------MENU DE KARTS-------------------");
		System.out.println("1. Crear Kart");
        System.out.println("2. Listar Karts");
        System.out.println("3. Asociar Karts a Pistas");
        System.out.println("--------------------------------------------");
        System.out.println("0. Salir a menu principal.");
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
		
		DAOUsuario usuarioTabla = new DAOUsuario();
		if(usuarioTabla.solicitarUsuario(correo).getCorreo() == null) {
			return false;
		}

		return true;
	}
	
	/**
	 * Funcion que restaura las pistas al finalizar el programa
	 * @param GestorPistas
	 * @return void
	 */
	
	public static void restaurar(GestorPistas GestorPistas_) {
		DAOKart kartTabla = new DAOKart();
		DAOPista pistaTabla = new DAOPista();
		ArrayList <DTOKart> karts = kartTabla.solicitarKarts();
		ArrayList <DTOPista> pistas = pistaTabla.solicitarPistas();
		
		for (int i = 0; i < karts.size(); i++) {
			if (karts.get(i).getEstado() == Estado.RESERVADO) {
				karts.get(i).setEstado(Estado.DISPONIBLE);
			}
		}
		
		for (int i = 0; i < pistas.size(); i++) {
			if (pistas.get(i).isEstado()) {
				pistas.get(i).setEstado(false);
			}
		}
	}
	
	/**
	 * Menu para elegir entre los tipos de reserva individual
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param scan_
	 */
	
	public static void crearReservaIndividual (GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("0. Reserva individual infantil");
        System.out.println("1. Reserva individual familiar");
        System.out.println("2. Reserva individual adulto");
        	System.out.println("");
        System.out.println("Elija una opcion escribiendo su numero");
        int option_= Integer.parseInt(scan_.nextLine());
        if(option_ == 0) {  	
        	GestorReservas_.ReservaIndividualInfantil(scan_);	            
        }else if(option_ == 1) {
        	GestorReservas_.ReservaIndividualFamiliar(scan_);
        }else if(option_ == 2) {
        	GestorReservas_.ReservaIndividualAdulto(scan_);
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}
	
	/**
	 * Menu para elegir que tipo de reserba de bono se va a hacer
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param scan_
	 */
	
	public static void crearReservaBono (GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("0. Reserva bono infantil");
        System.out.println("1. Reserva bono familiar");
        System.out.println("2. Reserva bono adulto");
        	System.out.println("");
        System.out.println("Elija una opcion escribiendo su numero");
        int option_= Integer.parseInt(scan_.nextLine());
        if(option_ == 0) {	
        	GestorReservas_.ReservaBonoInfantil(scan_);	            
        }else if(option_ == 1) {
        	GestorReservas_.ReservaBonoFamiliar(scan_);
        }else if(option_ == 2) {
        	GestorReservas_.ReservaBonoAdulto(scan_);
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}
	
	/**
	 * Al elegir modificar una reserva se encarga de llamar a la función segun sea infantil, familiar o adulta
	 * @param GestorReservas_
	 * @param usuario
	 * @param fecha
	 * @param pista
	 * @param duracion
	 * @param tipo
	 * @param scan_
	 */
	public static void modificarReservaIndividual (GestorReservas GestorReservas_, String usuario, LocalDate fecha, String pista, Integer duracion, Dificultad tipo, Scanner scan_) {
        if(tipo == Dificultad.INFANTIL) {  	
        	GestorReservas_.modificarReservaInfantil(usuario, fecha, pista, duracion, scan_);	            
        }else if(tipo == Dificultad.FAMILIAR) {
        	GestorReservas_.modificarReservaFamiliar(usuario, fecha, pista, duracion, scan_);
        }else if(tipo == Dificultad.ADULTO) {
        	GestorReservas_.modificarReservaAdulto(usuario, fecha, pista, duracion, scan_);
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}
}