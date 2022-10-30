package pw.p2.display;

import pw.p2.business.*;
import pw.p2.data.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;
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
        Integer maxkarts, participantes, id, duracion;
        String nombre, apellidos, correo, usuario, pista;
		Boolean estado, tipo;
		Dificultad dificultad;
		Estado estados;
		LocalDate fecha, nacimiento;
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
            	System.out.println("Introduzca el nombre de usuario");
					nombre = scan_.nextLine();
				System.out.println("Introduzca los apellidos de usuario");        			
					apellidos = scan_.nextLine();
				System.out.println("Introduzca la fecha de nacimiento de usuario (yyyy-mm-dd)");
					nacimiento = LocalDate.parse(scan_.nextLine());
				System.out.println("Introduzca el correo de usuario");
					correo = scan_.nextLine();
				GestorUsuarios_.registrarUsuario(nombre, apellidos, nacimiento, correo);	            
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
		        	                	System.out.println("Introduzca el nombre de pista");
		        	        				nombre = scan_.nextLine();
		        	        				System.out.println("");
		        	        			System.out.println("Introduzca el estado de pista");
		        	        				estado = Boolean.parseBoolean(scan_.nextLine());
		        	        				System.out.println("");
		        	        			System.out.println("Introduzca la dificultad de pista");
		        	        				dificultad = Dificultad.valueOf(scan_.nextLine());
		        	        				System.out.println("");
		        	        			System.out.println("Introduzca el numero maximo de karts de pista");
		        	        				maxkarts = Integer.parseInt(scan_.nextLine());
		        	        				System.out.println("");
		        	                    GestorPistas_.crearPista(nombre, estado, dificultad, maxkarts);
		        	                    break;
		        	                    
		        	                case 2:
		        	                	GestorPistas_.listaPistasMantenimiento();
		        	                    break;
		        	                    
		        	                case 3:
			        	                System.out.println("Introduzca el numero de participantes");
			    							participantes= Integer.parseInt(scan_.nextLine());
			    							System.out.println("");
			    						System.out.println("Introduzca la dificultad de pista");
			    							dificultad = Dificultad.valueOf(scan_.nextLine());
			    							System.out.println("");
			    							
			    	                	ArrayList<Pista> arraypistaslibres = GestorPistas_.pistasLibres(participantes, dificultad);
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
		        	                	System.out.println("Introduzca el nombre de usuario");
		        	        				nombre = scan_.nextLine();
		        	        			System.out.println("Introduzca los apellidos de usuario");        			
		        	        				apellidos = scan_.nextLine();
		        	        			System.out.println("Introduzca la fecha de nacimiento de usuario (yyyy-mm-dd)");
		        	        				nacimiento = LocalDate.parse(scan_.nextLine());
		        	        			System.out.println("Introduzca el correo de usuario");
		        	        				correo = scan_.nextLine();
		        	                	GestorUsuarios_.registrarUsuario(nombre, apellidos, nacimiento, correo);
		        	                	//GestorUsuarios_.requestUsuariosByCorreo(correo);//
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
		        	                	System.out.println("Introduzca su correo de usuario");
		        	        				usuario = scan_.nextLine();
		        	        			System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
		        	        				fecha = LocalDate.parse(scan_.nextLine());
		        	        			System.out.println("Introduzca la pista de la reserva");
		        	        				pista = scan_.nextLine();
		        	        			System.out.println("Introduzca la duracion de la reserva");
		        	        				duracion = Integer.parseInt(scan_.nextLine());
		        	        			System.out.println("");
		        	                	GestorReservas_.modificarReserva(usuario, fecha, pista, duracion, scan_);
		        	                    break;
		        	                    
		        	                case 4:
		        	                	System.out.println("Introduzca su correo de usuario");
		        	        				usuario = scan_.nextLine();
		        	        			System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
		        	        				fecha = LocalDate.parse(scan_.nextLine());
		        	        			System.out.println("Introduzca la pista de la reserva");
		        	        				pista = scan_.nextLine();
		        	        			System.out.println("Introduzca la duracion de la reserva");
		        	        				duracion = Integer.parseInt(scan_.nextLine());
		        	        			System.out.println("");
		        	                	GestorReservas_.eliminarReserva(usuario, fecha, pista, duracion);
		        	                	break;
		        	                	
		        	                case 5:
		        	                	System.out.println("Introduzca su correo de usuario");
		        	            			usuario = scan_.nextLine();
		        	            			System.out.println("");
		        	                	GestorReservas_.consultarReservasFuturasIndividuales(usuario);
		        	                    break;
		        	                    
		        	                case 6:
		        	                	System.out.println("Introduzca su correo de usuario");
		        	            			usuario = scan_.nextLine();
		        	            			System.out.println("");
		        	                	GestorReservas_.consultarReservasFuturasBono(usuario);
		        	                    break;
		        	                    
		        	                case 7:
		        	                	System.out.println("Introduzca su correo de usuario");
		        	        				usuario = scan_.nextLine();
		        	        			System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
		        	        				fecha = LocalDate.parse(scan_.nextLine());
		        	        			System.out.println("Introduzca la pista de la reserva");
		        	        				pista = scan_.nextLine();
		        	        			System.out.println("");
		        	                	GestorReservas_.consultarReservaEspecifica(GestorPistas_, GestorReservas_, usuario, fecha, pista, scan_);
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
		        	                	System.out.println("Introduzca el identificador de kart");
		        	        				id = Integer.parseInt(scan_.nextLine());							
		        	        			System.out.println("Introduzca el tipo de kart");
		        	        				tipo = Boolean.parseBoolean(scan_.nextLine());
		        	        			System.out.println("Introduzca el estado de kart");
		        	        				estados = Estado.valueOf(scan_.nextLine());
		        	                	GestorPistas_.crearKart(id, tipo, estados);
		        	                    break;
		        	                    
		        	                case 2:
		        	                	GestorPistas_.listarKartsDisponibles();
		        	                	break;
		        	                	
		        	                case 3:
		        	                	System.out.println("");
		        	                	System.out.println("Introduzca el identificador de kart");
		        	        				id = Integer.parseInt(scan_.nextLine());							
		        	        			System.out.println("Introduzca el nombre de pista");
		        	        				nombre = scan_.nextLine();
		        	                    GestorPistas_.asociarKartPista(id, nombre);
		        	                	break;
                	            }
                	            break;
        	            }
        	        }
            	}
            }else {
            	System.out.println("Opcion no reconocida");
				System.out.println("");
            }
        }
        	Escritor.escritor(GestorPistas_, GestorUsuarios_, GestorReservas_);
        //FicheroPropiedades.propiedades(GestorPistas_, GestorUsuarios_, GestorReservas_);
        scan_.close();
    }

}
