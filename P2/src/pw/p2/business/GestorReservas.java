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
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param nParticipantes
	 * @param scan_
	 * @return true si la reserva se ha realizado, false si no
	 */
	
	public boolean ReservaIndividualInfantil (GestorReservas GestorReservas_, GestorPistas GestorPistas_, Integer nparticipantes, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);
		ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, nparticipantes, Dificultad.INFANTIL);
		
				if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					System.out.println("Introduzca el numero de participantes (niños)");
						int participantes= Integer.parseInt(scan_.nextLine());
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
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param nParticipantes
	 * @param scan_
	 * @return
	 */
	
	public boolean ReservaIndividualAdulto (GestorReservas GestorReservas_, GestorPistas GestorPistas_, Integer nParticipantes, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);
		ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, nParticipantes, Dificultad.ADULTO);
		
			if (usuario.getCorreo().equals(correo)) {
				if ((usuario.getNacimiento()).isBefore(LocalDate.now().minusYears(18))) {
					System.out.println("Introduzca el numero de participantes (adultos)");
					int participantes= Integer.parseInt(scan_.nextLine());
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
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param nParticipantes
	 * @param scan_
	 * @return
	 */
	
	public boolean ReservaIndividualFamiliar (GestorReservas GestorReservas_, GestorPistas GestorPistas_, Integer nParticipantes, Scanner scan_) {
		System.out.println("Introduzca su correo de usuario");
		String correo = scan_.nextLine();
		System.out.println("");
		
		DAOUsuario usuarioTabla = new DAOUsuario();
		DAOPista pistaTabla = new DAOPista();
		DTOUsuario usuario = usuarioTabla.solicitarUsuario(correo);
		ArrayList<DTOPista> lpista = pistaTabla.solicitarPistasLibres(false, nParticipantes, Dificultad.FAMILIAR);
		
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
					for (int j = 0; j < lpista.size(); j++) {
						ArrayList<DTOKart> lkart = new ArrayList<DTOKart>();
						lkart = lpista.get(j).getLkart();
						int nKartInfantil = 0;
						int nKartAdulto = 0;
						for (int k = 0; k < lkart.size(); k++) {
							if (lkart.get(k).isTipo()) {
								nKartInfantil += 1;
							}else {
								nKartAdulto += 1;
							}
						}
						if (participantesInfantiles > nKartInfantil || participantesAdultos > nKartAdulto) {
							lpista.remove(j);
						}
					}
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
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param scan_
	 * @return
	 */
	
	public boolean ReservaBonoInfantil (GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
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
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param scan_
	 * @return
	 */
	
	public boolean ReservaBonoAdulto (GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
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
	 * @param GestorReservas_
	 * @param GestorPistas_
	 * @param GestorUsuarios_
	 * @param scan_
	 * @return
	 */
	
	public boolean ReservaBonoFamiliar (GestorReservas GestorReservas_, GestorPistas GestorPistas_, GestorUsuarios GestorUsuarios_, Scanner scan_) {
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
	 * @param scan_
	 */
	
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
	 * @param scan_
	 */

	/**public void consultarReservasFuturasBono (String usuario){
		for(int i = 0; i < arrayBonos.size(); i++) {
				if(arrayBonos.get(i).getbUsuario().equals(usuario)) {
					System.out.println(arrayBonos.get(i).toString());
					for(int j = 0; j < arrayBonos.get(i).getArrayReservas().size(); j++) {
						if(arrayBonos.get(i).getArrayReservas().get(j).getFecha().isAfter(LocalDate.now())) {
							System.out.println(arrayBonos.get(i).getArrayReservas().get(j).toString());
						}
					}
				}
		}
	}**/

	/**
	 * Muestra las reservas de un día y una pista específicas
	 * @param GestorPistas_
	 * @param GestorReservas_
	 * @param scan_
	 */
	
	public void consultarReservaEspecifica (GestorPistas GestorPistas_, GestorReservas GestorReservas_, String usuario, LocalDate fecha, String pista, Scanner scan_){		
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
	        		if(reservasInfantiles.get(i).getFecha().isEqual(fecha) && reservasInfantiles.get(i).getPista().equals(pista)){
	    				System.out.println(reservasInfantiles.get(i).toString());
	    			}
        		}
        	}
        	for(int i=0; i < reservasAdultos.size() ; i++){
        		if(reservasAdultos.get(i).getUsuario().equals(usuario)) {
	        		if(reservasAdultos.get(i).getFecha().isEqual(fecha) && reservasAdultos.get(i).getPista().equals(pista)){
	    				System.out.println(reservasAdultos.get(i).toString());
	    			}
        		}
        	}
        	for(int i=0; i < reservasFamiliares.size() ; i++){
        		if(reservasFamiliares.get(i).getUsuario().equals(usuario)) {
	        		if(reservasFamiliares.get(i).getFecha().isEqual(fecha) && reservasFamiliares.get(i).getPista().equals(pista)){
	    				System.out.println(reservasFamiliares.get(i).toString());
	    			}
        		}
        	}
        }else if(option_ == 1) {
        	/**for(int i = 0; i < arrayBonos.size(); i++){
        		if(arrayBonos.get(i).getbUsuario().equals(usuario)) {
	        		for(int j = 0; j < arrayBonos.size(); j++){
		        		if(arrayBonos.get(i).getArrayReservas().get(j).getFecha().isEqual(fecha) && arrayBonos.get(i).getArrayReservas().get(j).getPista().equals(pista)){
		        			System.out.println(arrayBonos.get(i).toString());
		        			System.out.println(arrayBonos.get(i).getArrayReservas().get(j).toString());
		    			}
	        		}
        		}
        	}**/
        }else {
        	System.out.println("Opcion no reconocida");
			System.out.println("");
        }
	}

	/**
	 * Elimina la reserva selecionada
	 * @param scan_
	 */

	public void eliminarReserva (String usuario, LocalDate fecha, String pista, Integer duracion){
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORInfantil> reservasInfantiles = reservaTabla.solicitarReservasInfantiles();
		ArrayList<DTORAdulto> reservasAdultos = reservaTabla.solicitarReservasAdultos();
		ArrayList<DTORFamiliar> reservasFamiliares = reservaTabla.solicitarReservasFamiliares();
		for(int i=0; i < reservasInfantiles.size() ; i++){
    		if(reservasInfantiles.get(i).getUsuario().equals(usuario)) {
        		if(reservasInfantiles.get(i).getFecha().isEqual(fecha) && reservasInfantiles.get(i).getPista().equals(pista)){
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
        		if(reservasAdultos.get(i).getFecha().isEqual(fecha) && reservasAdultos.get(i).getPista().equals(pista)){
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
        		if(reservasFamiliares.get(i).getFecha().isEqual(fecha) && reservasFamiliares.get(i).getPista().equals(pista)){
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
	 * Modifica la reserva seleccionada
	 * @param scan_
	 */

	public void modificarReservaInfantil (String usuario, LocalDate fecha, String pista, Integer duracion, Scanner scan_){	
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORInfantil> reservasInfantiles = reservaTabla.solicitarReservasInfantiles();
		for(int i=0 ; i< reservasInfantiles.size(); i++) {
			if (reservasInfantiles.get(i).getUsuario().equals(usuario) && reservasInfantiles.get(i).getFecha().isEqual(fecha) &&
				reservasInfantiles.get(i).getDur() == duracion && reservasInfantiles.get(i).getPista().equals(pista) &&
				reservasInfantiles.get(i).getFecha().isBefore(LocalDate.now().minusDays(1))) {
				int opcion = 1;
				while (opcion != 0) {
					System.out.println("0: Terminar modificacion.\n"
					+ "1: Cambiar Fecha.\n"
					+ "2: Cambiar Duracion.\n"
					+ "Introduzca una opcion:"); 
					
					opcion = Integer.parseInt(scan_.nextLine());
					switch (opcion) {					
					case 0: 
						System.out.println("Reserva modificada con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
						return;

					case 1:
						System.out.println("Introduzca la nueva fecha de reserva");
							LocalDate nfecha = LocalDate.parse(scan_.nextLine());
							System.out.println("");
							reservasInfantiles.get(i).setFecha(nfecha);
							if(reservaTabla.escribirReservaInfantilUpdate(reservasInfantiles.get(i)) == 0) {
								System.out.println("Error. La reserva no se puede modificar");
								System.out.println("-------------------------------------");
								System.out.println("");
								return;
							}
		
						break;
							
					case 2:
						System.out.println("Introduzca la nueva duracion de reserva");
						int nduracion = Integer.parseInt(scan_.nextLine());				
						System.out.println("");
						reservasInfantiles.get(i).setDur(nduracion);
						if(reservaTabla.escribirReservaInfantilUpdate(reservasInfantiles.get(i)) == 0) {
							System.out.println("Error. La reserva no se puede modificar");
							System.out.println("-------------------------------------");
							System.out.println("");
							return;
						}
					
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
	
	public void modificarReservaAdulto (String usuario, LocalDate fecha, String pista, Integer duracion, Scanner scan_){	
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORAdulto> reservasAdultos = reservaTabla.solicitarReservasAdultos();
		for(int i=0 ; i< reservasAdultos.size(); i++) {
			if (reservasAdultos.get(i).getUsuario().equals(usuario) && reservasAdultos.get(i).getFecha().isEqual(fecha) &&
					reservasAdultos.get(i).getDur() == duracion && reservasAdultos.get(i).getPista().equals(pista) &&
					reservasAdultos.get(i).getFecha().isBefore(LocalDate.now().minusDays(1))) {
				int opcion = 1;
				while (opcion != 0) {
					System.out.println("0: Terminar modificacion.\n"
					+ "1: Cambiar Fecha.\n"
					+ "2: Cambiar Duracion.\n"
					+ "Introduzca una opcion:"); 
					
					opcion = Integer.parseInt(scan_.nextLine());
					switch (opcion) {					
					case 0: 
						System.out.println("Reserva modificada con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
						return;

					case 1:
						System.out.println("Introduzca la nueva fecha de reserva");
							LocalDate nfecha = LocalDate.parse(scan_.nextLine());
							System.out.println("");
							reservasAdultos.get(i).setFecha(nfecha);
							if(reservaTabla.escribirReservaAdultoUpdate(reservasAdultos.get(i)) == 0) {
								System.out.println("Error. La reserva no se puede modificar");
								System.out.println("-------------------------------------");
								System.out.println("");
								return;
							}
		
						break;
							
					case 2:
						System.out.println("Introduzca la nueva duracion de reserva");
						int nduracion = Integer.parseInt(scan_.nextLine());				
						System.out.println("");
						reservasAdultos.get(i).setDur(nduracion);
						if(reservaTabla.escribirReservaAdultoUpdate(reservasAdultos.get(i)) == 0) {
							System.out.println("Error. La reserva no se puede modificar");
							System.out.println("-------------------------------------");
							System.out.println("");
							return;
						}
					
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
	
	public void modificarReservaFamiliar (String usuario, LocalDate fecha, String pista, Integer duracion, Scanner scan_){	
		DAOReserva reservaTabla = new DAOReserva();
		ArrayList<DTORFamiliar> reservasFamiliares = reservaTabla.solicitarReservasFamiliares();
		for(int i=0 ; i< reservasFamiliares.size(); i++) {
			if (reservasFamiliares.get(i).getUsuario().equals(usuario) && reservasFamiliares.get(i).getFecha().isEqual(fecha) &&
				reservasFamiliares.get(i).getDur() == duracion && reservasFamiliares.get(i).getPista().equals(pista) &&
					reservasFamiliares.get(i).getFecha().isBefore(LocalDate.now().minusDays(1))) {
				int opcion = 1;
				while (opcion != 0) {
					System.out.println("0: Terminar modificacion.\n"
					+ "1: Cambiar Fecha.\n"
					+ "2: Cambiar Duracion.\n"
					+ "Introduzca una opcion:"); 
					
					opcion = Integer.parseInt(scan_.nextLine());
					switch (opcion) {					
					case 0: 
						System.out.println("Reserva modificada con exito");
						System.out.println("-------------------------------------");
						System.out.println("");
						return;

					case 1:
						System.out.println("Introduzca la nueva fecha de reserva");
							LocalDate nfecha = LocalDate.parse(scan_.nextLine());
							System.out.println("");
							reservasFamiliares.get(i).setFecha(nfecha);
							if(reservaTabla.escribirReservaFamiliarUpdate(reservasFamiliares.get(i)) == 0) {
								System.out.println("Error. La reserva no se puede modificar");
								System.out.println("-------------------------------------");
								System.out.println("");
								return;
							}
		
						break;
							
					case 2:
						System.out.println("Introduzca la nueva duracion de reserva");
						int nduracion = Integer.parseInt(scan_.nextLine());				
						System.out.println("");
						reservasFamiliares.get(i).setDur(nduracion);
						if(reservaTabla.escribirReservaFamiliarUpdate(reservasFamiliares.get(i)) == 0) {
							System.out.println("Error. La reserva no se puede modificar");
							System.out.println("-------------------------------------");
							System.out.println("");
							return;
						}
					
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


