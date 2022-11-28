<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="pw.p3.business.user.*, pw.p3.business.reservation.*, pw.p3.data.*, pw.p3.data.dao.*, java.util.ArrayList, java.time.LocalDate, java.time.LocalTime" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<%%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Menu Admin</title>
<link href= "<%= request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<header>
	<h1>GESTOR DE KARTS</h1> 
</header>
<body>
<% String messageNextPage = request.getParameter("message"); %>
	<% if(customerBean.getAdminUser()) { %>
		<div>¡¡Bienvenido Administrador: <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
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
		</table>
		<% } else { %>
			<%= messageNextPage %><br/><br/>
			<%ReservationDAO reservationDAO = new ReservationDAO();%>
			<table>
			<tr><td>¡¡Bienvenido <jsp:getProperty property="nombreUser" name="customerBean"/>!!</td></tr>
			<tr><td>Fecha actual: <%=LocalDate.now()%>, con hora: <%=LocalTime.now()%></td></tr>
			<tr><td>Antigüedad: <jsp:getProperty property="antiguedadUser" name="customerBean"/> meses</td></tr>
			<tr><td><label><select name="Tipo de Reserva">
				<option value="INFANTIL">Proxima Reserva Infantil >> <%= reservationDAO.solicitarProximaReservaInfantil(customerBean.getCorreoUser(), Dificultad.INFANTIL).getFecha() %></option>
				<option value="ADULTO">Proxima Reserva Adulto >> <%= reservationDAO.solicitarProximaReservaAdulto(customerBean.getCorreoUser(), Dificultad.ADULTO).getFecha() %></option>
				<option value="FAMILIAR">Proxima Reserva Familiar >> <%= reservationDAO.solicitarProximaReservaFamiliar(customerBean.getCorreoUser(), Dificultad.FAMILIAR).getFecha() %></option>
			</select></label></td></tr>
			</table>
		<% } %>
</body>
</html>