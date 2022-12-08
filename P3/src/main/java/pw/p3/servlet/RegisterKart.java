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
		CustomerBean customerBean = new CustomerBean();
		if(customerBean.getAdminUser()) {
			RequestDispatcher vista = request.getRequestDispatcher("registerKartView.jsp");
			vista.forward(request, response);
			String id_string = request.getParameter("id");
				Integer id = Integer.parseInt(id_string);
			String tipo_string = request.getParameter("tipo");
				Boolean tipo = Boolean.valueOf(tipo_string);
			String estado_string = request.getParameter("estado");
				Estado estado = Estado.valueOf(estado_string);
			String nombrePista = request.getParameter("nombrePista");
			KartDAO kartDAO = new KartDAO();
			KartDTO kart = new KartDTO(id, tipo, estado, nombrePista);
			
			if(kartDAO.escribirKartInsert(kart) == 0) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("Error. Ese kart ya existe");
				RequestDispatcher error = request.getRequestDispatcher("registerKartView.jsp");
				error.include(request, response);
			} else {
				RequestDispatcher correcto = request.getRequestDispatcher("index.html");
				correcto.forward(request, response);
			}
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("Error. Debe ser usuario administrador");
			RequestDispatcher error = request.getRequestDispatcher("index.html");
			error.include(request, response);
		}
	}
}
