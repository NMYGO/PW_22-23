<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.time.LocalDate, java.time.LocalTime" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina Principal</title>
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean no está logado (== null && == "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
String nextPage = "../controller/clientMainController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) {
	messageNextPage = "clientMainView";
} %>

<% if (customerBean == null || customerBean.getCorreoUser().equalsIgnoreCase("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	nextPage = "../../index.jsp";
} else { %>
<%= messageNextPage %><br/><br/>
	<div>¡¡Bienvenido <jsp:getProperty property="nombreUser" name="customerBean"/>!! </div>
	<div>Fecha actual: <%=LocalDate.now()%>, con hora: <%=LocalTime.now()%> </div>
	<div>Antigüedad: <jsp:getProperty property="antiguedadUser" name="customerBean"/> meses </div>
	<div>Fecha de la proxima reserva: </div>
<% } %>
</body>
</html>