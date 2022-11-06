package pw.p2.display;

import java.util.Properties;
import java.io.*;

/**
 * Clase que gestiona todas las interacciones con la base de datos usando el fichero sql.properties.txt
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class SQLPropiedades {
	
	public static void propiedadesConfiguracion() {
			
		Properties prop = new Properties();
		try {
			BufferedReader reader_sqlproperties = new BufferedReader(new FileReader(new File("sql.properties.txt")));
			prop.load(reader_sqlproperties);	
			
			String consultaBono = prop.getProperty("consultaBono");
			String consultaBonoEspecifico = prop.getProperty("consultaBonoEspecifico");
			String updateBono = prop.getProperty("updateBono");
			String insertBono = prop.getProperty("insertBono");
			String updateReservaBono = prop.getProperty("updateReservaBono");
			String insertReservaBono = prop.getProperty("insertReservaBono");
			
			String consultaKart = prop.getProperty("consultaKart");
			String consultaKartID = prop.getProperty("consultaKartID");
			String consultaKartPista = prop.getProperty("consultaKartPista");
			String updateKart = prop.getProperty("updateKart");
			String insertKart = prop.getProperty("insertKart");
			
			String consultaPista = prop.getProperty("consultaPista");
			String consultaPistaEstadoDificultad = prop.getProperty("consultaPistaEstadoDificultad");
			String consultaPistaNombre = prop.getProperty("consultaPistaNombre");
			String updatePista = prop.getProperty("updatePista");
			String insertPista = prop.getProperty("insertPista");
			
			String consultaReservaInfantil = prop.getProperty("consultaReservaInfantil");
			String consultaReservaAdulto = prop.getProperty("consultaReservaAdulto");
			String consultaReservaFamiliar = prop.getProperty("consultaReservaFamiliar");
			String updateReserva = prop.getProperty("updateReserva");
			String insertReserva = prop.getProperty("insertReserva");
			String deleteReserva = prop.getProperty("deleteReserva");
			
			String consultaUsuario = prop.getProperty("consultaUsuario");
			String consultaUsuarioEspecifico = prop.getProperty("consultaUsuarioEspecifico");
			String updateUsuario = prop.getProperty("updateUsuario");
			String insertUsuario = prop.getProperty("insertUsuario");
			
			System.out.println("Consulta y Actualizacion Bonos-----------");
			System.out.println(consultaBono);
			System.out.println(consultaBonoEspecifico);
			System.out.println(updateBono);
			System.out.println(insertBono);
			System.out.println(updateReservaBono);
			System.out.println(insertReservaBono);
			System.out.println("Consulta y Actualizacion Karts-----------");
			System.out.println(consultaKart);
			System.out.println(consultaKartID);
			System.out.println(consultaKartPista);
			System.out.println(updateKart);
			System.out.println(insertKart);
			System.out.println("Consulta y Actualizacion Pista-----------");
			System.out.println(consultaPista);
			System.out.println(consultaPistaEstadoDificultad);
			System.out.println(consultaPistaNombre);
			System.out.println(updatePista);
			System.out.println(insertPista);
			System.out.println("Consulta y Actualizacion Reservas-----------");
			System.out.println(consultaReservaInfantil);
			System.out.println(consultaReservaAdulto);
			System.out.println(consultaReservaFamiliar);
			System.out.println(updateReserva);
			System.out.println(insertReserva);
			System.out.println(deleteReserva);
			System.out.println("Consulta y Actualizacion Usuarios-----------");
			System.out.println(consultaUsuario);
			System.out.println(consultaUsuarioEspecifico);
			System.out.println(updateUsuario);
			System.out.println(insertUsuario);
			System.out.println("--------------------------------------------");
	
			
			BufferedReader reader_configproperties = new BufferedReader(new FileReader(new File("config.properties.txt")));
			prop.load(reader_configproperties);

			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String name = prop.getProperty("nombre");
			
			System.out.println("Consulta y actualizacion de datos--------------------------------------");
			System.out.println("URL=" + url);
			System.out.println("User=" + user);
			System.out.println("Password" + password);
			System.out.println("Name=" + name);			
			System.out.println("-------------------------------------------------");
			
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}			
	}
}
