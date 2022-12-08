package pw.p3.servlet;

import pw.p3.data.*;
import pw.p3.data.dao.*;
import pw.p3.business.kart.*;
import pw.p3.display.javabean.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="RegisterKart", urlPatterns="/registerKart")
public class RegisterKart extends HttpServlet{
	
	/** Serial ID */
	private static final long serialVersionUID = -8861667687959414409L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(customerBean.getAdminUser()) {
				String id_string = request.getParameter("id");					
				String tipo_string = request.getParameter("tipo");					
				String estado_string = request.getParameter("estado");		
				String nombrePista = request.getParameter("nombrePista");
				
				if (id_string != null) {
					Integer id = Integer.parseInt(id_string);
					Boolean tipo = Boolean.valueOf(tipo_string);
					Estado estado = Estado.valueOf(estado_string);
					KartDAO kartDAO = new KartDAO();
					KartDTO kart = new KartDTO(id, tipo, estado, nombrePista);
					
					if(kartDAO.escribirKartInsert(kart) == 0) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. Ese kart ya existe");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/registerKartView.jsp");
						error.include(request, response);
					} else {
						RequestDispatcher correcto = request.getRequestDispatcher("../../index.jsp");
						correcto.forward(request, response);
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/admin/registerKartView.jsp");
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
