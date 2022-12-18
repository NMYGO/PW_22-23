package pw.p3.servlet.admin;

import pw.p3.business.circuit.*;
import pw.p3.data.Dificultad;
import pw.p3.data.dao.CircuitDAO;
import pw.p3.display.javabean.CustomerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * Servlet para registrar una pista
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

@WebServlet(name="RegisterCircuit", urlPatterns="/registerCircuit")
public class RegisterCircuit extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -8861667748359414409L;
	
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
			if(customerBean.getAdminUser()) {
				String path = getServletContext().getRealPath("/WEB-INF/sql.properties.txt");
				String nombre = request.getParameter("nombre");					
				String estado_string = request.getParameter("estado");					
				String dificultad_string = request.getParameter("dificultad");
				String maxkarts_string = request.getParameter("maxkarts");
				CircuitDAO circuitDAO = new CircuitDAO();
				ArrayList<CircuitDTO> pistas = circuitDAO.solicitarPistas(path);
				request.setAttribute("pistas", pistas);
				
				if (nombre != null || estado_string != null || dificultad_string != null || maxkarts_string != null) {
					Boolean estado = Boolean.valueOf(estado_string);
					Dificultad dificultad = Dificultad.valueOf(dificultad_string);
					Integer maxkarts = Integer.parseInt(maxkarts_string);
					CircuitDTO circuit = new CircuitDTO(nombre, estado, dificultad, maxkarts);
					
					if(circuitDAO.escribirPistaInsert(path, circuit) == 0) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. Pista no registrada");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/circuit/registerCircuitView.jsp");
						error.include(request, response);
					} else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("RegisterCircuit");
						RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
						correcto.include(request, response);
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/admin/circuit/registerCircuitView.jsp");
					vista.forward(request, response);
				}
			} else {
				RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/errorUsuarioAdminView.jsp");
				error.forward(request, response);
			}
		} else {
			RequestDispatcher error = request.getRequestDispatcher("/mvc/view/errorUsuarioLoginView.jsp");
			error.forward(request, response);
		}
	}
}
