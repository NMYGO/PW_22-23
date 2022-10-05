package pw.p1.classes;

import java.util.ArrayList;
import java.io.*;

/**
 * Una clase que implementa las clases Pista y Kart
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
	 * Crea una pista y la añade al array
	 * @param nombre
	 * @param estado
	 * @param dificultad
	 * @param maxkarts
	 * @return
	 */
	public boolean crearPista(String nombre, Boolean estado, Pista.Dificultad dificultad, Integer maxkarts) throws IOException{
		for (int i = 0;i < arrayPistas.size() ; i++) {
			if (nombre ==(arrayPistas.get(i)).getNombre()) {
				return false;
			}
		}
		Pista newPista = new Pista(nombre, estado, dificultad, maxkarts);
		arrayPistas.add(newPista);
		
		/*BufferedWriter writerP_ = new BufferedWriter(new FileWriter(new File("Pistas.txt")));
		writerP_.write(newPista.toString());
		writerP_.close();*/ //Tengo que ver que hacer con el array de karts
		
		return true;
	}

	/**
	 * Crea un kart y lo añade al array
	 * @param id
	 * @param tipo
	 * @param estado
	 * @return
	 */
	public boolean crearKart(Integer id, Boolean tipo, Kart.Estado estado) {
		for (int i = 0;i < arrayKarts.size() ; i++) {
			if (id ==(arrayKarts.get(i)).getId()) {
				return false;
			}
		}
		Kart newKarts = new Kart(id, tipo, estado);
		arrayKarts.add(newKarts);		
		return true;
	}

	/**
	 * Añade karts válidos al array de karts de las pistas válidas
	 */
	public void asociarKartPista() {

		Pista pista_;
		Kart kart_;
		ArrayList<Kart> listakarts_;
		int maxkarts_, cont = 0;

		for (int i = 0;i< arrayPistas.size() ; i++) {
			pista_ = arrayPistas.get(i);
			ArrayList<Kart> list_ = pista_.getLkart();

			if (!pista_.isEstado() && list_.size() != pista_.getMaxkarts()){
				maxkarts_ = pista_.getMaxkarts();

				for (int j = 0; j< arrayKarts.size() && cont <= maxkarts_; j++) {
					kart_ = arrayKarts.get(j);

					if (kart_.getEstado() == Kart.Estado.DISPONIBLE) {

						if (kart_.isTipo() == true && (pista_.getDificultad() == Pista.Dificultad.FAMILIAR || pista_.getDificultad() == Pista.Dificultad.INFANTIL)) {
							listakarts_ = pista_.getLkart();
							listakarts_.add(kart_);
							pista_.setLkart(listakarts_);
							cont++;
						}
						if (kart_.isTipo() == false && (pista_.getDificultad() == Pista.Dificultad.FAMILIAR || pista_.getDificultad() == Pista.Dificultad.ADULTO)) {
							listakarts_ = pista_.getLkart();
							listakarts_.add(kart_);
							pista_.setLkart(listakarts_);
							cont++;
						}
					}
				}
			}
		}
	}

	/**
	 * Lista por pantalla el nombre de las pistas en mantenimiento
	 */
	public void listaPistasMantenimiento(){
		for (int i = 0;i< arrayPistas.size() ; i++) {
			if (arrayPistas.get(i).isEstado()) {
				System.out.println(arrayPistas.get(i).getNombre());
			}
		}
	}

	/**
	 * Devuelve un array de las pistas libres
	 * @param kartnum
	 * @param tipo
	 * @return
	 */
	public ArrayList<Pista> pistasLibres(Integer kartnum, Pista.Dificultad tipo){
		
		ArrayList<Pista> arraypistaslibres_ = new ArrayList<Pista>();

		for (int i = 0; i< arrayPistas.size() ; i++) {
			ArrayList<Kart> listakarts = arrayPistas.get(i).getLkart();
			if (kartnum <= listakarts.size() && tipo == arrayPistas.get(i).getDificultad() && arrayPistas.get(i).isEstado()) {
				arraypistaslibres_.add(arrayPistas.get(i));
			}
		}
		return arraypistaslibres_;
	}
}