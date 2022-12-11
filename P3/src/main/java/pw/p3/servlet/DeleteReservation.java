package pw.p3.servlet;

import pw.p3.business.reservation.*;
import pw.p3.data.dao.ReservationDAO;
import pw.p3.display.javabean.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="DeleteReservation", urlPatterns="/deleteReservation")
public class DeleteReservation extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -937488287959414409L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(customerBean.getAdminUser()) {
				String id_string = request.getParameter("id");
				ReservationDAO reservationDAO = new ReservationDAO();
				ArrayList<RInfantileDTO> reservasInfantiles = reservationDAO.solicitarReservasInfantiles();
				ArrayList<RAdultDTO> reservasAdultas = reservationDAO.solicitarReservasAdultos();
				ArrayList<RFamiliarDTO> reservasFamiliares = reservationDAO.solicitarReservasFamiliares();
				request.setAttribute("reservasInfantiles", reservasInfantiles);
				request.setAttribute("reservasAdultas", reservasAdultas);
				request.setAttribute("reservasFamiliares", reservasFamiliares);
				
				if (id_string != null) {
					Integer id = Integer.parseInt(id_string);
					
					if(reservationDAO.deleteReservaPendiente(id) == 0) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. Reserva no modificada");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/DeleteReservationView.jsp");
						error.include(request, response);
					} else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("DeleteReservation");
						RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
						correcto.include(request, response);
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/admin/DeleteReservationView.jsp");
					vista.forward(request, response);
				}
			} else {
				RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/errorUsuarioAdminView.jsp");
				error.forward(request, response);
			}
		} else {
			RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/errorUsuarioLoginView.jsp");
			error.forward(request, response);
		}
	}
}
