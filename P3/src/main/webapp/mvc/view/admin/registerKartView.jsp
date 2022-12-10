<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Kart</title>
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
	messageNextPage = "registerKartView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<%= messageNextPage %><br/><br/>
<form method="get" action="/P3/registerKart">
	<label for="id">ID: </label>
	<input type="number" name="id" value="" min="1" required>
	<br/>
	<label for="type">Tipo: </label>
	<input type="radio" name="tipo" value="true" checked>Infantil
	<input type="radio" name="tipo" value="false">Adulto
	<br/>
	<label for="estado">Estado: </label>
	<select name="estado" required>
		<option value="DISPONIBLE">Disponible</option>
		<option value="RESERVADO">Reservado</option>
		<option value="MANTENIMIENTO">Mantenimiento</option>
	</select>
	<br/><br/>
	<input type="submit" value="Registrar Kart">
</form>
<%
}
%>
</body>
</html>