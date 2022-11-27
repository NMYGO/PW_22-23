<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="pw.p3.business.user.UserDTO, pw.p3.data.*, pw.p3.data.dao.*, java.util.ArrayList, java.time.LocalDate, java.time.LocalTime" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<header>
	<h1>GESTOR DE KARTS</h1> 
</header>
<body>
	<% String messageNextPage = request.getParameter("message"); %>
	<%--Este código de reset es únicamente para poder probar múltiples veces el MVC--%>
	<% if (request.getParameter("reset") != null) { %>
	<jsp:setProperty property="correoUser" value="" name="customerBean"/>
	<% } %>
	
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
				<div>¡¡Bienvenido Administrador: <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
				<br/>				
				<%ArrayList<UserDTO> usuarios = new ArrayList<UserDTO>();%>
				<%UserDAO userDAO = new UserDAO();%>
				<%usuarios = userDAO.solicitarUsuarios();%>			
				<table>	
				<% for (int i = 0; i < usuarios.size(); i++) { %>
				<tr><td> 
				<%
				/**OTRA FORMA DE HACERLO SIN OUT:PRINTLN??**/
				out.println("Cliente: " + usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellidos() 
				+ ", con antiguedad " + usuarios.get(i).getAntiguedad() + " meses. Reservas completadas: ");
				%> 
				</td></tr>
				<% } %>
				</table>
		<% } else { %>
			<%= messageNextPage %><br/><br/>
			<%ReservationDAO reservationDAO = new ReservationDAO();%>
			<div>¡¡Bienvenido <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
			<div>Fecha actual: <%=LocalDate.now()%>, con hora: <%=LocalTime.now()%> </div>
			<div>Antigüedad: <jsp:getProperty property="antiguedadUser" name="customerBean"/> meses </div>			
			<div>Fecha de la proxima reserva: 
				<label><select id="tReserva" name="Tipo de Reserva">
							<option value="INFANTIL">Infantil >> <%= reservationDAO.solicitarProximaReservaInfantil(customerBean.getCorreoUser(), Dificultad.INFANTIL).toString() %></option>
							<option value="ADULTO">Adulto >> <%= reservationDAO.solicitarProximaReservaAdulto(customerBean.getCorreoUser(), Dificultad.ADULTO).toString() %></option>
							<option value="FAMILIAR">Familiar >> <%= reservationDAO.solicitarProximaReservaFamiliar(customerBean.getCorreoUser(), Dificultad.FAMILIAR).toString() %></option>
						</select></label></div>
		<% } %>
	<% } %>
</body>
</html>