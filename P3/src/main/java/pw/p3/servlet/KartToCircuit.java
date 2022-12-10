package pw.p3.servlet;

import pw.p3.business.kart.*;
import pw.p3.business.circuit.*;
import pw.p3.data.Estado;
import pw.p3.data.dao.*;
import pw.p3.display.javabean.CustomerBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="KartToCircuit", urlPatterns="/kartToCircuit")
public class KartToCircuit extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -8869673328359414409L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(customerBean.getAdminUser()) {
				String nombrePista = request.getParameter("nombrePista");
				String idKart_string = request.getParameter("idKart");
				
				if (nombrePista != null || idKart_string != null) {
					Integer idKart = Integer.parseInt(idKart_string);
					KartDAO kartDAO = new KartDAO();
					CircuitDAO circuitDAO = new CircuitDAO();
					KartDTO kart = kartDAO.solicitarKart(idKart);
					CircuitDTO pista = circuitDAO.solicitarPista(nombrePista);
					CircuitFunctionality circuitFunctionality = new CircuitFunctionality();
					
					if(kart == null || pista == null) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. El kart o la pista no existen");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/KartToCircuitView.jsp");
						error.include(request, response);
					} else {
						if(kart.getEstado() != Estado.DISPONIBLE || pista.isEstado() == true || circuitFunctionality.asociarKartAPista(kart, pista) == null) {
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.println("Error. Elija un kart y pista adecuados");
							RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/KartToCircuitView.jsp");
							error.include(request, response);
						} else {
							if(kartDAO.escribirKartUpdate(kart) == 0) {
								response.setContentType("text/html");
								PrintWriter out = response.getWriter();
								out.println("Error. Kart no asociado");
								RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/KartToCircuitView.jsp");
								error.include(request, response);
							} else {
								response.setContentType("text/html");
								PrintWriter out = response.getWriter();
								out.println("KartToCircuit");
								RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
								correcto.include(request, response);
							}
						}
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/admin/KartToCircuitView.jsp");
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
