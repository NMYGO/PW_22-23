<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Circuit</title>
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
	messageNextPage = "registerCircuitView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<%= messageNextPage %><br/><br/>
<form method="get" action="/P3/registerCircuit">
	<label for="nombre">Nombre: </label>
	<input type="text" name="nombre" value="" maxLength="30" required>
	<br/>
	<label for="estado">Estado: </label>
	<input type="radio" name="estado" value="true" checked>Reservado
	<input type="radio" name="estado" value="false">Disponible
	<br/>
	<label for="dificultad">Dificultad: </label>
	<select name="dificultad" required>
		<option value="INFANTIL">Infantil</option>
		<option value="ADULTO">Adulto</option>
		<option value="FAMILIAR">Familiar</option>
	</select>
	<br/>
	<label for="maxkarts">Maximo de Karts: </label>
	<input type="number" name="maxkarts" value="" min="1" required>
	<br/><br/>
	<input type="submit" value="Registrar Pista">
</form>
<%
}
%>
</body>
</html>