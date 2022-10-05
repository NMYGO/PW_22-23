package pw.p1.other;

import pw.p1.classes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMiscelanea {
	public static void menu () {
		System.out.println("1. Crear Pista");
        System.out.println("2. Crear Kart");
        System.out.println("3. Asociar Karts y Pistas");
        System.out.println("4. Listar Pistas en mantenimiento");
        System.out.println("5. Recoger Array de pistas libres");
        System.out.println("--------------------------------------------");
        System.out.println("6. Registrar Usuario");
        System.out.println("7. Modificar Usuario");
        System.out.println("8. Listar Usuarios");
        System.out.println("--------------------------------------------");
        System.out.println("9. Hacer reserva individual");
        System.out.println("10. Hacer reserva en bono");
        System.out.println("11. PLACEHOLDER");
        System.out.println("12. PLACEHOLDER");
        System.out.println("13. PLACEHOLDER");
        System.out.println("14. PLACEHOLDER");
        System.out.println("--------------------------------------------");
        System.out.println("0. Salir");
        System.out.println("");
	}
	
	public static boolean login (GestorUsuarios GestorUsuarios_, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		
		for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
			if (GestorUsuarios_.arrayUsuarios.get(i).getCorreo().equals(correo)) {
				return true;
			}
		}
		return false;
	}
	
}

/*public void asociarKartPista() {

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
}*/
