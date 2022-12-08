<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href= "<%= request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet"/>
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
<form method="post" action="pw/p3/servlet/RegisterKart.java">
	<label for="id">ID: </label>
	<input type="number" name="id" value="" min="1" required>
	<br/>
	<label for="tipo">Tipo: </label>
	<input type="radio" name="tipo" value="true" checked>
	<input type="radio" name="tipo" value="false">
	<br/>
	<label for="estado">Estado: </label>
	<select name="estado">
		<option value="DISPONIBLE">Disponible</option>
		<option value="RESERVADO">Reservado</option>
		<option value="MANTENIMIENTO">Mantenimiento</option>
	</select>
	<br/>
	<label for="pista">Nombre de Pista: </label>
	<input type="text" name="pista" value="" maxLength="20" required>
	<br/><br/>
	<input type="submit" value="Registrar Kart">
</form>
<%
}
%>
</body>
</html>