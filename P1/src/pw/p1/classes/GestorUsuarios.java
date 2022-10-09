package pw.p1.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Clase que gestiona a los usuarios
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class GestorUsuarios {
	
	/* Atributos */
	
	public ArrayList<Usuario> arrayUsuarios = new ArrayList<Usuario>();
	
	/**
	 * Constructor por defecto
	 **/
	
	public GestorUsuarios(){}
	
	/**
	 * Funcion que registra un usuario comprobando que no exista antes
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve un booleano 
	 * */

	public Boolean registrarUsuario(Scanner scan_) {
		System.out.println("Introduzca el nombre de usuario");
			String nombre = scan_.nextLine();
		System.out.println("Introduzca los apellidos de usuario");        			
			String apellidos = scan_.nextLine();
		System.out.println("Introduzca la fecha de nacimiento de usuario (yyyy-mm-dd)");
			LocalDate nacimiento = LocalDate.parse(scan_.nextLine());
		System.out.println("Introduzca el correo de usuario");
			String correo = scan_.nextLine();
			
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			if (arrayUsuarios.get(i).getCorreo().equals(correo)) {
				System.out.println("Error. Ese usuario ya existe");
				System.out.println("-------------------------------------");
				System.out.println("");
				return false;
			}
		}
		Usuario newUsuario = new Usuario(nombre, apellidos, nacimiento, correo);
		arrayUsuarios.add(newUsuario);
		System.out.println("Usuario creado con exito");
		System.out.println("-------------------------------------");
		System.out.println("");
		return true;
	}
	
	/**
	 * Funcion para modificar un usuario existente
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve un booleano  
	 * */

	public Boolean ModificarUsuario(Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			if (arrayUsuarios.get(i).getCorreo().equals(correo)) {

				int opcion = 1;
				while (opcion != 0) {
					System.out.println("0: Terminar modificacion.\n"
					+ "1: Cambiar Nombre.\n"
					+ "2: Cambiar Apellidos.\n"
					+ "3: Cambiar fecha de nacimiento.\n"
					+ "4: Cambiar direccion de Correo.\n"
					+ "Introduzca una opcion:"); 
					
					opcion = Integer.parseInt(scan_.nextLine());
					System.out.println("");
					switch (opcion) {
					case 0: 
						System.out.println("Usuario modificado con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
						
						break;
					case 1:		//Cambio de Nombre
						System.out.println("Introduzca el nuevo nombre de usuario");
						String nuevoNombre = scan_.nextLine();
						System.out.println("");
						arrayUsuarios.get(i).setNombre(nuevoNombre);
	
						break;
							
					case 2:		//Cambio de Apellidos
						System.out.println("Introduzca los nuevos apellidos de usuario");
						String nuevoApellidos = scan_.nextLine();
						System.out.println("");
						arrayUsuarios.get(i).setApellidos(nuevoApellidos);
	
						break;
					case 3:		//Cambio de fecha de nacimiento
						System.out.println("Introduzca la nueva fecha de nacimiento de usuario");
						LocalDate nuevaFechaNacimiento = LocalDate.parse(scan_.nextLine());					
						System.out.println("");
						arrayUsuarios.get(i).setNacimiento(nuevaFechaNacimiento);
						
						break;
						
					case 4:		//Cambio de correo
						System.out.println("Introduzca la nueva direccion de correo de usuario");
						String nuevoCorreo = scan_.nextLine();
						System.out.println("");
						arrayUsuarios.get(i).setCorreo(nuevoCorreo);
	
						break;
					default:
						System.out.println("Opcion no reconocida");
						System.out.println("");
						break;
					}
				}
				return true;
			}
		}
		System.out.println("Error. Ese usuario no existe");
		System.out.println("-------------------------------------");
		System.out.println("");
		return false; //NO SE HA ENCONTRADO EL USUARIO
	}
	
	/**
	 * Funcion para listar los usuarios existentes
	 */
	
	public void listarUsuarios() {
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			System.out.println(arrayUsuarios.get(i).toString());
		}
	}
}
