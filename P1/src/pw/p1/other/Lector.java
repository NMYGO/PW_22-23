package pw.p1.other;

import java.time.LocalDate;
import java.io.*;
import pw.p1.classes.*;
import pw.p1.factory_reserva.*;

/**
 * Clase que gestiona la lectura de los ficheros
 * @author 
 * */

public class Lector {
	
	/**
	 * Funcion que lee el contenido de los ficheros .txt y los carga en los ArrayList
	 * @param GestorPistas
	 * @param GestorUsuarios
	 * @param GestorReservas
	 * @return void
	 */
	
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
	    		System.out.println("--------------------------------------------------------------------------");
	    		System.out.println("Se ha creado el fichero Usuarios.txt porque no existia");
	    		System.out.println("--------------------------------------------------------------------------");
	    	}
	    }
	    
	    File Pfile = new File("Pistas.txt");
	    if(Pfile.isFile()) {
	    	BufferedReader readerP_ = new BufferedReader(new FileReader(new File("Pistas.txt")));
		    while((line = readerP_.readLine()) != null) {
		    	String nombre = line.substring(line.indexOf("e=") + 2, line.indexOf(", estado"));
		    	String sestado = line.substring(line.indexOf("o=") + 2, line.indexOf(", dificultad"));
		    		Boolean estado = Boolean.parseBoolean(sestado);
		    	String sdificultad = line.substring(line.indexOf("d=") + 2, line.indexOf(", maxkarts"));
		    		Pista.Dificultad dificultad = Pista.Dificultad.valueOf(sdificultad);
		    	String smaxkarts = line.substring(line.indexOf("s=") + 2, line.indexOf("]"));
		    		int maxkarts = Integer.parseInt(smaxkarts);
		    	Pista newPista = new Pista(nombre, estado, dificultad, maxkarts); 
		    	GestorPistas_.arrayPistas.add(newPista);
			}
		    readerP_.close();
	    }else {
	    	if(Pfile.createNewFile()) {
	    		System.out.println("--------------------------------------------------------------------------");
	    		System.out.println("Se ha creado el fichero Pistas.txt porque no existia");
	    		System.out.println("--------------------------------------------------------------------------");
	    	}
	    }
	    
	    File Kfile = new File("Karts.txt");
	    if(Kfile.isFile()) {
	    	BufferedReader readerK_ = new BufferedReader(new FileReader(new File("Karts.txt")));
		    while((line = readerK_.readLine()) != null) {
		    	String sid = line.substring(line.indexOf("d=") + 2, line.indexOf(", tipo"));
					int id = Integer.parseInt(sid);
		    	String stipo = line.substring(line.indexOf("o=") + 2, line.indexOf(", estado"));
		    		Boolean tipo = Boolean.parseBoolean(stipo);
		    	String sestado = line.substring(line.indexOf("do=") + 3, line.indexOf("]"));
		    		Kart.Estado estado = Kart.Estado.valueOf(sestado);
		    	Kart newKart = new Kart(id, tipo, estado); 
		    	GestorPistas_.arrayKarts.add(newKart);
			}
		    readerK_.close();
	    }else {
	    	if(Kfile.createNewFile()) {
	    		System.out.println("--------------------------------------------------------------------------");
	    		System.out.println("Se ha creado el fichero Karts.txt porque no existia");
	    		System.out.println("--------------------------------------------------------------------------");
	    	}
	    }
	    
	    File RIfile = new File("ReservasIndividuales.txt");
	    if(RIfile.isFile()) {
	    	BufferedReader readerRI_ = new BufferedReader(new FileReader(new File("ReservasIndividuales.txt")));
		    while((line = readerRI_.readLine()) != null) {
		    	String usuario = line.substring(line.indexOf("o=") + 2, line.indexOf(", fecha"));
		    	String sfecha = line.substring(line.indexOf("a=") + 2, line.indexOf(", duracion"));
		    		LocalDate fecha = LocalDate.parse(sfecha);
		    	String sduracion = line.substring(line.indexOf("n=") + 2, line.indexOf(", pista"));
		    		int duracion = Integer.parseInt(sduracion);
		    	String pista = line.substring(line.indexOf("ta=") + 3, line.indexOf(", precio"));
		    	String sprecio = line.substring(line.indexOf("cio=") + 4, line.indexOf(", descuento"));
		    		float precio = Float.parseFloat(sprecio);
		    	String sdescuento = line.substring(line.indexOf("to=") + 3, line.indexOf(", participantes"));
		    		int descuento = Integer.parseInt(sdescuento);
		    	String sparticipantesninios = line.substring(line.indexOf("ños=") + 4, line.indexOf(", participantes adultos"));
		    		int participantesninios = Integer.parseInt(sparticipantesninios);
		    	String sparticipantesadultos = line.substring(line.indexOf("tos=") + 4, line.indexOf("]"));
		    	int participantesadultos = Integer.parseInt(sparticipantesadultos);
	    		RIndividualCreador individualCreador = new RIndividualCreador();
	    		if(line.contains("-> Reserva Infantil")) {
	    			RInfantil newReserva = individualCreador.creaRInf(usuario, fecha, duracion, pista, precio, descuento, participantesninios); 
	    			GestorReservas_.arrayReservaIndividual.add(newReserva);
	    		}else if(line.contains("-> Reserva Familiar")) {
	    			RFamiliar newReserva = individualCreador.creaRFam(usuario, fecha, duracion, pista, precio, descuento, participantesninios, participantesadultos); 
	    			GestorReservas_.arrayReservaIndividual.add(newReserva);
	    		}else if(line.contains("-> Reserva Adulto")){
	    			RAdulto newReserva = individualCreador.creaRAdu(usuario, fecha, duracion, pista, precio, descuento, participantesadultos); 
	    			GestorReservas_.arrayReservaIndividual.add(newReserva);
	    		}else {
	    			System.out.println("Error. Fallo en el lector de reserva individual");
	    		}
			}
		    readerRI_.close();
	    }else {
	    	if(RIfile.createNewFile()) {
	    		System.out.println("--------------------------------------------------------------------------");
	    		System.out.println("Se ha creado el fichero ReservasIndividuales.txt porque no existia");
	    		System.out.println("--------------------------------------------------------------------------");
	    	}
	    }

	    File RBfile = new File("ReservasBonos.txt");
	    if(RBfile.isFile()) {
	    	BufferedReader readerRB_ = new BufferedReader(new FileReader(new File("ReservasBonos.txt")));
		    while((line = readerRB_.readLine()) != null) {
		    	String sid = line.substring(line.indexOf("d=") + 2, line.indexOf(", sesion"));
		    		int id = Integer.parseInt(sid);
		    	String ssesion = line.substring(line.indexOf("ion=") + 4, line.indexOf(", bUser"));
		    		int sesion = Integer.parseInt(ssesion);
		    	String bUsuario = line.substring(line.indexOf("er=") + 3, line.indexOf(", tipo"));
		    	String stipo = line.substring(line.indexOf("po=") + 3, line.indexOf("]"));
		    		RBonoCreador.Tipo tipo = RBonoCreador.Tipo.valueOf(stipo);
	    		RBonoCreador newBono = new RBonoCreador(id, sesion, bUsuario, tipo);
	    		
	    		/*while(!line.contains("Bono")) {
			    	String usuario = line.substring(line.indexOf("rio=") + 4, line.indexOf(", fecha"));
			    	String sfecha = line.substring(line.indexOf("cha=") + 4, line.indexOf(", duracion"));
			    		LocalDate fecha = LocalDate.parse(sfecha);
			    	String sduracion = line.substring(line.indexOf("cion=") + 5, line.indexOf(", pista"));
			    		int duracion = Integer.parseInt(sduracion);
			    	String pista = line.substring(line.indexOf("ta=") + 3, line.indexOf(", precio"));
			    	String sprecio = line.substring(line.indexOf("cio=") + 4, line.indexOf(", descuento"));
			    		float precio = Float.parseFloat(sprecio);
			    	String sdescuento = line.substring(line.indexOf("to=") + 3, line.indexOf(", participantes"));
			    		int descuento = Integer.parseInt(sdescuento);
			    	String sparticipantes = line.substring(line.indexOf("s=") + 2, line.indexOf("]"));
			    		int participantes = Integer.parseInt(sparticipantes);
		    		RIndividualCreador individualCreador = new RIndividualCreador();
		    		if(line.contains("-> Reserva Infantil")) {
		    			RInfantil newReserva = individualCreador.creaRInf(usuario, fecha, duracion, pista, precio, descuento, participantes); 
		    			GestorReservas_.arrayBonos.add(newReserva);
		    		}else if(line.contains("-> Reserva Familiar")) {
		    			
		    		}else if(line.contains("-> Reserva Adulto")){
		    			
		    		}else {
		    			System.out.println("Error. Fallo en el lector de reserva individual");
		    		}
	    		}*/
		    	GestorReservas_.arrayBonos.add(newBono);
			}
		    readerRB_.close();
	    }else {
	    	if(RBfile.createNewFile()) {
	    		System.out.println("--------------------------------------------------------------------------");
	    		System.out.println("Se ha creado el fichero ReservasBonos.txt porque no existia");
	    		System.out.println("--------------------------------------------------------------------------");
	    	}
	    }
	}
}
