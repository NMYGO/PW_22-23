package pw.p3.business.circuit;

import pw.p3.data.dao.*;
import pw.p3.business.kart.KartDTO;
import pw.p3.data.Dificultad;
import pw.p3.data.Estado;
import java.util.ArrayList;

/**
 * 
 * Clase que gestiona las pistas
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

public class CircuitManager{

	/**
	 * Constructor por defecto
	 **/
	public CircuitManager(){}

	/**
	 * Funcion que crea una pista
	 * @param nombre Nombre de pista
	 * @param estado Estado de pista
	 * @param dificultad Dificultad de pista
	 * @param maxkarts Maximo de karts de la pista
	 * @return Devuelve true si la pista se ha creado, false si no se ha creado
	 */
	
	public boolean crearPista (String path, String nombre, Boolean estado, Dificultad dificultad, Integer maxkarts) {		
		CircuitDAO pistaTabla = new CircuitDAO();
		CircuitDTO pista = new CircuitDTO(nombre, estado, dificultad, maxkarts);
		
		if(pistaTabla.escribirPistaInsert(path, pista) == 0) {
			System.out.println("Error. Pista no creada");
			System.out.println("-------------------------------------");
			System.out.println("");
			return false;
		}		
		System.out.println("Pista creada con exito");
		System.out.println("-------------------------------------");
		System.out.println("");
		return true;
	}

	/**
	 * Funcion que crea un kart
	 * @param id Identificador de kart
	 * @param tipo Tipo de kart
	 * @param estado Estado de kart
	 * @param nombrePista Nombre de la pista a la que pertenece el kart
	 * @return Devuelve true si el kart se ha creado, false si no se ha creado
	 */
	
	public boolean crearKart (String path, Integer id, Boolean tipo, Estado estado, String nombrePista) {				
		KartDAO kartTabla = new KartDAO();
		KartDTO kart = new KartDTO(id, tipo, estado, nombrePista);
		
		if (kartTabla.escribirKartInsert(path, kart) == 0) {
			System.out.println("Error. Ese kart ya existe");
			System.out.println("-------------------------------------");
			System.out.println("");
			return false;
		}	
		System.out.println("Kart creado con exito");
		System.out.println("-------------------------------------");
		System.out.println("");
		return true;
	}

	/**
	 * Asocia un kart a una pista
	 * @param idkart Identificadr del kart que se quiere asociar
	 * @param nombrepista Nombre de la pista a la que se asocia el kart
	 * @return Devuelve true si el kart se ha asociado, false si no se ha asociado
	 */
	
	public boolean asociarKartPista (String path, Integer idkart, String nombrepista) {
		KartDAO kartTabla = new KartDAO();
		CircuitDAO pistaTabla = new CircuitDAO();
		KartDTO kart = kartTabla.solicitarKart(path, idkart);
		ArrayList <CircuitDTO> pistas = pistaTabla.solicitarPistas(path);
		
		for (int i = 0;i< pistas.size() ; i++) {
			if (pistas.get(i).getNombre().equals(nombrepista)) {
				if(!pistas.get(i).isEstado()) {
					if(kart.getEstado() == Estado.DISPONIBLE) {
						CircuitFunctionality pistaF = new CircuitFunctionality();
						kart = pistaF.asociarKartAPista(path, kart, pistas.get(i));
						if(kart != null) {
							if (!(kartTabla.escribirKartUpdate(path, kart) == 0)) {
								System.out.println("Kart asociado con exito");
								System.out.println("-------------------------------------");
								System.out.println("");
								return true;
							}
						}
					}
				}
			}
		}
		System.out.println("Error. Kart no asociado");
		System.out.println("-------------------------------------");
		System.out.println("");
		return false;
	}

	/**
	 * Lista las pistas en mantenimiento
	 * @return
	 */
	
	public void listaPistasMantenimiento (String path) {
		CircuitDAO pistaTabla = new CircuitDAO();
		ArrayList <CircuitDTO> pistas = pistaTabla.solicitarPistas(path);
		
		for (int i = 0;i< pistas.size() ; i++) {
			if (pistas.get(i).isEstado()) {
				System.out.println(pistas.get(i).toString());
			}
		}
	}
	
	/**
	 * Devuelve las pistas libres para un numero concreto de participantes
	 * @param participantes Numero de participantes
	 * @param dificultad Dificultad de la pista
	 * @return ArrayList<DTOPista> de las pistas libres
	 */
	
	public ArrayList<CircuitDTO> pistasLibres (String path, Integer participantes, Dificultad dificultad) {							
		CircuitDAO pistaTabla = new CircuitDAO();
		
		ArrayList <CircuitDTO> pistas = pistaTabla.solicitarPistasLibres(path, false, participantes, dificultad);
		return pistas;
	}
	
	/**
	 * Lista las pistas disponibles por pantalla
	 * @return
	 */
	
	public void listarKartsDisponibles (String path) {
		KartDAO kartTabla = new KartDAO();
		ArrayList <KartDTO> karts = kartTabla.solicitarKarts(path);
		for(int i = 0; i < karts.size(); i++) {
			if(karts.get(i).getEstado() == Estado.DISPONIBLE) {
				System.out.println(karts.get(i).toString());
			}else if(karts.get(i).getEstado() == Estado.RESERVADO){
				System.out.println(karts.get(i).toString());
			}else {
				System.out.println(karts.get(i).toString());
			}
		}
	}
}
