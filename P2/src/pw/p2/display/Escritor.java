package pw.p2.display;

import pw.p2.data.DAO.*;

import java.util.ArrayList;

import pw.p2.business.*;

/**
 * Clase que gestiona la escritura en los ficheros
 * @author 
 * */

public class Escritor {
	
	/**
	 * Funcion que escribe el contenido de los ArrayList a los ficheros .txt
	 * @param GestorPistas
	 * @param GestorUsuarios
	 * @param GestorReservas
	 * @return void
	 */
	
	public static void escritor(GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, GestorReservas GestorReservas_){	
		DAOUsuario usuarioEscritor = new DAOUsuario();
		DAOKart kartEscritor = new DAOKart();
		DAOPista pistaEscritor = new DAOPista();
		DAOReserva reservaEscritor = new DAOReserva();
		
		ArrayList<DTOUsuario> usuarios = usuarioEscritor.solicitarUsuarios();
		boolean encontrado = false;
	    for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
	    	encontrado = false;
	    	for (int j = 0; j < usuarios.size() && !encontrado; j++) {	 
		    	if(GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(usuarios.get(j).getCorreo())) {
		    		encontrado = true;
		    	}
	    	}
	    	if(encontrado) {
		    	if(usuarioEscritor.escribirUsuariosUpdate(GestorUsuarios_.arrayUsuarios.get(i)) == -1){
		    		System.err.println("Error en la escritura de usuario");
		    	}
	    	}else if(!encontrado) {
	    		if(usuarioEscritor.escribirUsuariosInsert(GestorUsuarios_.arrayUsuarios.get(i)) == -1){
		    		System.err.println("Error en la escritura de usuario");
		    	}
	    	}
		}	
		
	}
	
	
}
