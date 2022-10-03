package pw.p1.classes;

import java.util.ArrayList;

public class GestorPistas{

	private ArrayList<Pista> arrayPistas;
	private ArrayList<Kart> arrayKarts;

	public GestorPistas(){}

	public boolean crearPista(Character nombre, Boolean estado, Pista.Dificultad dificultad, Integer maxkarts){
		for (int i = 0;i < arrayPistas.size() ; i++) {
			if (nombre ==(arrayPistas.get(i)).getNombre()) {
				return false;
			}
		}
		Pista newPista = new Pista(nombre, estado, dificultad, maxkarts);
		arrayPistas.add(newPista);
		return true;
	}

	public boolean crearKart(Integer id, Boolean tipo, Kart.Estado estado){
		for (int i = 0;i < arrayKarts.size() ; i++) {
			if (id ==(arrayKarts.get(i)).getId()) {
				return false;
			}
		}
		Kart newKarts = new Kart(id, tipo, estado);
		arrayKarts.add(newKarts);
		return true;
	}

	public void asociaKartPista(){

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

	public void listaPistasMantenimiento(){
		for (int i = 0;i< arrayPistas.size() ; i++) {
			if (arrayPistas.get(i).isEstado()) {
				System.out.println(arrayPistas.get(i).getNombre());
			}
		}
	}

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