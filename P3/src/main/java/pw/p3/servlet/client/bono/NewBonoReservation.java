package pw.p3.servlet.client.bono;

import pw.p3.business.reservation.*;
import pw.p3.display.javabean.CustomerBean;
import pw.p3.display.javabean.ReservationBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="NewBonoReservation", urlPatterns="/newBonoReservation")
public class NewBonoReservation extends HttpServlet {
	
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
				if(request.getParameter("pista") == null) {
					
					if (string_duracion != null && string_fecha != null && string_ninos != null && string_adultos != null && dificultad != null) {
						reservaBean.setAdultos(Integer.parseInt(string_adultos));
						reservaBean.setNinos(Integer.parseInt(string_ninos));
						reservaBean.setDuracion(Integer.parseInt(string_duracion));
						reservaBean.setFecha(LocalDate.parse(string_fecha));
						reservaBean.setDificultad(dificultad);
						request.setAttribute("datosReserva", reservaBean);
						
						RequestDispatcher vista2 = request.getRequestDispatcher("/mvc/view/client/getBono/chooseBCircuitView.jsp");
						vista2.forward(request, response);
					} else {
						RequestDispatcher vista = request.getRequestDispatcher("/mvc/view/client/getBono/newBonoReservationView.jsp");
						vista.forward(request, response);
					}
				} else {
					String pista = request.getParameter("pista");
					Integer adultos = Integer.parseInt(string_adultos);
					Integer ninos = Integer.parseInt(string_ninos);
					Integer duracion = Integer.parseInt(string_duracion);
					LocalDate fecha = LocalDate.parse(string_fecha);
					Integer descuento=0;
					if(customerBean.getAntiguedadUser()>2) {
						descuento = 10;
					}
					
					ReservationManager reserva = new ReservationManager();
					if(!reserva.crearReserva(customerBean.getCorreoUser(), pista, dificultad, ninos, adultos, duracion, descuento, fecha)) {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Error. Reserva no creada");
						RequestDispatcher error = request.getRequestDispatcher("/mvc/view/client/getBono/newBonoReservationView.jsp");
						error.include(request, response);
					} else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.println("Reserva Creada");
						RequestDispatcher correcto = request.getRequestDispatcher("index.jsp");
						correcto.include(request, response);
					}
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