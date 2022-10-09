package pw.p1.other;

import java.io.*;
import pw.p1.classes.*;

/**
 * Clase que gestiona la escritura en los ficheros
 * @author 
 * */

public class Escritor {
	
	/**
	 * Funcion que escribe el contenido de los ArrayList a los ficheros .txt
	 * @param GestorPistas
	 * @param GestorUsuarios
	 * @param GestorReservas
	 * @return void
	 */
	
	public static void escritor(GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, GestorReservas GestorReservas_) throws IOException{
	    BufferedWriter writerU_ = new BufferedWriter(new FileWriter(new File("Usuarios.txt")));
	    BufferedWriter writerP_ = new BufferedWriter(new FileWriter(new File("Pistas.txt")));
	    BufferedWriter writerK_ = new BufferedWriter(new FileWriter(new File("Karts.txt")));
	    BufferedWriter writerRI_ = new BufferedWriter(new FileWriter(new File("ReservasIndividuales.txt")));
	    BufferedWriter writerRB_ = new BufferedWriter(new FileWriter(new File("ReservasBonos.txt")));
	    
	    for (int i = 0; i < GestorUsuarios_.arrayUsuarios.size(); i++) {
	    	writerU_.write(GestorUsuarios_.arrayUsuarios.get(i).toString());
		}	
		writerU_.close();
		
		for (int i = 0; i < GestorPistas_.arrayPistas.size(); i++) {
	    	writerP_.write(GestorPistas_.arrayPistas.get(i).toString());
		}	
		writerP_.close();
		
	    for (int i = 0; i < GestorPistas_.arrayKarts.size(); i++) {
	    	writerK_.write(GestorPistas_.arrayKarts.get(i).toString());
		}	
		writerK_.close();
		
		for (int i = 0; i < GestorPistas_.arrayPistas.size(); i++) {
			BufferedWriter writerPKarts_ = new BufferedWriter(new FileWriter(new File(GestorPistas_.arrayPistas.get(i).getNombre() +"_Karts.txt")));
			for (int j = 0; j < GestorPistas_.arrayPistas.get(i).getLkart().size(); j++) {
				writerPKarts_.write(GestorPistas_.arrayPistas.get(i).getLkart().get(j).toString());
			}
			writerPKarts_.close();
		}	
		
		for (int i = 0; i < GestorReservas_.arrayReservaIndividual.size(); i++) {
			writerRI_.write(GestorReservas_.arrayReservaIndividual.get(i).toString());
		}	
		writerRI_.close();
		
		for (int i = 0; i < GestorReservas_.arrayBonos.size(); i++) {
			writerRB_.write(GestorReservas_.arrayBonos.get(i).toString());
		}	
		writerRB_.close();
		
		for (int i = 0; i < GestorReservas_.arrayBonos.size(); i++) {
			BufferedWriter writerRBRes_ = new BufferedWriter(new FileWriter(new File(GestorReservas_.arrayBonos.get(i).getbUsuario() + "_ReservasBonosRes.txt")));
			for (int j = 0; j < GestorReservas_.arrayBonos.get(i).getArrayReservas().size(); j++) {
				writerRBRes_.write(GestorReservas_.arrayBonos.get(i).getArrayReservas().get(j).toString());
			}
			writerRBRes_.close();
		}	
		
	}
}
