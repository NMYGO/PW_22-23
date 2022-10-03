package pw.p1.classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorReservas {

	/* Atributos */
	
	/**
	 * Constructor por defecto
	 **/	
	public GestorReservas(){}
	
	/**
	 * Realiza una reserva individual
	 **/
	
	public boolean ReservaIndividualInfantil(Usuario usuario, Pista pista, int nparticipantes) {
		if((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			if((pista.getDificultad() == Pista.Dificultad.INFANTIL) && (pista.isEstado() == false)) {
				Pista kpista = new Pista();
				ArrayList<Kart> dkart = kpista.consultarKartsDisponibles();
				int infantilkart = 0;
				for (int i = 0;i < dkart.size() ; i++) {
					if ((dkart.get(i)).isTipo() == true) {
						infantilkart ++;
					}
				}
				if(infantilkart <= nparticipantes) {
					if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
						
					}
				}
			}
		}
		
		return true;
	}
	
}
