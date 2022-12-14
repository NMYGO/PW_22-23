package pw.p3.servlet.admin;

import pw.p3.business.circuit.*;
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
 * Servlet para modificar el estado de un circuito
 * 
 * @author David Olmo Cejudo
 * @author Francisco Moreno Cano
 * @author Ángel Quintero Montes
 * @author Diego Tabas Costa
 *
 **/

@WebServlet(name="ModifyStateCircuit", urlPatterns="/modifyStateCircuit")
public class ModifyStateCircuit extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -8869673328353849111L;
	
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
				CircuitDAO circuitDAO = new CircuitDAO();
				ArrayList<CircuitDTO> pistas = circuitDAO.solicitarPistas(path);
				request.setAttribute("pistas", pistas);
				
				if (nombre != null || estado_string != null) {
					Boolean estado = Boolean.valueOf(estado_string);
					CircuitDTO pista = circuitDAO.solicitarPista(path, nombre);
					
					if(pista == null) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. La pista no existe");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/circuit/ModifyStateCircuitView.jsp");
						error.include(request, response);
					} else {
						pista.setEstado(estado);
						if(circuitDAO.escribirPistaUpdate(path, pista) == 0) {
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.println("Error. Pista no modificada");
							RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/circuit/ModifyStateCircuitView.jsp");
							error.include(request, response);
						} else {
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.println("ModifyStateCircuit");
							RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
							correcto.include(request, response);
						}
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/admin/circuit/ModifyStateCircuitView.jsp");
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
