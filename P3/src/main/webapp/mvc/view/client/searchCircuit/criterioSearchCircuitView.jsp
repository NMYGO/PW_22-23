<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.ArrayList, pw.p3.business.circuit.*, pw.p3.data.Dificultad" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Criterio Busqueda Pista Disponible</title>
<link href= "<%= request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
String nextPage = "../../index.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) {
	messageNextPage = "criterioSearchCircuitView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<form method="get" action="/P3/searchCircuit">
	<label for="criterio">Criterio: </label>
	<input type="radio" name="criterio" value="tipo" checked>Tipo de pista
	<input type="radio" name="criterio" value="nombre">Nombre
	<input type="radio" name="criterio" value="nkarts">Numero minimo de karts disponibles
	<br/><br/>
	<input type="submit" value="Establecer Criterio">
</form>
<%
}
%>
</body>
</html>