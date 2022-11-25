<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="pw.p3.business.user.UserDTO, pw.p3.data.dao.UserDAO" %>
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
	<% if (request.getParameter("reset") != null) {%>
	<jsp:setProperty property="emailUser" value="" name="customerBean"/>
	<% } %>
	
	<% if (customerBean == null || customerBean.getEmailUser() == "") { %>
	<a href="/P3/mvc/controller/registerController.jsp">Registrarse</a>		
	<% } %>
	<br/>
	<% if (customerBean == null || customerBean.getEmailUser()=="") { %>
	<%--Usuario no logado -> Se invoca al controlador de la funcionalidad--%>
	<a href="/P3/mvc/controller/loginController.jsp">Acceder</a>
	<% } else { %>
		<div>¡¡Bienvenido <jsp:getProperty property="emailUser" name="customerBean"/>!! </div>
	<% } %>
</body>
</html>