package pw.p1.classes;
import java.util.ArrayList;
/**
 * Un programa main para implementar 

 * las funcionalidades de las clases
 * */

import java.util.Scanner;
import pw.p1.other.*;
import java.io.*;

/**
 * 
 * Un programa main para implementar las funcionalidades de las clases
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class MainProgram{

    public static void main(String[] args) throws IOException{
        Scanner scan_ = new Scanner(System.in);
        boolean exit_ = false;
        int option_;
        GestorPistas GestorPistas_ = new GestorPistas();
        GestorUsuarios GestorUsuarios_ = new GestorUsuarios();
        GestorReservas GestorReservas_ = new GestorReservas();        
        	Lector.lector(GestorPistas_, GestorUsuarios_, GestorReservas_);
        
        while(!exit_){
        	System.out.println("PROGRAMA MAIN");
        	System.out.println("=====================================");
            System.out.println("0. Registrarse como usuario");
            System.out.println("1. Loguearse como usuario");
            	System.out.println("");
            System.out.println("Elija una opcion escribiendo su numero");
            option_= Integer.parseInt(scan_.nextLine());
            System.out.println("");
            if(option_ == 0) {  	
	            GestorUsuarios_.registrarUsuario(scan_);	            
            }else if(option_ == 1) {           
        		if(!MainMiscelanea.login(GestorUsuarios_, scan_)) {
        			System.out.println("	Debe registrarse antes de iniciar sesion");
        			System.out.println("");
        		}else {
                	System.out.println("");
                	System.out.println("¡¡BIENVENIDO!!");
                	System.out.println("");
                	exit_ = false;
        	        while(!exit_){      	                  	        	
        	        	MainMiscelanea.menuPrincipal();
        	            System.out.println("Elija una opcion escribiendo su numero");
        	            option_= Integer.parseInt(scan_.nextLine());
        	            System.out.println("");
        	            
        	            switch(option_) {
        	            	case 0:
        	                    exit_=true;
        	                    break;
        	                    
        	            	case 1: 
        	            		MainMiscelanea.menuPistas();
                	            System.out.println("Elija una opcion escribiendo su numero");
                	            option_= Integer.parseInt(scan_.nextLine());
                	            System.out.println("");
        	            		
	        	            	switch(option_){
		 
		        	                case 1:
		        	                    GestorPistas_.crearPista(scan_);
		        	                    break;
		        	                    
		        	                case 2:
		        	                	GestorPistas_.listaPistasMantenimiento();
		        	                    break;
		        	                    
		        	                case 3:
			        	                System.out.println("Introduzca el numero de participantes");
			    						int participantes= Integer.parseInt(scan_.nextLine());
			    						System.out.println("");
			    						System.out.println("Introduzca la dificultad de pista");
			    						Dificultad dificultad = Dificultad.valueOf(scan_.nextLine());
			    						System.out.println("");
			    							
			    	                	ArrayList<Pista> arraypistaslibres = GestorPistas_.pistasLibres(scan_, participantes, dificultad);
			    	                	for (int i = 0;i< arraypistaslibres.size() ; i++) {
			    	            				System.out.println(arraypistaslibres.get(i).toString());
			    	            		}
			    	                	break;
			    	               	}
			    	               	break;
			    	                
		        	        case 2:
		        	        	MainMiscelanea.menuUsuarios();
                	            System.out.println("Elija una opcion escribiendo su numero");
                	            option_= Integer.parseInt(scan_.nextLine());
                	            System.out.println("");
                	            switch(option_){	
		        	                case 1:
		        	                	GestorUsuarios_.registrarUsuario(scan_);
		        	                    break;
		        	                    
		        	                case 2:
		        	                	GestorUsuarios_.ModificarUsuario(scan_);
		        	                	break;
		        	                	
		        	                case 3:
		        	                	GestorUsuarios_.listarUsuarios();
		        	                    
		        	                    break;
                	            }
	        	                break;
		        	        case 3: 
		        	        	MainMiscelanea.menuReservas();
                	            System.out.println("Elija una opcion escribiendo su numero");
                	            option_= Integer.parseInt(scan_.nextLine());
                	            System.out.println("");
                	            switch(option_){	
                	            
		        	                case 1: 
		        	                	MainMiscelanea.reservaIndividual(GestorReservas_, GestorPistas_, GestorUsuarios_, scan_);
		        	                    break;
		        	                    
		        	                case 2:
		        	                	MainMiscelanea.reservaBono(GestorReservas_, GestorPistas_, GestorUsuarios_, scan_);
		        	                    break;
		        	                    
		        	                case 3:
		        	                	GestorReservas_.modificarReserva(scan_);
		        	                    break;
		        	                    
		        	                case 4:
		        	                	GestorReservas_.eliminarReserva(scan_);
		        	                	break;
		        	                	
		        	                case 5:
		        	                	GestorReservas_.consultarReservasFuturasIndividuales(scan_);
		        	                    break;
		        	                    
		        	                case 6:
		        	                	GestorReservas_.consultarReservasFuturasBono(scan_);
		        	                    break;
		        	                    
		        	                case 7:
		        	                	GestorReservas_.consultarReservaEspecifica(GestorPistas_, GestorReservas_, scan_);
		        	                    break;
		        	                    
        	            }
        	            break;
		        	        case 4:
		        	        	MainMiscelanea.menuKarts();
                	            System.out.println("Elija una opcion escribiendo su numero");
                	            option_= Integer.parseInt(scan_.nextLine());
                	            System.out.println("");
                	            switch(option_){	
                	           
		        	                case 1:
		        	                	GestorPistas_.crearKart(scan_);
		        	                    break;
		        	                    
		        	                case 2:
		        	                	MainMiscelanea.listarKartsDisponibles(GestorPistas_);
		        	                	break;
		        	                	
		        	                case 3:
		        	                	System.out.println("");
		        	                	System.out.println("Introduzca el identificador de kart");
		        	        				Integer id = Integer.parseInt(scan_.nextLine());							
		        	        			System.out.println("Introduzca el nombre de pista");
		        	        				String nombre = scan_.nextLine();
		        	                    GestorPistas_.asociarKartPista(id, nombre);
		        	                	break;
                	            }
                	            break;
        	            }
        	        }
            	}
            }  else {
            	System.out.println("Opcion no reconocida");
				System.out.println("");
            }
        }
        //MainMiscelanea.restaurar(GestorPistas_);
        Escritor.escritor(GestorPistas_, GestorUsuarios_, GestorReservas_);
        FicheroPropiedades.propiedades(GestorPistas_, GestorUsuarios_, GestorReservas_);
        scan_.close();
    }

}
