package pw.p1.classes;

/**
 * Un programa main para implementar 
 * las funcionalidades de las clases
 * */
import java.util.Scanner;
import pw.p1.other.*;
import java.time.LocalDate;
import java.io.*;

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
        	        	MainMiscelanea.menu();
        	            System.out.println("Elija una opcion escribiendo su numero");
        	            option_= Integer.parseInt(scan_.nextLine());
        	            
        	            switch(option_){
        	                case 0:
        	                    exit_=true;
        	                    break;
        	                case 1:
        	                    GestorPistas_.crearPista(scan_);
        	                    break;
        	                case 2:
        	                    //GestorPistas_.crearKart();
        	                    break;
        	                case 3:
        	                    GestorPistas_.asociarKartPista();
        	                    break;
        	                case 4:
        	                    GestorPistas_.listaPistasMantenimiento();
        	                    break;
        	                case 5:
        	                    //GestorPistas_.pistasLibres();
        	                    break;
        	                case 6:
        	                    //GestorUsuarios_.registrarUsuario(scan_);
        	                    break;
        	                case 7:       	                	
        	                    GestorUsuarios_.ModificarUsuario(scan_);
        	                    break;
        	                case 8:
        	                    GestorUsuarios_.listarUsuarios();
        	                    break;
        	                case 9:
        	                    //GestorReservas_
        	                    break;
        	                case 10:
        	
        	                    break;
        	                case 11:
        	
        	                    break;
        	                case 12:
        	
        	                    break;
        	                case 13:
        	
        	                    break;
        	                case 14:
        	
        	                    break;
        	            }
        	        }
            	}
            }         
        }
        Escritor.escritor(GestorPistas_, GestorUsuarios_, GestorReservas_); //Escribo los arrays de los lectores en los ficheros
        scan_.close();
    }

}
