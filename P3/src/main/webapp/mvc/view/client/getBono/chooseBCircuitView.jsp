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
<header>
	<a class="tuno" href="/P3/index.jsp">GESTOR DE KARTS</a>
</header>
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
	messageNextPage = "NewBonoReservation";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<form method="get" action="/P3/newBonoReservation">
	<label for="pista">Pista: </label>
	<input type="text" name="pista" value="" required>
	<br/>
	<label for="dificultad"></label>
	<input type="hidden" name="dificultad" value='<jsp:getProperty property="dificultad" name="reservaBean"/>'>
	<label for="adultos"></label>
	<input type="hidden" name="adultos" value='<jsp:getProperty property="adultos" name="reservaBean"/>'>
	<label for="ninos"></label>
	<input type="hidden" name="ninos" value='<jsp:getProperty property="ninos" name="reservaBean"/>'>
	<label for="duracion"></label>
	<input type="hidden" name="duracion" value='<jsp:getProperty property="duracion" name="reservaBean"/>'>
	<label for="fecha"></label>
	<input type="hidden" name="fecha" value='<jsp:getProperty property="fecha" name="reservaBean"/>'>
	<label for="id"></label>
	<input type="hidden" name="id" value='<jsp:getProperty property="bono" name="reservaBean"/>'>
	<input type="submit" value="Elegir pista">
	<br/><br/>
</form>


<%
CircuitManager circuitos = new CircuitManager();
Integer total = reservaBean.getNinos()+reservaBean.getAdultos();
String dificultad = reservaBean.getDificultad();
ArrayList<CircuitDTO> pistas = new ArrayList<CircuitDTO>();
String path = (String)request.getAttribute("path");
if(dificultad.equals("ADULTO")){
	pistas = circuitos.pistasLibres(path, reservaBean.getAdultos(), Dificultad.ADULTO);
}else if (dificultad.equals("INFANTIL")){
	pistas = circuitos.pistasLibres(path, reservaBean.getNinos(), Dificultad.INFANTIL);
}else{
	pistas = circuitos.pistasLibres(path, total, Dificultad.FAMILIAR);
}

%>
<br/><br/>
<table>
	<tr>
	<th>PISTA</th>
	</tr>
	<% for (int i = 0; i < pistas.size(); i++) { %>
	<tr>
		<td>
		<%= pistas.get(i).getNombre() %>
		</td>
	</tr>
	<% } %>
</table>
<%
}
%>
</body>
</html>