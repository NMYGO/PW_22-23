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
        System.out.println("5. Recoger Array de pistas libres");
        System.out.println("--------------------------------------------");
        System.out.println("6. Registrar Usuario");
        System.out.println("7. Modificar Usuario");
        System.out.println("8. Listar Usuarios");
        System.out.println("--------------------------------------------");
        System.out.println("9. Hacer reserva individual");
        System.out.println("10. Hacer reserva en bono");
        System.out.println("11. PLACEHOLDER");
        System.out.println("12. PLACEHOLDER");
        System.out.println("13. PLACEHOLDER");
        System.out.println("14. PLACEHOLDER");
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
	
}