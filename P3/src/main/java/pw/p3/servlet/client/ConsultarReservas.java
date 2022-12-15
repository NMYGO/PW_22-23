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

@WebServlet(name="ConsultarReservas", urlPatterns="/consultarReservas")
public class ConsultarReservas extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -937488287959414409L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
				String fecha1_string = request.getParameter("fechaInicio");
				String fecha2_string = request.getParameter("fechaFinal");
				ReservationDAO reservationDAO = new ReservationDAO();
				ArrayList<RInfantileDTO> reservasInfantiles = reservationDAO.solicitarReservasInfantiles();
				ArrayList<RAdultDTO> reservasAdultas = reservationDAO.solicitarReservasAdultos();
				ArrayList<RFamiliarDTO> reservasFamiliares = reservationDAO.solicitarReservasFamiliares();
				request.setAttribute("reservasInfantiles", reservasInfantiles);
				request.setAttribute("reservasAdultas", reservasAdultas);
				request.setAttribute("reservasFamiliares", reservasFamiliares);
				
				if (fecha1_string != null && fecha2_string != null) {
					LocalDate init = LocalDate.parse(fecha1_string);
					LocalDate fin = LocalDate.parse(fecha2_string);
					if(init.isAfter(fin)) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. La fecha final tiene que ser menos a la fecha de inicio");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/selectDatesView.jsp");
						error.include(request, response);
					}
					
					else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Jajant");
						RequestDispatcher correcto = request.getRequestDispatcher("/mvc/view/client/showReservationsView.jsp");
						correcto.include(request, response);
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/selectDatesView.jsp");
					vista.forward(request, response);
				}
			} else {
			RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/errorUsuarioLoginView.jsp");
			error.forward(request, response);
		}
	}
}