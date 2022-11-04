package pw.p2.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Clase que resuelve las funcionalidades del DTOUsuario
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class DTOUsuarioFuncionalidades {
	
	/**
	 * Calcula la antiguedad del usuario
	 * @param inscripcion LocalDate con la fecha de inscripcion del usuario
	 * @return
	 **/
	
	public void calcularAntiguedad(LocalDate inscripcion) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("uuuu");
		String ahora = (LocalDate.now()).format(formateador);
		String sInscripcion = inscripcion.format(formateador);
		int antiguedad = Integer.parseInt(ahora) - Integer.parseInt(sInscripcion);
			System.out.println("Nº de años registrado: " + antiguedad);
	}
}
