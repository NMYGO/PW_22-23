package pw.p2.display;

import java.util.Properties;
import java.io.*;
import java.util.*;


/**
 * Clase que se encarga de gestionar las rutas de los archivos
 * */

public class FicheroPropiedades {

	
	public static void propiedades(GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, GestorReservas GestorReservas_) {
		Properties prop = new Properties();
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(new File("Propiedades.txt")));
			prop.load(reader);		
			ArrayList<String> k_pistas = new ArrayList<String>(), r_reservasBonos = new ArrayList<String>();
			
			String usuarios = prop.getProperty("usuarios");			
			String pistas = prop.getProperty("pistas");	
			for (int i = 0; i < GestorPistas_.arrayPistas.size(); i++) {
				k_pistas.add(prop.getProperty(GestorPistas_.arrayPistas.get(i).getNombre().toLowerCase()));
			}
			String karts = prop.getProperty("karts");	
			String reservasIndividuales = prop.getProperty("reservasIndividuales");	
			String reservasBonos = prop.getProperty("reservasBonos");	
			for (int i = 0; i < GestorReservas_.arrayBonos.size(); i++) {
				r_reservasBonos.add(prop.getProperty((GestorReservas_.arrayBonos.get(i).getbUsuario() + "_bono").toLowerCase()));
			}	
			
			System.out.println("PROPIEDADES--------------------------------------");
			System.out.println(usuarios);
			System.out.println(pistas);
			for (int i = 0; i < k_pistas.size(); i++) {
				System.out.println(k_pistas.get(i));
			}
			System.out.println(karts);
			System.out.println(reservasIndividuales);
			System.out.println(reservasBonos);
			for (int i = 0; i < r_reservasBonos.size(); i++) {
				System.out.println(r_reservasBonos.get(i));
			}
			System.out.println("-------------------------------------------------");
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
