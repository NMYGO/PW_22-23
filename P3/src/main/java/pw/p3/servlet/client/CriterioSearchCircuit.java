package pw.p3.servlet.client;

import pw.p3.business.circuit.*;
import pw.p3.data.Dificultad;
import pw.p3.data.dao.CircuitDAO;
import pw.p3.display.javabean.CustomerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * Servlet para elegir un criterio de busqueda de pista
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

@WebServlet(name="SearchCircuit", urlPatterns="/searchCircuit")
public class CriterioSearchCircuit extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -8861667748359414188L;
	
	/**
	 * Metodo Get del servlet
	 * @param request Request
	 * @param response Response
	 * @return
	 **/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(!customerBean.getAdminUser()) {
				String criterio = request.getParameter("criterio");
				if (criterio != null) {
					if (criterio.equalsIgnoreCase("tipo")) {
						RequestDispatcher servlet = request.getRequestDispatcher("/searchCircuitType");
						servlet.forward(request, response);						
					} else if (criterio.equalsIgnoreCase("nombre")) {
						RequestDispatcher servlet = request.getRequestDispatcher("/searchCircuitName");
						servlet.forward(request, response);
					} else if (criterio.equalsIgnoreCase("nkarts")) {
						RequestDispatcher servlet = request.getRequestDispatcher("/searchCircuitNkarts");
						servlet.forward(request, response);
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/searchCircuit/criterioSearchCircuitView.jsp");
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
