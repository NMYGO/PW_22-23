package pw.p3.business.user;

import pw.p3.data.dao.UserDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	
	public int calcularAntiguedad(UserDTO usuario) {
		DateTimeFormatter formateadorYY = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter formateadorMM = DateTimeFormatter.ofPattern("MM");
		String ahoraYY = (LocalDate.now()).format(formateadorYY);
		String sInscripcionYY = usuario.getInscripcion().format(formateadorYY);
		String ahoraMM = (LocalDate.now()).format(formateadorMM);
		String sInscripcionMM = usuario.getInscripcion().format(formateadorMM);
		int antiguedad = 12*(Integer.parseInt(ahoraYY) - Integer.parseInt(sInscripcionYY)) + (Integer.parseInt(ahoraMM) - Integer.parseInt(sInscripcionMM));
		return antiguedad;
	}
}
