package pw.p2.business;

import pw.p2.data.DAO.DAOUsuario;
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
	
	/**
	 * Constructor por defecto
	 **/	
	public GestorUsuarios(){}
	
	/**
	 * Funcion que registra un usuario comprobando que no exista antes
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve un booleano 
	 * */

	public Boolean registrarUsuario (String nombre, String apellidos, LocalDate nacimiento, String correo) {	
		DAOUsuario usuarioTabla = new DAOUsuario();
		DTOUsuario usuario = new DTOUsuario(nombre, apellidos, nacimiento, correo);
		
		if(usuarioTabla.escribirUsuarioInsert(usuario) == 0) {
			System.out.println("Error. Usuario no registrado");
			System.out.println("-------------------------------------");
			System.out.println("");
			return false;
		}
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

	public Boolean ModificarUsuario (Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
			String correo = scan_.nextLine();
			System.out.println("");
			
			DAOUsuario usuarioTabla = new DAOUsuario();
			DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);

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
					if(usuarioTabla.escribirUsuarioUpdate(usuario) == 0) {
						System.out.println("Error. Usuario no modificado");
						System.out.println("-------------------------------------");
						System.out.println("");
						return false;
					}					
					System.out.println("Usuario modificado con exito");
					System.out.println("-------------------------------------");
					System.out.println("");
					
					break;
				case 1:
					System.out.println("Introduzca el nuevo nombre de usuario");
					String nuevoNombre = scan_.nextLine();
					System.out.println("");
					usuario.setNombre(nuevoNombre);

					break;
						
				case 2:
					System.out.println("Introduzca los nuevos apellidos de usuario");
					String nuevoApellidos = scan_.nextLine();
					System.out.println("");
					usuario.setApellidos(nuevoApellidos);

					break;
				case 3:
					System.out.println("Introduzca la nueva fecha de nacimiento de usuario");
					LocalDate nuevaFechaNacimiento = LocalDate.parse(scan_.nextLine());					
					System.out.println("");
					usuario.setNacimiento(nuevaFechaNacimiento);
					
					break;
				default:
					System.out.println("Opcion no reconocida");
					System.out.println("");
					break;
				}
			}
			
			return true;
	}
	
	/**
	 * Funcion para listar los usuarios existentes
	 */
	
	public void listarUsuarios () {
		DAOUsuario usuarioTabla = new DAOUsuario();
		ArrayList<DTOUsuario> usuarios = usuarioTabla.solicitarUsuarios();
		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println(usuarios.get(i).toString());
		}
	}
}
