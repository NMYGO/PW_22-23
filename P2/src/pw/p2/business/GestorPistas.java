package pw.p2.business;

import pw.p2.data.DTOPistaFuncionalidades;
import pw.p2.data.DAO.DAOPista;
import pw.p2.data.DAO.DAOKart;
import pw.p2.data.Dificultad;
import pw.p2.data.Estado;
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

public class GestorPistas{

	/**
	 * Constructor por defecto
	 **/
	public GestorPistas(){}

	/**
	 * Funcion que crea una pista
	 * @param nombre
	 * @param estado
	 * @param dificultad
	 * @param maxkarts
	 * @return
	 */
	
	public boolean crearPista (String nombre, Boolean estado, Dificultad dificultad, Integer maxkarts) {		
		DAOPista pistaTabla = new DAOPista();
		DTOPista pista = new DTOPista(nombre, estado, dificultad, maxkarts);
		
		if(pistaTabla.escribirPistaInsert(pista) == 0) {
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
	 * @param id
	 * @param tipo
	 * @param estado
	 * @param nombrePista
	 * @return
	 */
	
	public boolean crearKart (Integer id, Boolean tipo, Estado estado, String nombrePista) {				
		DAOKart kartTabla = new DAOKart();
		DTOKart kart = new DTOKart(id, tipo, estado, nombrePista);
		
		if (kartTabla.escribirKartInsert(kart) == 0) {
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
	 * @param idkart
	 * @param nombrepista
	 * @return
	 */
	
	public boolean asociarKartPista (Integer idkart, String nombrepista) {
		DAOKart kartTabla = new DAOKart();
		DAOPista pistaTabla = new DAOPista();
		DTOKart kart = kartTabla.solicitarKart(idkart);
		ArrayList <DTOPista> pistas = pistaTabla.solicitarPistas();
		
		for (int i = 0;i< pistas.size() ; i++) {
			if (pistas.get(i).getNombre().equals(nombrepista)) {
				if(!pistas.get(i).isEstado()) {
					if(kart.getEstado() == Estado.DISPONIBLE) {
						DTOPistaFuncionalidades pistaF = new DTOPistaFuncionalidades();
						kart = pistaF.asociarKartAPista(kart, pistas.get(i));
						if(kart != null) {
							if (!(kartTabla.escribirKartUpdate(kart) == 0)) {
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
	 */
	
	public void listaPistasMantenimiento () {
		DAOPista pistaTabla = new DAOPista();
		ArrayList <DTOPista> pistas = pistaTabla.solicitarPistas();
		
		for (int i = 0;i< pistas.size() ; i++) {
			if (pistas.get(i).isEstado()) {
				System.out.println(pistas.get(i).toString());
			}
		}
	}
	
	/**
	 * Devuelve las pistas libres
	 * @param participantes
	 * @param dificultad
	 * @return
	 */
	
	public ArrayList<DTOPista> pistasLibres (Integer participantes, Dificultad dificultad) {							
		DAOPista pistaTabla = new DAOPista();
		
		ArrayList <DTOPista> pistas = pistaTabla.solicitarPistasLibres(false, participantes, dificultad);
		return pistas;
	}
	
	/**
	 * Lista las pistas disponibles por pantalla
	 */
	
	public void listarKartsDisponibles () {
		DAOKart kartTabla = new DAOKart();
		ArrayList <DTOKart> karts = kartTabla.solicitarKarts();
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
