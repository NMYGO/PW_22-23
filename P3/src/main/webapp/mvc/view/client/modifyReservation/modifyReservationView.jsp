<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.ArrayList, pw.p3.business.circuit.*, pw.p3.data.Dificultad" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean  id="reservaBean" scope="session" class="pw.p3.display.javabean.ReservationBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nueva Reserva Individual</title>
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
	messageNextPage = "NewReservation";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<form method="get" action="/P3/modifyReservation">
	<label for="id"></label>
	<input type="hidden" name="id" value='<jsp:getProperty property="reserva" name="reservaBean"/>'>
	<label for="dificultad"></label>
	<input type="hidden" name="dificultad" value="">
	<br/>
	<label for="adultos">Numero de Adultos:</label>
	<input type="number" name="adultos" value="" min="1"> (No se tendrá en cuenta para reservas infantiles)
	<br/>
	<label for="ninos">Número de niños :</label>
	<input type="number" name="ninos" value="" min="1">(No se tendrá en cuenta para reservas adultas)
	<br/>
	<label for="duracion">Duracion: </label>
	<input type="radio" name="duracion" value="60">60 minutos
	<input type="radio" name="duracion" value="90">90 minutos
	<input type="radio" name="duracion" value="120">120 minutos
	<br/>
	<label for="fecha">Fecha:</label>
	<input type="date" name="fecha" value="">
	<br/>
	<br/>
	<input type="submit" value="Continuar">
</form>
<%
}
%>
</body>
</html>