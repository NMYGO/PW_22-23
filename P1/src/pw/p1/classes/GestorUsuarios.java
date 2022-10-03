package pw.p1.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//CREAR USUARIO CON LA FECHA ACTUAL DEL SISTEMA

public class GestorUsuarios {

	ArrayList<Usuario> lUsuarios;
	
	public Boolean registrarUsuario(String nombre, String apellidos, LocalDate nacimiento, String correo) {
		for (int i = 0; i < lUsuarios.size(); i++) {
			if (correo == lUsuarios.get(i).getCorreo()) {
				return false; //El usuario ya se encuentra registrado
			}
		}
		Usuario newUsuario = new Usuario(nombre, apellidos, nacimiento, correo);
		lUsuarios.add(newUsuario);
		return true;//El usuario se añade exitosamente
	}
	
	public Boolean ModificarUsuario(String correo) { //TRUE si el usuario a modificar se encuentra en la lista
		for (int i = 0; i < lUsuarios.size(); i++) {
			if (correo == lUsuarios.get(i).getCorreo()) {
				Scanner modificacion = new Scanner(System.in); //Para leer las variables introducidas
				
				Integer opcion = 1;
				while (opcion != 0) {
					System.out.println("Introduzca una opcion:\n"
					+ "0: Terminar modificacion.\n"
					+ "1: Cambiar Nombre.\n"
					+ "2: Cambiar Apellidos.\n"
					+ "3: Cambiar fecha de nacimiento.\n"
					+ "4: Cambiar direccion de Correo."); 
					
					opcion = System.in.read();
					
					switch (opcion) {
					case 0: 
						System.out.println("Terminando Modificaciones"); 
						return true;
						
						break;
					case 1:		//Cambio de Nombre
						System.out.println("Introduzca el nuevo nombre");
						String nuevoNombre = modificacion.next();
						lUsuarios.get(i).setNombre(nuevoNombre);
	
						break;
							
					case 2:		//Cambio de Apellidos
						System.out.println("Introduzca los nuevos apellidos");
						String nuevoApellidos = modificacion.next();
						lUsuarios.get(i).setApellidos(nuevoApellidos);
	
						break;
					case 3:		//Cambio de fecha de nacimiento
						System.out.println("Introduzca la nueva fecha de nacimiento");
						String nuevaFechaNacimiento = modificacion.next();
						lUsuarios.get(i).setNacimiento(nuevaFechaNacimiento);
	
						break;
						
					case 4:		//Cambio de correo
						System.out.println("Introduzca la nueva direccion de correo");
						String nuevoCorreo = modificacion.next();
						lUsuarios.get(i).setCorreo(nuevoCorreo);
	
						break;
					default:
						System.out.println("Opcion no reconocida");
						
						break;
					}
				}
			}
		}
		return false; //NO SE HA ENCONTRADO EL USUARIO
	}
	
	public void listarUsuarios() {
		for (int i = 0; i < lUsuarios.size(); i++) {
			lUsuarios.get(i).toString();
		}
	}
}
