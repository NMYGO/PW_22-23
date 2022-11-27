<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="pw.p3.business.user.UserDTO, pw.p3.data.dao.UserDAO, java.util.ArrayList" %>
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
			<%--NO SE LLEGA AHORA MISMO AQUI PORQUE ESTOY REDIRIGIENDO A CLIENTMAINCONTROLLER DESDE LOGINCONTROLLER--%>
			<%= messageNextPage %><br/><br/>
			<div>¡¡Bienvenido: <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
		<% } %>
	<% } %>
</body>
</html>