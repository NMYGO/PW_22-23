package pw.p1.other;

import java.io.*;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pw.p1.classes.*;

/**
 * Clase que se encarga de gestionar las rutas de los archivos
 * */

public class FicheroPropiedades {

	
	public String ficheros(String filename) {
		Properties prop = new Properties();
		String rutaFichero = new String();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("properties.txt")));
			prop.load(reader);
			
			rutaFichero = prop.getProperty(filename);			

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rutaFichero;
		}

}
