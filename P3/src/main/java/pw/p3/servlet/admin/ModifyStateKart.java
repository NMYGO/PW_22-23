package pw.p3.servlet.admin;

import pw.p3.business.kart.*;
import pw.p3.data.Estado;
import pw.p3.data.dao.KartDAO;
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

@WebServlet(name="ModifyStateKart", urlPatterns="/modifyStateKart")
public class ModifyStateKart extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -8869673328353849111L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(customerBean.getAdminUser()) {
				String id_string = request.getParameter("id");
				String estado_string = request.getParameter("estado");
				KartDAO kartDAO = new KartDAO();
				ArrayList<KartDTO> karts = kartDAO.solicitarKarts();
				request.setAttribute("karts", karts);
				
				if (id_string != null || estado_string != null) {
					Integer id = Integer.parseInt(id_string);
					Estado estado = Estado.valueOf(estado_string);
					KartDTO kart = kartDAO.solicitarKart(id);
					
					if(kart == null) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. El kart no existe");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/ModifyStateKartView.jsp");
						error.include(request, response);
					} else {
						if(kart.getNombrePista() == null && estado == Estado.RESERVADO) { //CON JS?? NO PASAR DE NO ASOCIADO A RESERVADO
							response.setContentType("text/html");
							PrintWriter out = response.getWriter();
							out.println("Error. Debe asignar este kart a una pista");
							RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/ModifyStateKartView.jsp");
							error.include(request, response);
						} else {
							kart.setEstado(estado);
							if(estado == Estado.DISPONIBLE) {
								kart.setNombrePista(null);
							}
							if(kartDAO.escribirKartUpdate(kart) == 0) {
								response.setContentType("text/html");
								PrintWriter out = response.getWriter();
								out.println("Error. Kart no modificado");
								RequestDispatcher error = request.getRequestDispatcher("/mvc/view/admin/ModifyStateKartView.jsp");
								error.include(request, response);
							} else {
								response.setContentType("text/html");
								PrintWriter out = response.getWriter();
								out.println("ModifyStateKart");
								RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
								correcto.include(request, response);
							}
						}
					}
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/admin/ModifyStateKartView.jsp");
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
