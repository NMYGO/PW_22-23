package pw.p3.business.circuit;

import pw.p3.data.dao.KartDAO;
import pw.p3.business.kart.KartDTO;
import pw.p3.data.Estado;
import pw.p3.data.Dificultad;
import java.util.ArrayList;

/**
 * 
 * Clase que resuelve las funcionalidades del DTOPista
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class CircuitFunctionality {	
	
	/**
	 * Función para encontrar los karts disponibles
	 * @param arrayKarts ArrayList<DTOKart> con todos los karts existentes
	 * @return Devuelve una lista de los karts disponibles
	 **/
	
	public ArrayList<KartDTO> consultarKartsDisponibles(ArrayList<KartDTO> arrayKarts) {
		ArrayList<KartDTO> dkart = new ArrayList<KartDTO>();
		for(int i = 0; i < arrayKarts.size(); i++) {
			if((arrayKarts.get(i)).getEstado() == Estado.DISPONIBLE) {
				dkart.add(arrayKarts.get(i));
			}
		}
		return dkart;
	}

	/**
	 * Función que asocia karts a las pistas dependiendo de la dificultad de ambos
	 * @param kart DTOKart con el kart que se quiere asociar
	 * @param pista DTOPista con la pista que se quiere asociar
	 * @return Devuelve el kart asociado
	 **/
	
	public KartDTO asociarKartAPista(KartDTO kart, CircuitDTO pista) {
		KartDAO kartTabla = new KartDAO();
		ArrayList <KartDTO> kartsPista = kartTabla.solicitarKartsPista(pista.getNombre());
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
