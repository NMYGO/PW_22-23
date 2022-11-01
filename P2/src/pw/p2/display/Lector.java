package pw.p2.display;

import pw.p2.data.*;
import pw.p2.data.DAO.*;
import pw.p2.business.*;
import java.util.ArrayList;

/**
 * Clase que gestiona la lectura de los ficheros
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class Lector {
	
	/**
	 * Funcion que lee el contenido de los ficheros .txt y los carga en los ArrayList
	 * @param GestorPistas
	 * @param GestorUsuarios
	 * @param GestorReservas
	 * @return void
	 */
	
	public static void lector(GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, GestorReservas GestorReservas_){
		
	    /**DAOUsuario usuarioTabla = new DAOUsuario();
		ArrayList<DTOUsuario> usuarios = usuarioTabla.solicitarUsuarios();
		for (int i = 0; i < usuarios.size(); i++) {
			//System.out.println(usuarios.get(i).toString());
			Usuario newUsuario = new Usuario(usuarios.get(i).getNombre(), usuarios.get(i).getApellidos(), usuarios.get(i).getNacimiento(), usuarios.get(i).getInscripcion(), usuarios.get(i).getCorreo()); 
			GestorUsuarios_.arrayUsuarios.add(newUsuario);
		}
	    
		DAOKart kartTabla = new DAOKart();
		ArrayList<DTOKart> karts = kartTabla.solicitarKarts();
		for (int i = 0; i < karts.size(); i++) {
			//System.out.println(karts.get(i).toString());
			Kart newKart = new Kart(karts.get(i).getId(), karts.get(i).isTipo(), karts.get(i).getEstado()); 
			GestorPistas_.arrayKarts.add(newKart);
		}
			
		DAOPista pistaTabla = new DAOPista();
		ArrayList<DTOPista> pistas = pistaTabla.solicitarPistas();
		for (int i = 0; i < pistas.size(); i++) {
			//System.out.println(pistas.get(i).toString());
			Pista newPista = new Pista(pistas.get(i).getNombre(), pistas.get(i).isEstado(), pistas.get(i).getDificultad(), pistas.get(i).getMaxkarts()); 
			GestorPistas_.arrayPistas.add(newPista);
		}
	    
	    for (int i = 0; i < GestorPistas_.arrayPistas.size(); i++) {
	    	//System.out.println(GestorPistas_.arrayPistas.get(i).toString());
	    	ArrayList<DTOKart> kartsPista = kartTabla.solicitarKartsPista(GestorPistas_.arrayPistas.get(i).getNombre());
	    	for(int j = 0; j < kartsPista.size(); j++) {
	    		//System.out.println(kartsPista.get(j).toString());
	    		Kart kart = new Kart(kartsPista.get(j).getId(), kartsPista.get(j).isTipo(), kartsPista.get(j).getEstado());
	    		GestorPistas_.arrayPistas.get(i).lkart.add(kart);
	    	}	    	
	    }
	    
	    DAOReserva reservaTabla = new DAOReserva();
	    RIndividualCreador individualCreador = new RIndividualCreador();
		ArrayList<DTOReserva> reservas = reservaTabla.solicitarReservas();
		for (int i = 0; i < reservas.size(); i++) {
			//System.out.println(reservas.get(i).toString());
			if(reservas.get(i).getTipo() == Tipo.INFANTIL) {				
				DTORInfantil newReserva = individualCreador.creaRInf(reservas.get(i).getCorreo(), reservas.get(i).getFecha(), reservas.get(i).getDur(), reservas.get(i).getPista(), reservas.get(i).getPrecio(), reservas.get(i).getDesc(), reservas.get(i).getNiños());				
				GestorReservas_.arrayReservaIndividual.add(newReserva);
			}else if(reservas.get(i).getTipo() == Tipo.FAMILIAR) {
				DTORFamiliar newReserva = individualCreador.creaRFam(reservas.get(i).getCorreo(), reservas.get(i).getFecha(), reservas.get(i).getDur(), reservas.get(i).getPista(), reservas.get(i).getPrecio(), reservas.get(i).getDesc(), reservas.get(i).getNiños(), reservas.get(i).getAdultos());
				GestorReservas_.arrayReservaIndividual.add(newReserva);
			}else if(reservas.get(i).getTipo() == Tipo.ADULTO) {
				DTORAdulto newReserva = individualCreador.creaRAdu(reservas.get(i).getCorreo(), reservas.get(i).getFecha(), reservas.get(i).getDur(), reservas.get(i).getPista(), reservas.get(i).getPrecio(), reservas.get(i).getDesc(), reservas.get(i).getAdultos());				
				GestorReservas_.arrayReservaIndividual.add(newReserva);
			}			
		}

	    /**File RBfile = new File("ReservasBonos.txt");
	    if(RBfile.isFile()) {
	    	BufferedReader readerRB_ = new BufferedReader(new FileReader(new File("ReservasBonos.txt")));
		    while((line = readerRB_.readLine()) != null) {
		    	String sid = line.substring(line.indexOf("d=") + 2, line.indexOf(", sesion"));
		    		int id = Integer.parseInt(sid);
		    	String ssesion = line.substring(line.indexOf("ion=") + 4, line.indexOf(", bUser"));
		    		int sesion = Integer.parseInt(ssesion);
		    	String bUsuario = line.substring(line.indexOf("er=") + 3, line.indexOf(", tipo"));
		    	String stipo = line.substring(line.indexOf("po=") + 3, line.indexOf(", fecha de caducidad"));
		    		Tipo tipo = Tipo.valueOf(stipo);
	    		String sfcaducidad = line.substring(line.indexOf("ad=") + 3, line.indexOf("]"));
	    			LocalDate fcaducidad = LocalDate.parse(sfcaducidad);
	    		RBono newBono = new RBono(id, sesion, bUsuario, tipo, fcaducidad);
	    		
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
	    for (int i = 0; i < GestorReservas_.arrayBonos.size(); i++) {
		    File RBResfile = new File(GestorReservas_.arrayBonos.get(i).getbUsuario() + "_ReservasBonosRes.txt");
		    if(RBResfile.isFile()) {
		    	BufferedReader readerRBRes_ = new BufferedReader(new FileReader(new File(GestorReservas_.arrayBonos.get(i).getbUsuario() + "_ReservasBonosRes.txt")));
			    while((line = readerRBRes_.readLine()) != null) {
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
		    			GestorReservas_.arrayBonos.get(i).arrayReservas.add(newReserva);
		    		}else if(line.contains("-> Reserva Familiar")) {
		    			RFamiliar newReserva = individualCreador.creaRFam(usuario, fecha, duracion, pista, precio, descuento, participantesninios, participantesadultos); 
		    			GestorReservas_.arrayBonos.get(i).arrayReservas.add(newReserva);
		    		}else if(line.contains("-> Reserva Adulto")){
		    			RAdulto newReserva = individualCreador.creaRAdu(usuario, fecha, duracion, pista, precio, descuento, participantesadultos); 
		    			GestorReservas_.arrayBonos.get(i).arrayReservas.add(newReserva);
		    		}else {
		    			System.out.println("Error. Fallo en el lector de reserva de bono");
		    		}
			    }
			    readerRBRes_.close();
		    }else {
		    	if(RBfile.createNewFile()) {
		    		System.out.println("--------------------------------------------------------------------------");
		    		System.out.println("Se ha creado el fichero " + GestorReservas_.arrayBonos.get(i).getbUsuario() + "_ReservasBonosRes.txt porque no existia");
		    		System.out.println("--------------------------------------------------------------------------");
		    	}
		    }
	    }**/
	    
	}
}
