package pw.p3.servlet.client;

import pw.p3.business.reservation.*;
import pw.p3.data.dao.ReservationDAO;
import pw.p3.display.javabean.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name="ModifyReservation", urlPatterns="/modifyReservation")
public class ModifyReservation extends HttpServlet {

	/** Serial ID */
	private static final long serialVersionUID = -937488287959414409L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		ReservationBean reservaBean = (ReservationBean)session.getAttribute("reservaBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(!customerBean.getAdminUser()) {
				ReservationDAO reservationDAO = new ReservationDAO();
				ArrayList<RInfantileDTO> reservasInfantiles = reservationDAO.solicitarReservasInfantiles();
				ArrayList<RAdultDTO> reservasAdultas = reservationDAO.solicitarReservasAdultos();
				ArrayList<RFamiliarDTO> reservasFamiliares = reservationDAO.solicitarReservasFamiliares();
				request.setAttribute("reservasInfantiles", reservasInfantiles);
				request.setAttribute("reservasAdultas", reservasAdultas);
				request.setAttribute("reservasFamiliares", reservasFamiliares);
				
				String id_string = request.getParameter("id");				
				String duracion_string = request.getParameter("duracion");
				String fecha_string = request.getParameter("fecha");
				String pista = request.getParameter("pista");
				String adultos_string = request.getParameter("adultos");
				String ninos_string = request.getParameter("ninos");
				if (id_string != null) {
					Integer id = Integer.parseInt(id_string);
					reservaBean.setReserva(id);
					request.setAttribute("reserva", id);
					ReservationDAO gestorReserva = new ReservationDAO();
					String dificultad = gestorReserva.solicitarDificultad(id);
					RInfantileDTO reservaInfantil = new RInfantileDTO();
					RAdultDTO reservaAdulto = new RAdultDTO();
					RFamiliarDTO reservaFamiliar = new RFamiliarDTO();
					Integer duracion = 60;
					Integer adultos = 0;
					Integer ninos = 0;
					LocalDate fecha;
					if(dificultad.equalsIgnoreCase("infantil")) {
						reservaInfantil = gestorReserva.solicitarReservaInfantil(id);
						duracion = reservaInfantil.getDur();
						ninos = reservaInfantil.getParticipantes();
						fecha = reservaInfantil.getFecha();
					}else if(dificultad.equalsIgnoreCase("adulto")) {
						reservaAdulto = gestorReserva.solicitarReservaAdulto(id);
						duracion = reservaAdulto.getDur();
						adultos = reservaAdulto.getPartipantes();
						fecha = reservaAdulto.getFecha();
					}else {
						reservaFamiliar = gestorReserva.solicitarReservaFamiliar(id);
						duracion = reservaFamiliar.getDur();
						adultos = reservaFamiliar.getadultos();
						ninos = reservaFamiliar.getNinos();
						fecha = reservaFamiliar.getFecha();
					}
					
					if(reservaInfantil != null || reservaAdulto != null || reservaFamiliar != null ) {
						if(duracion_string != null && fecha_string != null && adultos_string != null && ninos_string != null) {
							if(!duracion_string.equalsIgnoreCase("")) {
								duracion = Integer.parseInt(duracion_string);
							}
							reservaBean.setDuracion(duracion);
							if(!adultos_string.equalsIgnoreCase("")) {
								adultos = Integer.parseInt(adultos_string);
							}
							reservaBean.setAdultos(adultos);
							if(!ninos_string.equalsIgnoreCase("")){
								ninos = Integer.parseInt(ninos_string);
							}
							reservaBean.setNinos(ninos);
							if(!fecha_string.equalsIgnoreCase("")) {
								fecha = LocalDate.parse(fecha_string);
							}
							reservaBean.setFecha(fecha);
							reservaBean.setDificultad(dificultad);
							request.setAttribute("reservaBean", reservaBean);
							
							if(pista != null) {
								if(dificultad.equalsIgnoreCase("infantil")) {
									reservaInfantil.setDur(duracion);
									reservaInfantil.setFecha(fecha);
									reservaInfantil.setParticipantes(ninos);
									reservaInfantil.setPista(pista);
									
									if(gestorReserva.actualizarInfantil(reservaInfantil)==0) {
										response.setContentType("text/html");
										PrintWriter out = response.getWriter();
										out.println("Error. Reserva no modificada");
										RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/modifyReservation/modifyReservationView.jsp");
										error.include(request, response);
									}else {
										response.setContentType("text/html");
										PrintWriter out = response.getWriter();
										out.println("ModifyStateKart");
										RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
										correcto.include(request, response);
									}
								}else if(dificultad.equalsIgnoreCase("adulto")) {
									reservaAdulto.setDur(duracion);
									reservaAdulto.setFecha(fecha);
									reservaAdulto.setParticipantes(ninos);
									reservaAdulto.setPista(pista);
									if(gestorReserva.actualizarAdulto(reservaAdulto)==0) {
										response.setContentType("text/html");
										PrintWriter out = response.getWriter();
										out.println("Error. Kart no modificado");
										RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/modifyReservation/modifyReservationView.jsp");
										error.include(request, response);
									}else {
										response.setContentType("text/html");
										PrintWriter out = response.getWriter();
										out.println("ModifyStateKart");
										RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
										correcto.include(request, response);
									}
								}else {
									reservaFamiliar.setDur(duracion);
									reservaFamiliar.setFecha(fecha);
									reservaFamiliar.setninos(ninos);
									reservaFamiliar.setadultos(adultos);
									reservaFamiliar.setPista(pista);
									if(gestorReserva.actualizarFamiliar(reservaFamiliar)==0) {
										response.setContentType("text/html");
										PrintWriter out = response.getWriter();
										out.println("Error. Kart no modificado");
										RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/modifyReservation/modifyReservationView.jsp");
										error.include(request, response);
									}else {
										response.setContentType("text/html");
										PrintWriter out = response.getWriter();
										out.println("ModifyStateKart");
										RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
										correcto.include(request, response);
									}
								}
							} else {
								RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/modifyReservation/modifyCircuitReservationView.jsp");
								vista.forward(request, response);
							}
						} else {
							RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/modifyReservation/modifyReservationView.jsp");
							vista.forward(request, response);
						}
					} else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. La reserva no existe");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/modifyReservation/showModifyReservationView.jsp");
						error.include(request, response);
					} 
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/showReservations/showModifyReservationView.jsp");
					vista.forward(request, response);
				}
			} else {
				RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/errorUsuarioClienteView.jsp");
				error.forward(request, response);
			}
		} else {
		RequestDispatcher error = request.getRequestDispatcher("/mvc/view/errorUsuarioLoginView.jsp");
		error.forward(request, response);
		}
	}
}
