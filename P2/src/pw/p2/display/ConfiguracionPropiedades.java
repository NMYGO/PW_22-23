package pw.p2.display;

import java.util.Properties;
import java.io.*;

/**
 * Clase usada para comprobar la configuración para acceder a la base de datos usando el fichero config.properties.txt (de forma visual)
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class ConfiguracionPropiedades 

{
	public static void propiedadesConfiguracion() {
		
		Properties prop = new Properties();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("config.properties.txt")));
			prop.load(reader);
			
			
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String name = prop.getProperty("name");
			
			System.out.println("PROPIEDADES CONFIGURACION--------------------------------------");
			System.out.println("URL="+url);
			System.out.println("User="+user);
			System.out.println("Password"+password);
			System.out.println("Name="+name);
			
			System.out.println("-------------------------------------------------");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}
}
