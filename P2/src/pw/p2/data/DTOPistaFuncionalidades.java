package pw.p2.data;

import pw.p2.business.DTOPista;
import pw.p2.data.DAO.DAOKart;
import pw.p2.business.DTOKart;
import java.util.ArrayList;

/**
 * 
 * Clase Pista
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class DTOPistaFuncionalidades {	
	
	/**
	 * Función para encontrar los karts disponibles
	 * @param arrayKarts
	 * @return Devuelve una lista de los karts disponibles
	 */
	
	/**public ArrayList<DTOKart> consultarKartsDisponibles(ArrayList<DTOKart> arrayKarts) {
		ArrayList<DTOKart> dkart = new ArrayList<DTOKart>();
		for(int i = 0; i < arrayKarts.size(); i++) {
			if((arrayKarts.get(i)).getEstado() == Estado.DISPONIBLE) {
				dkart.add(arrayKarts.get(i));
			}
		}
		return dkart;
	}**/

	/**
	 * Función que asocia karts a las pistas dependiendo de la dificultad de ambos
	 * @param kart
	 * @param pista
	 * @return
	 */
	
	public DTOKart asociarKartAPista(DTOKart kart, DTOPista pista) {
		DAOKart kartTabla = new DAOKart();
		ArrayList <DTOKart> kartsPista = kartTabla.solicitarKartsPista(pista.getNombre());
		if(pista.getMaxkarts() > kartsPista.size()) {
			if(pista.getDificultad() == Dificultad.INFANTIL && kart.isTipo()) {
				kart.setEstado(Estado.RESERVADO);
				kart.setNombrePista(pista.getNombre());
				return kart;
			}else if(pista.getDificultad() == Dificultad.ADULTO && !kart.isTipo()) {
				kart.setEstado(Estado.RESERVADO);
				kart.setNombrePista(pista.getNombre());
				return kart;
			}else if(pista.getDificultad() == Dificultad.FAMILIAR) {
				kart.setEstado(Estado.RESERVADO);
				kart.setNombrePista(pista.getNombre());
				return kart;
			}
		}
		return null;
	}
}
