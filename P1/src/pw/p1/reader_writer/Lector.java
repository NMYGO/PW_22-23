package pw.p1.reader_writer;

import java.time.LocalDate;
import java.io.*;
import pw.p1.classes.*;

public class Lector {
	public static void lector(GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, GestorReservas GestorReservas_) throws IOException{
	    String line;

	    File Ufile = new File("Usuarios.txt");
	    if(Ufile.isFile()) {
		    BufferedReader readerU_ = new BufferedReader(new FileReader(new File("Usuarios.txt")));
			while((line = readerU_.readLine()) != null) {
		    	String nombre = line.substring(line.indexOf("e=") + 2, line.indexOf(", apellidos"));
		    	String apellidos = line.substring(line.indexOf("s=") + 2, line.indexOf(", nacimiento"));
		    	String snacimiento = line.substring(line.indexOf("o=") + 2, line.indexOf(", inscripcion"));
		    		LocalDate nacimiento = LocalDate.parse(snacimiento);
		    	String correo = line.substring(line.indexOf("eo=") + 3, line.indexOf("]"));
		    	Usuario newUsuario = new Usuario(nombre, apellidos, nacimiento, correo); 
		    	GestorUsuarios_.arrayUsuarios.add(newUsuario);
			}
			readerU_.close();
	    }else {
	    	if(Ufile.createNewFile()) {
	    		System.out.println("Se ha creado el fichero Usuarios.txt porque no existia");
	    	}
	    }
	    
	    File Pfile = new File("Pistas.txt");
	    if(Pfile.isFile()) {
	    	BufferedReader readerP_ = new BufferedReader(new FileReader(new File("Pistas.txt")));
		    while((line = readerP_.readLine()) != null) {
		    	String nombre = line.substring(line.indexOf("e=") + 2, line.indexOf(", estado"));
		    	String sestado = line.substring(line.indexOf("s=") + 2, line.indexOf(", dificultad"));
		    		Boolean estado = Boolean.parseBoolean(sestado);
		    	String sdificultad = line.substring(line.indexOf("d=") + 2, line.indexOf(", maxkarts"));
		    		Pista.Dificultad dificultad = Pista.Dificultad.valueOf(sdificultad);
		    	String smaxkarts = line.substring(line.indexOf("s=") + 2, line.indexOf(", lkart"));
		    		int maxkarts = Integer.parseInt(smaxkarts);
		    	Pista newPista = new Pista(nombre, estado, dificultad, maxkarts); 
		    	GestorPistas_.arrayPistas.add(newPista);
			}
		    readerP_.close();
	    }else {
	    	if(Pfile.createNewFile()) {
	    		System.out.println("Se ha creado el fichero Pistas.txt porque no existia");
	    	}
	    }
	    
	    File Kfile = new File("Karts.txt");
	    if(Kfile.isFile()) {
	    	BufferedReader readerK_ = new BufferedReader(new FileReader(new File("Karts.txt")));
		    while((line = readerK_.readLine()) != null) {
		    	String sid = line.substring(line.indexOf("s=") + 2, line.indexOf(", lkart"));
					int id = Integer.parseInt(sid);
		    	String stipo = line.substring(line.indexOf("s=") + 2, line.indexOf(", dificultad"));
		    		Boolean tipo = Boolean.parseBoolean(stipo);
		    	String sestado = line.substring(line.indexOf("d=") + 2, line.indexOf(", maxkarts"));
		    		Kart.Estado estado = Kart.Estado.valueOf(sestado);
		    	Kart newKart = new Kart(id, tipo, estado); 
		    	GestorPistas_.arrayKarts.add(newKart);
			}
		    readerK_.close();
	    }else {
	    	if(Kfile.createNewFile()) {
	    		System.out.println("Se ha creado el fichero Karts.txt porque no existia");
	    	}
	    }
	}
}
