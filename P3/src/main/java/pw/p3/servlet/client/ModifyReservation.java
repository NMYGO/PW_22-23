package pw.p3.servlet.client;

import pw.p3.business.reservation.*;
import pw.p3.data.dao.ReservationDAO;
import pw.p3.display.javabean.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.Iterator;
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
				
				String id_string = request.getParameter("id");
				ReservationDAO reservationDAO = new ReservationDAO();
				ArrayList<RInfantileDTO> reservasInfantiles = reservationDAO.solicitarReservasInfantiles();
				ArrayList<RAdultDTO> reservasAdultas = reservationDAO.solicitarReservasAdultos();
				ArrayList<RFamiliarDTO> reservasFamiliares = reservationDAO.solicitarReservasFamiliares();
				request.setAttribute("reservasInfantiles", reservasInfantiles);
				request.setAttribute("reservasAdultas", reservasAdultas);
				request.setAttribute("reservasFamiliares", reservasFamiliares);
				
				String string_duracion = request.getParameter("duracion");
				String string_fecha = request.getParameter("fecha");
				String string_dificultad = request.getParameter("dificultad");
				String string_ninos = "0";
				String string_adultos = "0";
				
				for (int i = 0; i < reservasInfantiles.size(); i++) {
					if(reservasInfantiles.get(i).getIdReserva() == Integer.parseInt(id_string)) 
					{
						reservaBean.setNinos(reservasInfantiles.get(i).getParticipantes());
						reservaBean.setDuracion(reservasInfantiles.get(i).getDur());
						//reservaBean.setDificultad(reservasInfantiles.get(i).getTipo());
						reservaBean.setFecha(reservasInfantiles.get(i).getFecha());
					}
				}
				
				for (int i = 0; i < reservasAdultas.size(); i++) {
					if(reservasAdultas.get(i).getIdReserva() == Integer.parseInt(id_string)) 
					{
						reservaBean.setAdultos(reservasAdultas.get(i).getParticipantes());
						reservaBean.setDuracion(reservasAdultas.get(i).getDur());
						//reservaBean.setDificultad(reservasInfantiles.get(i).getTipo());
						reservaBean.setFecha(reservasAdultas.get(i).getFecha());
					}
				}
				
				for (int i = 0; i < reservasFamiliares.size(); i++) {
					if(reservasFamiliares.get(i).getIdReserva() == Integer.parseInt(id_string)) 
					{
						reservaBean.setAdultos(reservasFamiliares.get(i).getadultos());
						reservaBean.setNinos(reservasFamiliares.get(i).getNinos());
						reservaBean.setDuracion(reservasFamiliares.get(i).getDur());
						//reservaBean.setDificultad(reservasInfantiles.get(i).getTipo());
						reservaBean.setFecha(reservasFamiliares.get(i).getFecha());
					}
				}
				
				if (string_duracion != null)
				{
					reservaBean.setDuracion(Integer.parseInt(string_duracion));
				}
				
				if (string_fecha != null)
				{
					reservaBean.setFecha(LocalDate.parse(string_fecha));
				}
				
				if(string_dificultad != null)
				{
					reservaBean.setDificultad(string_dificultad);
				}
				
				if (string_dificultad == "INFANTIL")
				{
					reservaBean.setAdultos(0);
					if(string_ninos != null) {
						reservaBean.setNinos(Integer.parseInt(string_ninos));
					}
				}
				
				if (string_dificultad == "ADULTO")
				{
					reservaBean.setNinos(0);
					if(string_adultos != null) 
					{
						reservaBean.setAdultos(Integer.parseInt(string_adultos));
					}
				}
				
				if (string_dificultad == "FAMILIAR")
				{
					if(string_adultos != null) 
					{
						reservaBean.setAdultos(Integer.parseInt(string_adultos));
					}
					if(string_ninos != null) {
						reservaBean.setNinos(Integer.parseInt(string_ninos));
					}
				}
				
				
				if (id_string != null) {
					Integer id = Integer.parseInt(id_string);
					
					if(reservationDAO.cancelReservaCliente(id) == 0) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. Reserva no modificada");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/ModifyReservationView.jsp");
						error.include(request, response);
					} else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("ModifyReservation");
						RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
						correcto.include(request, response);
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/ModifyReservationView.jsp");
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
