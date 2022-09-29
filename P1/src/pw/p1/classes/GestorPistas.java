package pw.p1.classes;

public class GestorPistas{

	private ArrayList<Pista> arrayPistas;
	private ArrayList<Kart> arrayKarts;

	public GestorPistas(){}

	public boolean crearPista(Character nombre, Boolean estado, Dificultad dificultad, Integer maxkarts){
		for (int i = 0;i < arrayPistas.size() ; i++) {
			if (nombre ==(arrayPistas.get(i)).getNombre()) {
				return false;
			}
		}
		Pista newPista = new Pista(nombre, estado, dificultad, maxkarts);
		arrayPistas.add(newPista);
		return true;
	}

	public boolean crearKart(Integer id, Boolean tipo, Estado estado){
		for (int i = 0;i < arrayKarts.size() ; i++) {
			if (id ==(arrayKarts.get(i)).getId()) {
				return false;
			}
		}
		Pista newKart = new Kart(id, tipo, estado);
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

			if (!pista_.getEstado() && pista_.lkart.size() != pista_getMaxkarts(){
				maxkarts_ = pista_.getMaxkarts();

				for (int j = 0; j< arrayKarts.size() && cont <= maxkarts_; j++) {
					kart_ = arrayKarts.get(j);

					if (kart_.estado == "DISPONIBLE") {

						if (kart_.isTipo() == true && (pista_.getDificultad() == "FAMILIAR" || pista_.getDificultad() == "INFANTIL")) {
							listakarts_ = pista_.getLkart();
							listakarts_.add(kart_);
							pista_.setLkart(listakarts_);
							cont++;
						}
						if (kart_.isTipo() == false && (pista_.getDificultad() == "FAMILIAR" || pista_.getDificultad() == "ADULTO")) {
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
			if (arrayPistas.get(i).isEstado) {
				System.out.println(arrayPistas.get(i).getNombre);
			}
		}
	}

	public ArrayList<Pistas> pistasLibres(Integer kartnum, Dificultad tipo){
		
		ArrayList<Pista> arraypistas;

		for (int i = 0; i< arrayPistas.size() ; i++) {
			ArrayList<Kart> listakarts = arrayPistas.get(i).getLkart();
			if (kartnum <= listakarts.size() && tipo == arrayPistas.get(i).getDificultad() && arrayPistas.get(i).isEstado()) {
				arraypistas.add(arrayPistas.get(i));
			}
		}
		return arraypistas;
	}
}