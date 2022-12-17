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

@WebServlet(name="GetBono", urlPatterns="/getBono")
public class GetBono extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -937488269950014409L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(!customerBean.getAdminUser()) {	
				String criterio = request.getParameter("criterio");
				if (criterio != null) {
					if (criterio.equalsIgnoreCase("nuevo")) {
						RequestDispatcher servlet = request.getRequestDispatcher("/getNewBono");
						servlet.forward(request, response);						
					} else if (criterio.equalsIgnoreCase("actual")) {
						RequestDispatcher servlet = request.getRequestDispatcher("/getActualBono");
						servlet.forward(request, response);
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/getBono/criterioGetBonoView.jsp");
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