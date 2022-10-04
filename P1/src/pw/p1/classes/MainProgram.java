package pw.p1.classes;

/**
 * Un programa main para implementar 
 * las funcionalidades de las clases
 * */
import java.util.Scanner;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainProgram{

    public static void main(String[] args) throws IOException{
        Scanner scan_ = new Scanner(System.in);
        BufferedReader scanline_ = new BufferedReader(new InputStreamReader(System.in));
        boolean exit_ = false;
        int option_;
        GestorPistas GestorPistas_ = new GestorPistas();
        GestorUsuarios GestorUsuarios_ = new GestorUsuarios();
        GestorReservas GestorReservas_ = new GestorReservas();
        
        while(!exit_){
            System.out.println("0. Registrarse como usuario");
            System.out.println("1. Loguearse como usuario");
            
            System.out.println("Elija una opción escribiendo su numero");
            option_=scan_.nextInt();
            
            if(option_ == 0) {
            	System.out.println("Introduzca su nombre");
        			String rnombre = scan_.next();
        		System.out.println("Introduzca sus apellidos");        			
        				String rapellidos = scanline_.readLine();
        		System.out.println("Introduzca fecha de nacimiento (yyyy-mm-dd)");
    				String rsnacimiento = scan_.next();
    				LocalDate rnacimiento = LocalDate.parse(rsnacimiento);
    			System.out.println("Introduzca su correo");
        			String rcorreo = scan_.next();
	            if(GestorUsuarios_.registrarUsuario(rnombre, rapellidos, rnacimiento, rcorreo)) {
	            	System.out.println("Se ha registrado correctamente");
	            	System.out.println("");
	            }else {
	            	System.out.println("Error al registrarse");
	            	System.out.println("");
	            }
            }else if(option_ == 1) {           
            	System.out.println("Introduzca su correo");
        		String lcorreo = scan_.next();
        		if(lcorreo == "") {
                	
                }else {
                	exit_ = false;
        	        while(!exit_){
        	            System.out.println("1. Crear Pista");
        	            System.out.println("2. Crear Kart");
        	            System.out.println("3. Asociar Karts y Pistas");
        	            System.out.println("4. Listar Pistas en mantenimiento");
        	            System.out.println("5. Recoger Array de pistas libres");
        	            System.out.println("--------------------------------------------");
        	            System.out.println("6. Registrar Usuario");
        	            System.out.println("7. Modificar Usuario");
        	            System.out.println("8. Listar Usuarios");
        	            System.out.println("--------------------------------------------");
        	            System.out.println("9. Hacer reserva individual");
        	            System.out.println("10. Hacer reserva en bono");
        	            System.out.println("11. PLACEHOLDER");
        	            System.out.println("12. PLACEHOLDER");
        	            System.out.println("13. PLACEHOLDER");
        	            System.out.println("14. PLACEHOLDER");
        	            System.out.println("--------------------------------------------");
        	            System.out.println("0. Salir");
        	
        	
        	            System.out.println("Elija una opción escribiendo su numero");
        	            option_=scan_.nextInt();
        	
        	            switch(option_){
        	                case 0:
        	                    exit_=true;
        	                    break;
        	                case 1:
        	                    //GestorPistas_.crearPista();
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
        	                    //GestorUsuarios_.registrarUsuario();
        	                    break;
        	                case 7:
        	                    //GestorUsuarios_.ModificarUsuario();
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
        
        scan_.close();
    }

}
