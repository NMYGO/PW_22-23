package pw.p1.classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Clase que gestiona las pistas
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class GestorPistas{

	/* Atributos */

	public ArrayList<Pista> arrayPistas = new ArrayList<Pista>();
	public ArrayList<Kart> arrayKarts = new ArrayList<Kart>();

	/**
	 * Constructor por defecto
	 */
	public GestorPistas(){}

	/**
	 * Funcion que crea una pista y la añade al arrayPistas
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve un booleano 
	 */
	
	public boolean crearPista(Scanner scan_) {
		System.out.println("Introduzca el nombre de pista");
			String nombre = scan_.nextLine();							
		System.out.println("Introduzca el estado de pista");
			Boolean estado = Boolean.parseBoolean(scan_.nextLine());
		System.out.println("Introduzca la dificultad de pista");
			Pista.Dificultad dificultad = Pista.Dificultad.valueOf(scan_.nextLine());
		System.out.println("Introduzca el numero maximo de karts de pista");
			Integer maxkarts = Integer.parseInt(scan_.nextLine());
			
		for (int i = 0;i < arrayPistas.size() ; i++) {
			if (arrayPistas.get(i).getNombre().equals(nombre)) {
				System.out.println("Error. Esa pista ya existe");
				System.out.println("-------------------------------------");
				System.out.println("");
				return false;
			}
		}
		
		Pista newPista = new Pista(nombre, estado, dificultad, maxkarts);
		arrayPistas.add(newPista);
		System.out.println("Pista creada con exito");
		System.out.println("-------------------------------------");
		System.out.println("");
		return true;
	}

	/**
	 * Funcion que crea un kart y lo añade al arrayPistas
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve un booleano 
	 */
	
	public boolean crearKart(Scanner scan_) {
		System.out.println("Introduzca el identificador de kart");
			Integer id = Integer.parseInt(scan_.nextLine());							
		System.out.println("Introduzca el tipo de kart");
			Boolean tipo = Boolean.parseBoolean(scan_.nextLine());
		System.out.println("Introduzca el estado de kart");
			Kart.Estado estado = Kart.Estado.valueOf(scan_.nextLine());
		
		for (int i = 0;i < arrayKarts.size() ; i++) {
			if (id == (arrayKarts.get(i)).getId()) {
				System.out.println("Error. Ese kart ya existe");
				System.out.println("-------------------------------------");
				System.out.println("");
				return false;
			}
		}
		Kart newKarts = new Kart(id, tipo, estado);
		arrayKarts.add(newKarts);	
		System.out.println("Kart creado con exito");
		System.out.println("-------------------------------------");
		System.out.println("");
		return true;
	}

	/**
	 * Funcion que añade karts válidos al array de karts de las pistas válidas
	 * @param idkart Identificador del kart a asociar
	 * @param nombrepista Nombre de la pista a la que asociamos los karts
	 * @return Devuelve un booleano
	 */
	
	public boolean asociarKartPista(Integer idkart, String nombrepista) {
		for (int i = 0;i< arrayPistas.size() ; i++) {
			if (arrayPistas.get(i).getNombre().equals(nombrepista)) {
				if(!arrayPistas.get(i).isEstado()) {
					ArrayList<Kart> listakarts = arrayPistas.get(i).consultarKartsDisponibles(arrayKarts);
					for (int j = 0;j< listakarts.size() ; j++) {
						if (idkart == (listakarts.get(j).getId())) {
							if(arrayPistas.get(i).asociarKartAPista(listakarts.get(j), arrayPistas.get(i))) {							
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
		System.out.println("Error. No se ha podido asociar el kart a la pista");
		System.out.println("-------------------------------------");
		System.out.println("");
		return true;
		
	}

	/**
	 * Función que lista por pantalla el nombre de las pistas en mantenimiento
	 */
	
	public void listaPistasMantenimiento(){
		for (int i = 0;i< arrayPistas.size() ; i++) {
			if (arrayPistas.get(i).isEstado()) {
				System.out.println(arrayPistas.get(i).toString());
			}
		}
	}

	
	/**
	 * Funcion que devuelve un array de las pistas libres con un minimo numero de karts
	 * @param scan_
	 * @param kartnum número de karts de la pista
	 * @param dificultad dificultad de la pista (INFANTIL, FAMILIAR, ADULTA)
	 * @return Devuelve array de pistas libres
	 */
	public ArrayList<Pista> pistasLibres(Scanner scan_, Integer kartnum, Pista.Dificultad dificultad){							

		ArrayList<Pista> arraypistaslibres_ = new ArrayList<Pista>();
		ArrayList<Kart> listakarts = new ArrayList<Kart>();
		
		for (int i = 0; i< arrayPistas.size() ; i++) {
			listakarts = arrayPistas.get(i).getLkart();
			if ((kartnum <= listakarts.size()) && (dificultad == arrayPistas.get(i).getDificultad()) && (!arrayPistas.get(i).isEstado())) {
				arraypistaslibres_.add(arrayPistas.get(i));
			}
		}
		return arraypistaslibres_;
	}
}