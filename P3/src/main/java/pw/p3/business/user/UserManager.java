package pw.p3.business.user;

import pw.p3.data.dao.UserDAO;
import java.util.ArrayList;

/**
 * 
 * Clase que gestiona a los usuarios
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class UserManager {
	
	/**
	 * Constructor por defecto
	 **/	
	public UserManager(){}
	
	/**public String listarUsuariosAdmin () {
		ArrayList<UserDTO> usuarios = new ArrayList<UserDTO>();
		UserDAO userDAO = new UserDAO();
		usuarios = userDAO.solicitarUsuarios();
		String lista_usuarios = "";
		for (int i = 0; i < usuarios.size(); i++) {
			lista_usuarios.concat("Cliente: " + usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellidos() 
			+ ", con antiguedad " + usuarios.get(i).getAntiguedad() + " meses. Reservas completadas: \n");
		}
		return lista_usuarios;
	}**/
}
