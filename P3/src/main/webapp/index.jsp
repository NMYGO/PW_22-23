<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="pw.p3.business.user.UserDTO, pw.p3.data.dao.UserDAO, java.time.LocalDate, java.time.LocalTime" %>
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
	<%--Este código de reset es únicamente para poder probar múltiples veces el MVC--%>
	<% if (request.getParameter("reset") != null) { %>
	<jsp:setProperty property="correoUser" value="" name="customerBean"/>
	<% } %>
	
	<% if (customerBean == null || customerBean.getCorreoUser() == "") { %>
	<a href="/P3/mvc/controller/registerController.jsp">Registrarse</a>		
	<% } %>
	<br/>
	<% if (customerBean == null || customerBean.getCorreoUser()=="") { %>
	<%--Usuario no logado -> Se invoca al controlador de la funcionalidad--%>
	<a href="/P3/mvc/controller/loginController.jsp">Acceder</a>
	<% } else { %>
		<% if(!customerBean.getAdminUser()) { %>
			<div>¡¡Bienvenido <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
			<div>Fecha actual: <%=LocalDate.now()%>, con hora: <%=LocalTime.now()%> </div>
			<div>Antigüedad: <jsp:getProperty property="antiguedadUser" name="customerBean"/> meses </div>
			<div>Fecha de la proxima reserva: </div>
		<% } else { %>
				<div>¡¡Bienvenido Administrador: <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
		<% } %>
	<% } %>
</body>
</html>