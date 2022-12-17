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
<% if (customerBean == null || customerBean.getCorreoUser() == "") { %>
	<header>
		<h1>GESTOR DE KARTS</h1> 
	</header>
<% } else {%>
	<header>
		<h1>GESTOR DE KARTS</h1>
		<nav>
		<a href="/P3/mvc/controller/logoutController.jsp">Desconectarse</a>
		<a href="/P3/mvc/controller/modifyUserController.jsp">Modificar Datos</a>
		</nav>
		
	</header>
<% } %>
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
				<div>¡¡Bienvenido Administrador: <jsp:getProperty property="nombreUser" name="customerBean"/>!!</div>
				<table>
				<tr>
				<th>CLIENTE</th>
				<th>ANTIGUEDAD</th>
				<th>Nº RESERVAS</th>
				</tr>
				<% for (int i = 0; i < auxiliaryBean.getUsuarios().size(); i++) { %>
				<tr>
					<td>
					<%= auxiliaryBean.getUsuarios().get(i).getNombre() %> <%= auxiliaryBean.getUsuarios().get(i).getApellidos() %>
					</td>
					<td>
					<%= auxiliaryBean.getAntiguedades().get(i) %> meses
					</td>
					<td>
					<%= auxiliaryBean.getNreservas().get(i) %>
					</td>
				</tr>
				<% } %>
				</table>
				<div>
				<a href="/P3/registerKart">Registrar Kart</a>
				<a href="/P3/registerCircuit">Registrar Pista</a>
				<a href="/P3/kartToCircuit">Asociar Kart a Pista</a>
				<a href="/P3/modifyStateKart">Modificar Estado de Kart</a>
				<a href="/P3/modifyStateCircuit">Modificar Estado de Pista</a>
				<a href="/P3/deleteReservation">Borrar Reserva Pendiente</a>
				</div>
		<% } else { %>
			<%= messageNextPage %><br/><br/>
			<div>¡¡Bienvenido <jsp:getProperty property="nombreUser" name="customerBean"/>!!</div>
			<table>
			<tr><td>Fecha actual: <%=LocalDate.now()%>, con hora: <%=LocalTime.now()%></td></tr>
			<tr><td>Antigüedad: <jsp:getProperty property="antiguedadUser" name="customerBean"/> meses</td></tr>
			<% if (auxiliaryBean.getFechaProximaReserva() ==  null) { %>
			<tr><td>Fecha de la proxima reserva (yyyy-mm-dd): No hay ninguna reserva proxima</td></tr>
			<% } else { %>
			<tr><td>Fecha de la proxima reserva (yyyy-mm-dd): <jsp:getProperty property="fechaProximaReserva" name="auxiliaryBean"/> => Reserva <jsp:getProperty property="dificultadProximaReserva" name="auxiliaryBean"/></td></tr>
			<% } %>
			</table>
			<div>
			<a href="/P3/consultReservation">Consultar Reservas</a>
			<a href="/P3/searchCircuit">Buscar Pistas</a>
			<a href="/P3/newReservation">Nueva Reserva Individual</a>
			</div>
		<% } %>
	<% } %>
</body>
</html>