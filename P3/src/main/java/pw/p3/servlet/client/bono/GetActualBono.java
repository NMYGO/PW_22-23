package pw.p3.servlet.client.bono;

import pw.p3.business.reservation.*;
import pw.p3.data.Dificultad;
import pw.p3.data.dao.BonoDAO;
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

@WebServlet(name="GetActualBono", urlPatterns="/getActualBono")
public class GetActualBono extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -8861600984429414188L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(!customerBean.getAdminUser()) {
				String tipo_string = request.getParameter("criterio");
				if (tipo_string != null && tipo_string.equalsIgnoreCase("actual")) {
						String id_string = request.getParameter("id");
						if(id_string != null) {
							Integer sesion = 0;
							Dificultad tipo = Dificultad.valueOf(tipo_string);
							BonoDAO bonoDAO = new BonoDAO();
							BonoDTO bono = new BonoDTO(sesion, customerBean.getCorreoUser(), tipo);
							if (bonoDAO.solicitarBono(Integer.parseInt(id_string)) != null) {
									bono = bonoDAO.solicitarBono(Integer.parseInt(id_string));
							} else {
								response.setContentType("text/html");
								PrintWriter out = response.getWriter();
								out.println("Error. Bono de ese tipo no existe");
								RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/getBono/getActualBonoView.jsp");
								vista.include(request, response);
							}
						} else {
							
							RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/getBono/getActualBonoView.jsp");
							vista.forward(request, response);
						}
				} else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("Error. Tonto");
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/getBono/getNewBonoView.jsp");
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
