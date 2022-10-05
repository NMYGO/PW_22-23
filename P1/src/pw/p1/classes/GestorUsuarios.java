package pw.p1.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//CREAR USUARIO CON LA FECHA ACTUAL DEL SISTEMA

/**
 * Clase que Gestiona los usuarios
 * @author 
 * */

public class GestorUsuarios {
	
	/* Atributos */
	
	public ArrayList<Usuario> arrayUsuarios = new ArrayList<Usuario>();
	
	/**
	 * Funcion que registra un usuario comprobando que no exista antes
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param nacimiento Nacimiento del usuario
	 * @param correo Correo unico del usuario
	 * @author 
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
	 * @param correo Correo unico del usuario
	 * @return Devuelve TRUE  
	 * */

	public Boolean ModificarUsuario(Scanner scan_) { //TRUE si el usuario a modificar se encuentra en la lista
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			if (correo == arrayUsuarios.get(i).getCorreo()) {
				Scanner modificacion = new Scanner(System.in); //Para leer las variables introducidas
				
				Integer opcion = 1;
				while (opcion != 0) {
					System.out.println("Introduzca una opcion:\n"
					+ "0: Terminar modificacion.\n"
					+ "1: Cambiar Nombre.\n"
					+ "2: Cambiar Apellidos.\n"
					+ "3: Cambiar fecha de nacimiento.\n"
					+ "4: Cambiar direccion de Correo."); 
					
					opcion = modificacion.nextInt();
					//opcion = System.in.read();
					
					switch (opcion) {
					case 0: 
						System.out.println("Usuario modificado con exito"); 
						
						break;
					case 1:		//Cambio de Nombre
						System.out.println("Introduzca el nuevo nombre de usuario");
						String nuevoNombre = modificacion.next();
						arrayUsuarios.get(i).setNombre(nuevoNombre);
	
						break;
							
					case 2:		//Cambio de Apellidos
						System.out.println("Introduzca los nuevos apellidos de usuario");
						String nuevoApellidos = modificacion.next();
						arrayUsuarios.get(i).setApellidos(nuevoApellidos);
	
						break;
					case 3:		//Cambio de fecha de nacimiento
						System.out.println("Introduzca la nueva fecha de nacimiento de usuario");
						String nuevaFechaNacimiento = modificacion.next();
						DateTimeFormatter formato= DateTimeFormatter.ofPattern("dd-MMM-yyyy");
						LocalDate date = LocalDate.parse(nuevaFechaNacimiento, formato);
						arrayUsuarios.get(i).setNacimiento(date);
						
						break;
						
					case 4:		//Cambio de correo
						System.out.println("Introduzca la nueva direccion de correo de usuario");
						String nuevoCorreo = modificacion.next();
						arrayUsuarios.get(i).setCorreo(nuevoCorreo);
	
						break;
					default:
						System.out.println("Opcion no reconocida");
						
						break;
					}
				}
				modificacion.close();
				return true;
			}
		}
		System.out.println("Error. Ese usuario no existe");
		return false; //NO SE HA ENCONTRADO EL USUARIO
	}
	
	public void listarUsuarios() {
		for (int i = 0; i < arrayUsuarios.size(); i++) {
			System.out.println(arrayUsuarios.get(i).toString());
		}
	}
}
