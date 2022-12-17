package pw.p3.servlet.client.reservation;

import pw.p3.business.reservation.*;
import pw.p3.data.Dificultad;
import pw.p3.data.dao.ReservationDAO;
import pw.p3.display.javabean.CustomerBean;
import pw.p3.display.javabean.ReservationBean;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="NewReservation", urlPatterns="/newReservation")
public class NewReservation extends HttpServlet {
	
	/** Serial ID */
	private static final long serialVersionUID = -8869673328353849111L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		ReservationBean reservaBean = (ReservationBean)session.getAttribute("reservaBean");
		if (customerBean != null && customerBean.getCorreoUser() != "") {
			if(!customerBean.getAdminUser()) {
				String string_duracion = request.getParameter("duracion");
				String string_fecha = request.getParameter("fecha");
				String dificultad = request.getParameter("dificultad");
				String string_ninos = "0";
				String string_adultos = "0";
				if(dificultad != "ADULTO") {
				string_ninos = request.getParameter("ninos");
				}
				if(dificultad != "INFANTIL") {
				string_adultos = request.getParameter("adultos");
				}
				
				if (string_duracion != null && string_fecha != null && string_ninos != null && string_adultos != null && dificultad != null) {
					reservaBean.setAdultos(Integer.parseInt(string_adultos));
					reservaBean.setNinos(Integer.parseInt(string_ninos));
					reservaBean.setDuracion(Integer.parseInt(string_duracion));
					reservaBean.setFecha(LocalDate.parse(string_fecha));
					if(dificultad=="ADULTO") {
						reservaBean.setDificultad(Dificultad.ADULTO);
					}else if (dificultad=="INFANTIL") {
						reservaBean.setDificultad(Dificultad.INFANTIL);
					}else {
						reservaBean.setDificultad(Dificultad.FAMILIAR);
					}
					request.setAttribute("datosReserva", reservaBean);
					
					RequestDispatcher vista2 = request.getRequestDispatcher("/mvc/view/client/indivReservation/chooseCircuitView.jsp");
					vista2.forward(request, response);
				} else {
					RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/indivReservation/newReservationView.jsp");
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