// Esta clase DAO es "simulada" - no tiene acceso a la base de datos
package pw.p3.data.dao;

import pw.p3.business.user.UserDTO;

public class UserDAO {
	public UserDTO getUserByName(String name) {
		//Esta clase accedería a base de datos para la comprobación. 
		//Se simula que si es "JohnDoe", será verdadero; en otro caso, devuelve null.
		if (name.equalsIgnoreCase("johndoe")) {
			return new UserDTO("john.doe@email.com", "JohnDoe", (int)(Math.random() * 80));
		}
		return null;
	}
}
