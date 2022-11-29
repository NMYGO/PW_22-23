<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="pw.p3.business.user.*, pw.p3.business.reservation.*, pw.p3.data.*, pw.p3.data.dao.*, java.util.ArrayList, java.time.LocalDate, java.time.LocalTime" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean  id="auxiliaryBean" scope="session" class="pw.p3.display.javabean.AuxiliaryBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Pagina Principal</title>
<link href= "<%= request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<header>
	<h1>GESTOR DE KARTS</h1> 
</header>
<body>
	<% String messageNextPage = request.getParameter("message"); %>
	<%--Este código de reset es únicamente para poder probar múltiples veces el MVC
	<% if (request.getParameter("reset") != null) {
			request.getSession().removeAttribute("customerBean");
			request.getSession().removeAttribute("auxiliaryBean");
		} %> --%>
	
	<% if (customerBean == null || customerBean.getCorreoUser() == "") { %>
	<%--Usuario no registrado -> Se invoca al controlador de la funcionalidad--%>
	<a href="/P3/mvc/controller/registerController.jsp">Registrarse</a>		
	<% } %>
	<br/>
	<% if (customerBean == null || customerBean.getCorreoUser()=="") { %>
	<%--Usuario no logado -> Se invoca al controlador de la funcionalidad--%>
	<a href="/P3/mvc/controller/loginController.jsp">Acceder</a>
	<% } else { %>
		<% if(customerBean.getAdminUser()) { %>
				<%= messageNextPage %><br/><br/>
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
				+ ", con antiguedad " + customerBean.getAntiguedadUser() + " meses.");
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
					<table>
					<tr><td>¡¡Bienvenido <jsp:getProperty property="nombreUser" name="customerBean"/>!!</td></tr>
					<tr><td>Fecha actual: <%=LocalDate.now()%>, con hora: <%=LocalTime.now()%></td></tr>
					<tr><td>Antigüedad: <jsp:getProperty property="antiguedadUser" name="customerBean"/> meses</td></tr>
					<% if (auxiliaryBean.getFechaProximaReserva() ==  null) { %>
					<tr><td>Fecha de la proxima reserva (yyyy-mm-dd): No hay ninguna reserva proxima</td></tr>
					<% } else { %>
					<tr><td>Fecha de la proxima reserva (yyyy-mm-dd): <jsp:getProperty property="fechaProximaReserva" name="auxiliaryBean"/> => Reserva <jsp:getProperty property="dificultadProximaReserva" name="auxiliaryBean"/></td></tr>
					<% } %>
					</table>
				<% } %>
				<br/><br/><a href="/P3/mvc/controller/logoutController.jsp">Desconectarse</a>
			<% } %>
</body>
</html>