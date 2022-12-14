package pw.p2.business;

import pw.p2.data.*;
import pw.p2.data.DAO.DAOPista;
import pw.p2.data.DAO.DAOReserva;
import pw.p2.data.DAO.DAOUsuario;
import pw.p2.data.DAO.DAOBono;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Clase que gestiona las reservas
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 */

public class GestorReservas {
	
	/**
	 * Constructor por defecto
	 **/	
	public GestorReservas(){}
	
	/**
	 * Realiza las reservas individuales infantiles
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve true si la reserva se ha realizado, false si no se ha realizado
	 **/
	
	public boolean ReservaIndividualInfantil (Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);
		
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			System.out.println("Introduzca el numero de participantes (niños)");
			int participantes= Integer.parseInt(scan_.nextLine());
			System.out.println("");		
			ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, participantes, Dificultad.INFANTIL);
			if(lpista.size() > 0) {
				System.out.println("Pistas libres:");
				for (int j = 0; j < lpista.size(); j++) {
					System.out.println(lpista.get(j).toString());
				}
				System.out.println("");
				System.out.println("Escoja una pista mediante su nombre");
				String pista = scan_.nextLine();
				System.out.println("");
				boolean valido = false;
				while(!valido) {
					for (int j = 0; j < lpista.size(); j++) {
						if(!lpista.get(j).getNombre().equals(pista)) {
							System.out.println("Escoja una pista mediante su nombre");
							pista = scan_.nextLine();
							System.out.println("");
						}else {
							valido = true;
						}
					}
				}
				float precio = 0;
				int descuento = 0;
				System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
				int duracion = Integer.parseInt(scan_.nextLine());
				System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
				LocalDate fecha = LocalDate.parse(scan_.nextLine());
				switch(duracion) {
				case 60:
					precio = 20;
					break;
				case 90:
					precio = 30;
					break;
				case 120:
					precio = 40;
					break;
				}	
				if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
					descuento = 10;
				}
				
				RIndividualCreador individualCreador = new RIndividualCreador();
				DTORInfantil newReserva = individualCreador.creaRInf(usuario.getCorreo(), fecha, duracion, pista, precio, descuento, participantes, Dificultad.INFANTIL);
				DAOReserva reservaTabla = new DAOReserva();
				if(reservaTabla.escribirReservaInfantilInsert(newReserva) == 0) {
					System.out.println("Error. Reserva no creada");
					System.out.println("");
					return false;
				}
				System.out.println("Reserva creada con exito");
				System.out.println("-------------------------------------");
				System.out.println("");
			} else {
				System.out.println("Error. No existen pistas libres");
				System.out.println("");
				return false;
			}
		} else {
			System.out.println("Error. Las reservas se realizan solo por adultos");
			System.out.println("");
			return false;
		}
		return true;
	}
	
	/**
	 * Realiza las reservas individuales adultas
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve true si la reserva se ha realizado, false si no se ha realizado
	 **/
	
	public boolean ReservaIndividualAdulto (Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);		
		
		if (usuario.getCorreo().equals(correo)) {
			if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
				System.out.println("Introduzca el numero de participantes (adultos)");
				int participantes= Integer.parseInt(scan_.nextLine());
				ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, participantes, Dificultad.ADULTO);
				System.out.println("");	
				if(lpista.size() > 0) {
					System.out.println("Pistas libres:");
					for (int j = 0; j < lpista.size(); j++) {
						System.out.println(lpista.get(j).toString());
					}
					System.out.println("");
					System.out.println("Escoja una pista mediante su nombre");
					String pista = scan_.nextLine();
					System.out.println("");
					boolean valido = false;
					while(!valido) {
						for (int j = 0; j < lpista.size(); j++) {
							if(!lpista.get(j).getNombre().equals(pista)) {
								System.out.println("Escoja una pista mediante su nombre");
								pista = scan_.nextLine();
								System.out.println("");
							}else {
								valido = true;
							}
						}
					}
					float precio = 0;
					int descuento = 0;
					System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
					int duracion = Integer.parseInt(scan_.nextLine());
					System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
					LocalDate fecha = LocalDate.parse(scan_.nextLine());
					switch(duracion) {
					case 60:
						precio = 20;
						break;
					case 90:
						precio = 30;
						break;
					case 120:
						precio = 40;
						break;
					}	
					if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
						descuento = 10;
					}
					
					RIndividualCreador individualCreador = new RIndividualCreador();
					DTORAdulto newReserva = individualCreador.creaRAdu(usuario.getCorreo(), fecha, duracion, pista, precio, descuento, participantes, Dificultad.ADULTO);
					DAOReserva reservaTabla = new DAOReserva();
					if(reservaTabla.escribirReservaAdultoInsert(newReserva) == 0) {
						System.out.println("Error. Reserva no creada");
						System.out.println("");
						return false;
					}
					System.out.println("Reserva creada con exito");
					System.out.println("-------------------------------------");
					System.out.println("");
				} else {
					System.out.println("Error. No existen pistas libres");
					System.out.println("");
					return false;
				}
			} else {
				System.out.println("Error. Las reservas se realizan solo por adultos");
				System.out.println("");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Realiza las reservas individuales familiares
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve true si la reserva se ha realizado, false si no se ha realizado
	 **/
	
	public boolean ReservaIndividualFamiliar (Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);
		
		if (usuario.getCorreo().equals(correo)) {
			if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
				System.out.println("Introduzca el numero de participantes (niños)");
					int participantesInfantiles= Integer.parseInt(scan_.nextLine());
					System.out.println("");
					System.out.println("Introduzca el numero de participantes (adultos)");
					int participantesAdultos= Integer.parseInt(scan_.nextLine());
					while(participantesAdultos <= 0) {
						System.out.println("Introduzca el numero de participantes (adultos)");
						participantesAdultos= Integer.parseInt(scan_.nextLine());
					}
					System.out.println("");
					ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, participantesInfantiles + participantesAdultos, Dificultad.FAMILIAR);
					if(lpista.size() > 0) {
					System.out.println("Pistas libres:");
					for (int j = 0; j < lpista.size(); j++) {
							System.out.println(lpista.get(j).toString());
					}
					System.out.println("");
					System.out.println("Escoja una pista mediante su nombre");
					String pista = scan_.nextLine();
					System.out.println("");
					boolean valido = false;
					while(!valido) {
						for (int j = 0; j < lpista.size(); j++) {
							if(!lpista.get(j).getNombre().equals(pista)) {
								System.out.println("Escoja una pista mediante su nombre");
								pista = scan_.nextLine();
								System.out.println("");
							}else {
								valido = true;
							}
						}
					}
					float precio = 0;
					int descuento = 0;
					System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
					int duracion = Integer.parseInt(scan_.nextLine());
					System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
					LocalDate fecha = LocalDate.parse(scan_.nextLine());
					switch(duracion) {
					case 60:
						precio = 20;
						break;
					case 90:
						precio = 30;
						break;
					case 120:
						precio = 40;
						break;
					}	
					if(usuario.getInscripcion().isBefore(LocalDate.now().minusYears(2))) {
						descuento = 10;
					}
					
					RIndividualCreador individualCreador = new RIndividualCreador();
					DTORFamiliar newReserva = individualCreador.creaRFam(usuario.getCorreo(), fecha, duracion, pista, precio, descuento, participantesInfantiles, participantesAdultos, Dificultad.FAMILIAR);
					DAOReserva reservaTabla = new DAOReserva();
					if(reservaTabla.escribirReservaFamiliarInsert(newReserva) == 0) {
						System.out.println("Error. Reserva no creada");
						System.out.println("");
						return false;
					}
					System.out.println("Reserva creada con exito");
					System.out.println("-------------------------------------");
					System.out.println("");
				} else {
					System.out.println("Error. No existen pistas libres");
					System.out.println("");
					return false;
				}
			} else {
				System.out.println("Error. Las reservas se realizan solo por adultos");
				System.out.println("");
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Realiza las reservas de bono infantiles
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve true si la reserva se ha realizado, false si no se ha realizado
	 **/
	
	public boolean ReservaBonoInfantil (Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");

		DAOBono bonoTabla = new DAOBono();
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);		
		ArrayList<DTOBono> bonos = bonoTabla.solicitarBonos();
		
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			if(bonos.size() == 0) {
				System.out.println("No existe ningun bono. Creando nuevo bono...");
				DTOBono newBono = new DTOBono(0, usuario.getCorreo(), Dificultad.INFANTIL);
				bonos.add(newBono);
				if(bonoTabla.escribirBonoInsert(newBono) == 0) {
					System.out.println("Error. Bono no creado");
					System.out.println("");
					return false;
				}
				System.out.println("");
			}
			boolean encontrado = false;
			for (int k = 0; k < bonos.size(); k++) {						
				for (int e = 0; e < bonos.size()  && !encontrado; e++) {
					if(usuario.getCorreo().equals(bonos.get(e).getbUsuario()) && bonos.get(e).getTipo() == Dificultad.INFANTIL) {
						encontrado = true;
					}
				}
				if(!encontrado) {
					System.out.println("No hay un bono asociado a este usuario. Creando nuevo bono...");
					DTOBono newBono = new DTOBono(0, usuario.getCorreo(), Dificultad.INFANTIL);
					bonos.add(newBono);
					if(bonoTabla.escribirBonoInsert(newBono) == 0) {
						System.out.println("Error. Bono no creado");
						System.out.println("");
						return false;
					}
					System.out.println("");
				}
				if(bonos.get(k).getbUsuario().equals(usuario.getCorreo()) && bonos.get(k).getTipo() == Dificultad.INFANTIL) {
					if (bonos.get(k).getSesion() < 5) {
						System.out.println("Introduzca el numero de participantes (niños)");
						int participantes= Integer.parseInt(scan_.nextLine());
						System.out.println("");
						ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, participantes, Dificultad.INFANTIL);			
						if(lpista.size() > 0) {
							System.out.println("Pistas libres:");
							for (int j = 0; j < lpista.size(); j++) {
								System.out.println(lpista.get(j).toString());
							}
							System.out.println("");
							System.out.println("Escoja una pista mediante su nombre");
							String pista = scan_.nextLine();
							System.out.println("");
							boolean valido = false;
							while(!valido) {
								for (int j = 0; j < lpista.size(); j++) {
									if(!lpista.get(j).getNombre().equals(pista)) {
										System.out.println("Escoja una pista mediante su nombre");
										pista = scan_.nextLine();
										System.out.println("");
									}else {
										valido = true;
									}
								}
							}
							float precio = 0;
							int descuento = 5;
							System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
							int duracion = Integer.parseInt(scan_.nextLine());
							System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
							LocalDate fecha = LocalDate.parse(scan_.nextLine());
							switch(duracion) {
							case 60:
								precio = 20;
								break;
							case 90:
								precio = 30;
								break;
							case 120:
								precio = 40;
								break;
							}
							
							RBonoCreador BonoCreador = new RBonoCreador();
							DTORInfantil newReserva = BonoCreador.creaRInf(usuario.getCorreo(), fecha, duracion, pista, precio, descuento, participantes, Dificultad.INFANTIL);
							DTOBono bono = bonoTabla.solicitarBono(usuario.getCorreo(), Dificultad.INFANTIL);
							bono.setSesion(bono.getSesion() + 1);
							if(bonoTabla.escribirBonoUpdate(bono) == 0) {
								System.out.println("Error. Reserva no creada");
								System.out.println("");
								return false;
							}
							if(bonoTabla.escribirReservaInfantilInsert(newReserva, bono.getId()) == 0) {
								System.out.println("Error. Reserva no creada");
								System.out.println("");
								return false;
							}
							System.out.println("Reserva creada con exito");
							System.out.println("-------------------------------------");
							System.out.println("");
						} else {
							System.out.println("Error. No existen pistas libres");
							System.out.println("");
							return false;
						}						
					} else {
						System.out.println("Error. Solo puede haber hasta 5 reservas en un bono");
						System.out.println("");
						return false;
					}
				}
			}
		} else {
			System.out.println("Error. Las reservas se realizan solo por adultos");
			System.out.println("");
			return false;
		}
		return true;
	}
	
	/**
	 * Realiza las reservas de bono adultas
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve true si la reserva se ha realizado, false si no se ha realizado
	 **/
	
	public boolean ReservaBonoAdulto (Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");

		DAOBono bonoTabla = new DAOBono();
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);		
		ArrayList<DTOBono> bonos = bonoTabla.solicitarBonos();
		
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			if(bonos.size() == 0) {
				System.out.println("No existe ningun bono. Creando nuevo bono...");
				DTOBono newBono = new DTOBono(0, usuario.getCorreo(), Dificultad.ADULTO);
				bonos.add(newBono);
				if(bonoTabla.escribirBonoInsert(newBono) == 0) {
					System.out.println("Error. Bono no creado");
					System.out.println("");
					return false;
				}
				System.out.println("");
			}
			boolean encontrado = false;
			for (int k = 0; k < bonos.size(); k++) {						
				for (int e = 0; e < bonos.size()  && !encontrado; e++) {
					if(usuario.getCorreo().equals(bonos.get(e).getbUsuario()) && bonos.get(e).getTipo() == Dificultad.ADULTO) {
						encontrado = true;
					}
				}
				if(!encontrado) {
					System.out.println("No hay un bono asociado a este usuario. Creando nuevo bono...");
					DTOBono newBono = new DTOBono(0, usuario.getCorreo(), Dificultad.ADULTO);
					bonos.add(newBono);
					if(bonoTabla.escribirBonoInsert(newBono) == 0) {
						System.out.println("Error. Bono no creado");
						System.out.println("");
						return false;
					}
					System.out.println("");
				}
				if(bonos.get(k).getbUsuario().equals(usuario.getCorreo()) && bonos.get(k).getTipo() == Dificultad.ADULTO) {
					if (bonos.get(k).getSesion() < 5) {
						System.out.println("Introduzca el numero de participantes (adultos)");
						int participantes= Integer.parseInt(scan_.nextLine());
						System.out.println("");
						ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, participantes, Dificultad.ADULTO);			
						if(lpista.size() > 0) {
							System.out.println("Pistas libres:");
							for (int j = 0; j < lpista.size(); j++) {
								System.out.println(lpista.get(j).toString());
							}
							System.out.println("");
							System.out.println("Escoja una pista mediante su nombre");
							String pista = scan_.nextLine();
							System.out.println("");
							boolean valido = false;
							while(!valido) {
								for (int j = 0; j < lpista.size(); j++) {
									if(!lpista.get(j).getNombre().equals(pista)) {
										System.out.println("Escoja una pista mediante su nombre");
										pista = scan_.nextLine();
										System.out.println("");
									}else {
										valido = true;
									}
								}
							}
							float precio = 0;
							int descuento = 5;
							System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
							int duracion = Integer.parseInt(scan_.nextLine());
							System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
							LocalDate fecha = LocalDate.parse(scan_.nextLine());
							switch(duracion) {
							case 60:
								precio = 20;
								break;
							case 90:
								precio = 30;
								break;
							case 120:
								precio = 40;
								break;
							}
							
							RBonoCreador BonoCreador = new RBonoCreador();
							DTORAdulto newReserva = BonoCreador.creaRAdu(usuario.getCorreo(), fecha, duracion, pista, precio, descuento, participantes, Dificultad.ADULTO);
							DTOBono bono = bonoTabla.solicitarBono(usuario.getCorreo(), Dificultad.ADULTO);
							bono.setSesion(bono.getSesion() + 1);
							if(bonoTabla.escribirBonoUpdate(bono) == 0) {
								System.out.println("Error. Reserva no creada");
								System.out.println("");
								return false;
							}
							if(bonoTabla.escribirReservaAdultoInsert(newReserva, bono.getId()) == 0) {
								System.out.println("Error. Reserva no creada");
								System.out.println("");
								return false;
							}
							System.out.println("Reserva creada con exito");
							System.out.println("-------------------------------------");
							System.out.println("");
						} else {
							System.out.println("Error. No existen pistas libres");
							System.out.println("");
							return false;
						}						
					} else {
						System.out.println("Error. Solo puede haber hasta 5 reservas en un bono");
						System.out.println("");
						return false;
					}
				}
			}
		} else {
			System.out.println("Error. Las reservas se realizan solo por adultos");
			System.out.println("");
			return false;
		}
		return true;
	}
	
	/**
	 * Realiza las reservas de bono familiares
	 * @param scan_ Scanner para leer por teclado
	 * @return Devuelve true si la reserva se ha realizado, false si no se ha realizado
	 **/
	
	public boolean ReservaBonoFamiliar (Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");

		DAOBono bonoTabla = new DAOBono();
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);		
		ArrayList<DTOBono> bonos = bonoTabla.solicitarBonos();
		
		if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
			if(bonos.size() == 0) {
				System.out.println("No existe ningun bono. Creando nuevo bono...");
				DTOBono newBono = new DTOBono(0, usuario.getCorreo(), Dificultad.FAMILIAR);
				bonos.add(newBono);
				if(bonoTabla.escribirBonoInsert(newBono) == 0) {
					System.out.println("Error. Bono no creado");
					System.out.println("");
					return false;
				}
				System.out.println("");
			}
			boolean encontrado = false;
			for (int k = 0; k < bonos.size(); k++) {						
				for (int e = 0; e < bonos.size()  && !encontrado; e++) {
					if(usuario.getCorreo().equals(bonos.get(e).getbUsuario()) && bonos.get(e).getTipo() == Dificultad.FAMILIAR) {
						encontrado = true;
					}
				}
				if(!encontrado) {
					System.out.println("No hay un bono asociado a este usuario. Creando nuevo bono...");
					DTOBono newBono = new DTOBono(0, usuario.getCorreo(), Dificultad.FAMILIAR);
					bonos.add(newBono);
					if(bonoTabla.escribirBonoInsert(newBono) == 0) {
						System.out.println("Error. Bono no creado");
						System.out.println("");
						return false;
					}
					System.out.println("");
				}
				if(bonos.get(k).getbUsuario().equals(usuario.getCorreo()) && bonos.get(k).getTipo() == Dificultad.FAMILIAR) {
					if (bonos.get(k).getSesion() < 5) {
						System.out.println("Introduzca el numero de participantes (niños)");
						int ninos = Integer.parseInt(scan_.nextLine());
						System.out.println("Introduzca el numero de participantes (adultos)");
						int adultos = Integer.parseInt(scan_.nextLine());
						while(adultos <= 0) {
							System.out.println("Introduzca el numero de participantes (adultos)");
							adultos= Integer.parseInt(scan_.nextLine());
						}
						int participantes=ninos+adultos;
						ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, participantes, Dificultad.FAMILIAR);			
						if(lpista.size() > 0) {
							System.out.println("Pistas libres:");
							for (int j = 0; j < lpista.size(); j++) {
								System.out.println(lpista.get(j).toString());
							}
							System.out.println("");
							System.out.println("Escoja una pista mediante su nombre");
							String pista = scan_.nextLine();
							System.out.println("");
							boolean valido = false;
							while(!valido) {
								for (int j = 0; j < lpista.size(); j++) {
									if(!lpista.get(j).getNombre().equals(pista)) {
										System.out.println("Escoja una pista mediante su nombre");
										pista = scan_.nextLine();
										System.out.println("");
									}else {
										valido = true;
									}
								}
							}
							float precio = 0;
							int descuento = 5;
							System.out.println("Introduzca la duración de la reserva (60/90/120 minutos)");
							int duracion = Integer.parseInt(scan_.nextLine());
							System.out.println("Introduzca la fecha de la reserva (yyyy-mm-dd)");
							LocalDate fecha = LocalDate.parse(scan_.nextLine());
							switch(duracion) {
							case 60:
								precio = 20;
								break;
							case 90:
								precio = 30;
								break;
							case 120:
								precio = 40;
								break;
							}
							
							RBonoCreador BonoCreador = new RBonoCreador();
							DTORFamiliar newReserva = BonoCreador.creaRFam(usuario.getCorreo(), fecha, duracion, pista, precio, descuento, adultos, ninos, Dificultad.FAMILIAR);
							DTOBono bono = bonoTabla.solicitarBono(usuario.getCorreo(), Dificultad.FAMILIAR);
							bono.setSesion(bono.getSesion() + 1);
							if(bonoTabla.escribirBonoUpdate(bono) == 0) {
								System.out.println("Error. Reserva no creada");
								System.out.println("");
								return false;
							}
							if(bonoTabla.escribirReservaFamiliarInsert(newReserva, bono.getId()) == 0) {
								System.out.println("Error. Reserva no creada");
								System.out.println("");
								return false;
							}
							System.out.println("Reserva creada con exito");
							System.out.println("-------------------------------------");
							System.out.println("");
						} else {
							System.out.println("Error. No existen pistas libres");
							System.out.println("");
							return false;
						}						
					} else {
						System.out.println("Error. Solo puede haber hasta 5 reservas en un bono");
						System.out.println("");
						return false;
					}
				}
			}
		} else {
			System.out.println("Error. Las reservas se realizan solo por adultos");
			System.out.println("");
			return false;
		}
		return true;
	}
	
	/**
	 * Muestra todas las reservas individuales futuras por pantalla
	 * @param usuario String con el nombre de usuario
	 * @return
	 **/
	
	public void consultarReservasFuturasIndividuales (String usuario){
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORInfantil> reservasInfantiles = reservaTabla.solicitarReservasInfantiles();
		ArrayList<DTORAdulto> reservasAdultos = reservaTabla.solicitarReservasAdultos();
		ArrayList<DTORFamiliar> reservasFamiliares = reservaTabla.solicitarReservasFamiliares();
		
		for(int i = 0; i < reservasInfantiles.size(); i++) {
				if(reservasInfantiles.get(i).getUsuario().equals(usuario) && reservasInfantiles.get(i).getFecha().isAfter(LocalDate.now())) {
					System.out.println(reservasInfantiles.get(i).toString());
				}
		}
		for(int i = 0; i < reservasAdultos.size(); i++) {
			if(reservasAdultos.get(i).getUsuario().equals(usuario) && reservasAdultos.get(i).getFecha().isAfter(LocalDate.now())) {
				System.out.println(reservasAdultos.get(i).toString());
			}
		}
		for(int i = 0; i < reservasFamiliares.size(); i++) {
			if(reservasFamiliares.get(i).getUsuario().equals(usuario) && reservasFamiliares.get(i).getFecha().isAfter(LocalDate.now())) {
				System.out.println(reservasFamiliares.get(i).toString());
			}
		}
	}
	
	/**
	 * Muestra todas las reservas de bonos futuras por pantalla
	 * @param usuario String con el nombre de usuario
	 * @param tipo Enum Dificultad con el tipo de reserva
	 * @return
	 **/

	public void consultarReservasFuturasBono (String usuario, Dificultad tipo){ 
		DAOBono reservaBono = new DAOBono();
		DTOBono bono = reservaBono.solicitarBono(usuario, tipo);
		
		if(tipo == Dificultad.INFANTIL) {
			ArrayList<DTORInfantil> reservasInfantiles = reservaBono.solicitarReservasInfantiles(bono);
			for(int i = 0; i < reservasInfantiles.size(); i++) {
				System.out.println(reservasInfantiles.get(i).toString());
			}
		}else if(tipo == Dificultad.ADULTO) {
			ArrayList<DTORAdulto> reservasAdultos = reservaBono.solicitarReservasAdultos(bono);
			for(int i = 0; i < reservasAdultos.size(); i++) {
				System.out.println(reservasAdultos.get(i).toString());	
			}
		} else if(tipo == Dificultad.FAMILIAR) {
			ArrayList<DTORFamiliar> reservasFamiliares = reservaBono.solicitarReservasFamiliares(bono);
			for(int i = 0; i < reservasFamiliares.size(); i++) {
				System.out.println(reservasFamiliares.get(i).toString());
			}
		}
	}

	/**
	 * Muestra las reservas de un día y una pista específica
	 * @param usuario Correo de usuario que solicita la reserva
	 * @param fecha Fecha de la reserva
	 * @param pista Pista de la reserva
	 * @param tipo Tipo de dificultad de la pista
	 * @param scan_ Scanner para leer por teclado
	 * @return
	 **/
	
	public void consultarReservaEspecifica (String usuario, LocalDate fecha, String pista, Dificultad tipo, Scanner scan_){
		System.out.println("0. Consultar reserva individual");
        System.out.println("1. Consultar reserva bono");
    	System.out.println("");
        System.out.println("Elija una opcion escribiendo su numero");
        int option_= Integer.parseInt(scan_.nextLine());
        
        if(option_ == 0) {
        	DAOReserva reservaTabla = new DAOReserva();
    		ArrayList<DTORInfantil> reservasInfantiles = reservaTabla.solicitarReservasInfantiles();
    		ArrayList<DTORAdulto> reservasAdultos = reservaTabla.solicitarReservasAdultos();
    		ArrayList<DTORFamiliar> reservasFamiliares = reservaTabla.solicitarReservasFamiliares();
    		
        	for(int i=0; i < reservasInfantiles.size() ; i++){
        		if(reservasInfantiles.get(i).getUsuario().equals(usuario)) {
	        		if(reservasInfantiles.get(i).getFecha().isEqual(fecha) && reservasInfantiles.get(i).getPista().equals(pista) && reservasInfantiles.get(i).getTipo() == tipo){
	    				System.out.println(reservasInfantiles.get(i).toString());
	    			}
        		}
        	}
        	for(int i=0; i < reservasAdultos.size() ; i++){
        		if(reservasAdultos.get(i).getUsuario().equals(usuario)) {
	        		if(reservasAdultos.get(i).getFecha().isEqual(fecha) && reservasAdultos.get(i).getPista().equals(pista) && reservasAdultos.get(i).getTipo() == tipo){
	    				System.out.println(reservasAdultos.get(i).toString());
	    			}
        		}
        	}
        	for(int i=0; i < reservasFamiliares.size() ; i++){
        		if(reservasFamiliares.get(i).getUsuario().equals(usuario)) {
	        		if(reservasFamiliares.get(i).getFecha().isEqual(fecha) && reservasFamiliares.get(i).getPista().equals(pista) && reservasFamiliares.get(i).getTipo() == tipo){
	    				System.out.println(reservasFamiliares.get(i).toString());
	    			}
        		}
        	}
        }else if(option_ == 1) {
        	DAOBono reservaBono = new DAOBono();
    		DTOBono bono = reservaBono.solicitarBono(usuario, tipo);
    		
    		if(tipo == Dificultad.INFANTIL) {
    			ArrayList<DTORInfantil> reservasInfantiles = reservaBono.solicitarReservasInfantiles(bono);
    			for(int i = 0; i < reservasInfantiles.size(); i++) {
    				if(reservasInfantiles.get(i).getFecha().isEqual(fecha) && reservasInfantiles.get(i).getPista().equals(pista)) {				
    					System.out.println(reservasInfantiles.get(i).toString());
    				}
    			}
    		}else if(tipo == Dificultad.ADULTO) {
    			ArrayList<DTORAdulto> reservasAdultos = reservaBono.solicitarReservasAdultos(bono);
    			for(int i = 0; i < reservasAdultos.size(); i++) {
    				if(reservasAdultos.get(i).getFecha().isEqual(fecha) && reservasAdultos.get(i).getPista().equals(pista)){
	    				System.out.println(reservasAdultos.get(i).toString());
	    			}	
    			}
    		} else if(tipo == Dificultad.FAMILIAR) {
    			ArrayList<DTORFamiliar> reservasFamiliares = reservaBono.solicitarReservasFamiliares(bono);
    			for(int i = 0; i < reservasFamiliares.size(); i++) {
    				if(reservasFamiliares.get(i).getFecha().isEqual(fecha) && reservasFamiliares.get(i).getPista().equals(pista)){
	    				System.out.println(reservasFamiliares.get(i).toString());
	    			}
    			}
    		}
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}

	/**
	 * Elimina la reserva selecionada
	 * @param usuario Correo de usuario que solicita la reserva
	 * @param fecha Fecha de la reserva
	 * @param pista Pista de la reserva
	 * @param tipo Tipo de dificultad de la pista
	 * @param duracion Duracion de la reserva
	 * @return
	 **/

	public void eliminarReserva (String usuario, LocalDate fecha, String pista, Dificultad tipo, Integer duracion){
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORInfantil> reservasInfantiles = reservaTabla.solicitarReservasInfantiles();
		ArrayList<DTORAdulto> reservasAdultos = reservaTabla.solicitarReservasAdultos();
		ArrayList<DTORFamiliar> reservasFamiliares = reservaTabla.solicitarReservasFamiliares();
		
		for(int i=0; i < reservasInfantiles.size() ; i++){
    		if(reservasInfantiles.get(i).getUsuario().equals(usuario)) {
        		if(reservasInfantiles.get(i).getFecha().isEqual(fecha) && reservasInfantiles.get(i).getPista().equals(pista) && reservasInfantiles.get(i).getTipo() == tipo){
    				if(!(reservaTabla.deleteReservaInfantil(reservasInfantiles.get(i)) == 0)) {
    					System.out.println("Reserva eliminada con exito");
        				System.out.println("-------------------------------------");
        				System.out.println("");
        				return;
    				}        			
    			}
    		}
    	}
    	for(int i=0; i < reservasAdultos.size() ; i++){
    		if(reservasAdultos.get(i).getUsuario().equals(usuario)) {
        		if(reservasAdultos.get(i).getFecha().isEqual(fecha) && reservasAdultos.get(i).getPista().equals(pista) && reservasAdultos.get(i).getTipo() == tipo){
        			if(!(reservaTabla.deleteReservaAdulto(reservasAdultos.get(i)) == 0)) {
    					System.out.println("Reserva eliminada con exito");
        				System.out.println("-------------------------------------");
        				System.out.println("");
        				return;
    				} 
    			}
    		}
    	}
    	for(int i=0; i < reservasFamiliares.size() ; i++){
    		if(reservasFamiliares.get(i).getUsuario().equals(usuario)) {
        		if(reservasFamiliares.get(i).getFecha().isEqual(fecha) && reservasFamiliares.get(i).getPista().equals(pista) && reservasFamiliares.get(i).getTipo() == tipo){
        			if(!(reservaTabla.deleteReservaFamiliar(reservasFamiliares.get(i)) == 0)) {
    					System.out.println("Reserva eliminada con exito");
        				System.out.println("-------------------------------------");
        				System.out.println("");
        				return;
    				} 
    			}
    		}
    	}				
		System.out.println("Error. La reserva no se puede eliminar");
		System.out.println("-------------------------------------");
		System.out.println("");
	}

	/**
	 * Modificar la reserva infantil
	 * @param usuario Correo de usuario que solicita la reserva
	 * @param fecha Fecha de la reserva
	 * @param pista Pista de la reserva
	 * @param duracion Duracion de la reserva
	 * @param scan_ Scanner para leer por teclado
	 * @return
	 **/

	public void modificarReservaInfantil (String usuario, LocalDate fecha, String pista, Integer duracion, Scanner scan_){	
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORInfantil> reservasInfantiles = reservaTabla.solicitarReservasInfantiles();
		
		for(int i=0 ; i< reservasInfantiles.size(); i++) {
			if (reservasInfantiles.get(i).getUsuario().equals(usuario) && reservasInfantiles.get(i).getFecha().isEqual(fecha) &&
				reservasInfantiles.get(i).getDur() == duracion && reservasInfantiles.get(i).getPista().equals(pista) &&
				reservasInfantiles.get(i).getFecha().isAfter(LocalDate.now().minusDays(1)) ) {			
				int idReserva = reservaTabla.solicitarIdReserva(usuario, fecha, pista, duracion, Dificultad.INFANTIL);
				reservasInfantiles.get(i).setIdReserva(idReserva);
				
				int opcion = 1;
				while (opcion != 0) {
					System.out.println("0: Terminar modificacion.\n"
					+ "1: Cambiar Fecha.\n"
					+ "2: Cambiar Duracion.\n"
					+ "Introduzca una opcion:"); 
					
					opcion = Integer.parseInt(scan_.nextLine());
					switch (opcion) {					
					case 0: 
						if(reservaTabla.escribirReservaInfantilUpdate(reservasInfantiles.get(i)) == 0) {
							System.out.println("Error. La reserva no se puede modificar");
							System.out.println("-------------------------------------");
							System.out.println("");
							return;
						}
						System.out.println("Reserva modificada con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
						return;

					case 1:
						System.out.println("Introduzca la nueva fecha de reserva");
							LocalDate nfecha = LocalDate.parse(scan_.nextLine());
							System.out.println("");
							reservasInfantiles.get(i).setFecha(nfecha);
						break;
							
					case 2:
						System.out.println("Introduzca la nueva duracion de reserva");
						int nduracion = Integer.parseInt(scan_.nextLine());				
						System.out.println("");
						reservasInfantiles.get(i).setDur(nduracion);
					break;

					default:
						System.out.println("Opcion no reconocida");
						System.out.println("");
						break;
					}
				}
			}
		}
		System.out.println("Error. La reserva no se puede modificar");
		System.out.println("-------------------------------------");
		System.out.println("");
	}
	
	/**
	 * Modificar una reserva adulta
	 * @param usuario Correo de usuario que solicita la reserva
	 * @param fecha Fecha de la reserva
	 * @param pista Pista de la reserva
	 * @param duracion Duracion de la reserva
	 * @param scan_ Scanner para leer por teclado
	 * @return
	 */
	
	public void modificarReservaAdulto (String usuario, LocalDate fecha, String pista, Integer duracion, Scanner scan_){	
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORAdulto> reservasAdultos = reservaTabla.solicitarReservasAdultos();
		
		for(int i=0 ; i< reservasAdultos.size(); i++) {
			if (reservasAdultos.get(i).getUsuario().equals(usuario) && reservasAdultos.get(i).getFecha().isEqual(fecha) &&
					reservasAdultos.get(i).getDur() == duracion && reservasAdultos.get(i).getPista().equals(pista) &&
					reservasAdultos.get(i).getFecha().isAfter(LocalDate.now().minusDays(1))) {
					int idReserva = reservaTabla.solicitarIdReserva(usuario, fecha, pista, duracion, Dificultad.ADULTO);
					reservasAdultos.get(i).setIdReserva(idReserva);
				
				int opcion = 1;
				while (opcion != 0) {
					System.out.println("0: Terminar modificacion.\n"
					+ "1: Cambiar Fecha.\n"
					+ "2: Cambiar Duracion.\n"
					+ "Introduzca una opcion:"); 
					
					opcion = Integer.parseInt(scan_.nextLine());
					switch (opcion) {					
					case 0: 
						if(reservaTabla.escribirReservaAdultoUpdate(reservasAdultos.get(i)) == 0) {
							System.out.println("Error. La reserva no se puede modificar");
							System.out.println("-------------------------------------");
							System.out.println("");
							return;
						}
						System.out.println("Reserva modificada con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
						return;

					case 1:
						System.out.println("Introduzca la nueva fecha de reserva");
							LocalDate nfecha = LocalDate.parse(scan_.nextLine());
							System.out.println("");
							reservasAdultos.get(i).setFecha(nfecha);
						break;
							
					case 2:
						System.out.println("Introduzca la nueva duracion de reserva");
						int nduracion = Integer.parseInt(scan_.nextLine());				
						System.out.println("");
						reservasAdultos.get(i).setDur(nduracion);
					break;

					default:
						System.out.println("Opcion no reconocida");
						System.out.println("");
						break;
					}
				}
			}
		}
		System.out.println("Error. La reserva no se puede modificar");
		System.out.println("-------------------------------------");
		System.out.println("");
	}
	
	/**
	 * Modificar la reserva familiar
	 * @param usuario Correo de usuario que solicita la reserva
	 * @param fecha Fecha de la reserva
	 * @param pista Pista de la reserva
	 * @param duracion Duracion de la reserva
	 * @param scan_ Scanner para leer por teclado
	 * @return
	 */
	
	public void modificarReservaFamiliar (String usuario, LocalDate fecha, String pista, Integer duracion, Scanner scan_){	
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORFamiliar> reservasFamiliares = reservaTabla.solicitarReservasFamiliares();
		
		for(int i=0 ; i< reservasFamiliares.size(); i++) {
			if (reservasFamiliares.get(i).getUsuario().equals(usuario) && reservasFamiliares.get(i).getFecha().isEqual(fecha) &&
				reservasFamiliares.get(i).getDur() == duracion && reservasFamiliares.get(i).getPista().equals(pista) &&
					reservasFamiliares.get(i).getFecha().isAfter(LocalDate.now().minusDays(1))) {
					int idReserva = reservaTabla.solicitarIdReserva(usuario, fecha, pista, duracion, Dificultad.FAMILIAR);
					reservasFamiliares.get(i).setIdReserva(idReserva);
					
				int opcion = 1;
				while (opcion != 0) {
					System.out.println("0: Terminar modificacion.\n"
					+ "1: Cambiar Fecha.\n"
					+ "2: Cambiar Duracion.\n"
					+ "Introduzca una opcion:"); 
					
					opcion = Integer.parseInt(scan_.nextLine());
					switch (opcion) {					
					case 0: 
						if(reservaTabla.escribirReservaFamiliarUpdate(reservasFamiliares.get(i)) == 0) {
							System.out.println("Error. La reserva no se puede modificar");
							System.out.println("-------------------------------------");
							System.out.println("");
							return;
						}
						System.out.println("Reserva modificada con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
						return;

					case 1:
						System.out.println("Introduzca la nueva fecha de reserva");
							LocalDate nfecha = LocalDate.parse(scan_.nextLine());
							System.out.println("");
							reservasFamiliares.get(i).setFecha(nfecha);		
						break;
							
					case 2:
						System.out.println("Introduzca la nueva duracion de reserva");
						int nduracion = Integer.parseInt(scan_.nextLine());				
						System.out.println("");
						reservasFamiliares.get(i).setDur(nduracion);											
					break;

					default:
						System.out.println("Opcion no reconocida");
						System.out.println("");
						break;
					}
				}
			}
		}
		System.out.println("Error. La reserva no se puede modificar");
		System.out.println("-------------------------------------");
		System.out.println("");
	}
}
