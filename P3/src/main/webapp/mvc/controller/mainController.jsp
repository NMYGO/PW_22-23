<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="pw.p3.business.user.*, pw.p3.business.reservation.*, pw.p3.data.*, pw.p3.data.dao.*, java.util.ArrayList, java.time.LocalDate, java.time.LocalTime" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean  id="auxiliaryBean" scope="session" class="pw.p3.display.javabean.AuxiliaryBean"></jsp:useBean>

<%
String nextPage = "../../index.jsp";
String mensajeNextPage = "mainController";
%>

<% if(customerBean.getAdminUser()) { %>
	<%-- <div>¡¡Bienvenido Administrador: <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
	<br/>				
	<%ArrayList<UserDTO> usuarios = new ArrayList<UserDTO>();%>
	<%ArrayList<RInfantileDTO> reservasInfantil = new ArrayList<RInfantileDTO>();%>
	<%ArrayList<RAdultDTO> reservasAdulto = new ArrayList<RAdultDTO>();%>
	<%ArrayList<RFamiliarDTO> reservasFamiliar = new ArrayList<RFamiliarDTO>();%>
	<%UserDAO userDAO = new UserDAO();%>
	<%ReservationDAO reservationDAO = new ReservationDAO();%>
	<%usuarios = userDAO.solicitarUsuarios();%>			
	<table>	
	<% for (int i = 0; i < usuarios.size(); i++) { %>
	<tr><td> 
	<%
	/**OTRA FORMA DE HACERLO SIN OUT:PRINTLN??**/
	out.println("Cliente: " + usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellidos() 
	+ ", con antiguedad " + usuarios.get(i).getAntiguedad() + " meses.");
	%> <br/>
		<% reservasInfantil = reservationDAO.solicitarReservasInfantilCompletada(usuarios.get(i).getCorreo(), Dificultad.INFANTIL);%>
		<% reservasAdulto = reservationDAO.solicitarReservasAdultoCompletada(usuarios.get(i).getCorreo(), Dificultad.ADULTO); %>
		<% reservasFamiliar = reservationDAO.solicitarReservasFamiliarCompletada(usuarios.get(i).getCorreo(), Dificultad.FAMILIAR); %>
		total = <%= reservasInfantil.size() + reservasAdulto.size() + reservasFamiliar.size()%>
		</br>
	</td></tr>
	<% } %>
	</table> --%>
<% } else { %>
	<% ReservationDAO reservationDAO = new ReservationDAO();
	LocalDate fechaInfantil = reservationDAO.solicitarProximaReservaInfantil(customerBean.getCorreoUser(), Dificultad.INFANTIL).getFecha();
	LocalDate fechaAdulto = reservationDAO.solicitarProximaReservaAdulto(customerBean.getCorreoUser(), Dificultad.ADULTO).getFecha();
	LocalDate fechaFamiliar = reservationDAO.solicitarProximaReservaFamiliar(customerBean.getCorreoUser(), Dificultad.FAMILIAR).getFecha(); %>
	<% if (fechaInfantil == null) { //Si alguna fecha es null le doy el maximo valor para que en la comparacion se obvie
		fechaInfantil = LocalDate.MAX;
	} 
	if (fechaAdulto == null) {
		fechaAdulto = LocalDate.MAX;
	}
	if (fechaFamiliar == null) {
		fechaFamiliar = LocalDate.MAX;
	} %>
	
	<% if (fechaInfantil.isBefore(fechaAdulto) && fechaInfantil.isBefore(fechaFamiliar)) { %>
			<jsp:setProperty property="dificultadProximaReserva" value="<%=Dificultad.INFANTIL%>" name="auxiliaryBean"/>
			<jsp:setProperty property="fechaProximaReserva" value="<%=fechaInfantil%>" name="auxiliaryBean"/>
	<% }else if (fechaAdulto.isBefore(fechaInfantil) && fechaAdulto.isBefore(fechaFamiliar)) { %>
			<jsp:setProperty property="dificultadProximaReserva" value="<%=Dificultad.ADULTO%>" name="auxiliaryBean"/>
			<jsp:setProperty property="fechaProximaReserva" value="<%=fechaAdulto%>" name="auxiliaryBean"/>
	<% }else if (fechaFamiliar.isBefore(fechaInfantil) && fechaFamiliar.isBefore(fechaAdulto)) { %>
			<jsp:setProperty property="dificultadProximaReserva" value="<%=Dificultad.FAMILIAR%>" name="auxiliaryBean"/>
			<jsp:setProperty property="fechaProximaReserva" value="<%=fechaFamiliar%>" name="auxiliaryBean"/>
	<% } %>
<% } %>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>