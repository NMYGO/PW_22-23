<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<header>
	<h1>KARTS</h1> 
	<%
	if (customerBean == null || customerBean.getEmailUser() == "") {
	%>
	<a href="/P3/mvc/controller/registerController.jsp">Registrarse</a>		
	<% } else { %>
	
	<% } %>
</header>
<body>
	<% 
	//Este código de reset es únicamente para poder probar múltiples veces el MVC
	if (request.getParameter("reset") != null) {
	%>
	<jsp:setProperty property="emailUser" value="" name="customerBean"/>
	<%
	}
	if (customerBean == null || customerBean.getEmailUser()=="") {
	// Usuario no logado -> Se invoca al controlador de la funcionalidad
	%>
	<a href="/P3/mvc/controller/loginController.jsp">Acceder</a>
	<% } else { %>
		<div>¡¡Bienvenido <jsp:getProperty property="emailUser" name="customerBean"/>!! </div>
	<% } %>
</body>
</html>