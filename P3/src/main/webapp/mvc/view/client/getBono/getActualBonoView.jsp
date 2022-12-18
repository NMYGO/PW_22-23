<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.ArrayList, pw.p3.business.reservation.*, pw.p3.data.Dificultad" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Elegir Bono</title>
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
	messageNextPage = "Elegir Bono a Usar";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<form method="get" action="/P3/getActualBono">
	<label for="id">ID bono:</label>
	<input type="number" name="id" value="" required>
	<label for="criterio"></label>
	<input type="hidden" name="criterio" value="actual">
	<br/><br/>
	<input type="submit" value="Elegir Bono">
</form>
<%
ArrayList<BonoDTO> bonos = (ArrayList<BonoDTO>)request.getAttribute("arrayBonos");
%>
<br/><br/>
<table>
	<tr>
	<th>ID</th>
	<th>SESIONES USADAS</th>
	<th>CADUCIDAD</th>
	<th>DIFICULTAD</th>
	</tr>
	<% for (int i = 0; i < bonos.size(); i++) { %>
	<tr>
		<td>
		<%= bonos.get(i).getId() %>
		</td>
		<td>
		<%= bonos.get(i).getSesion() %>
		</td>
		<td>
		<%= bonos.get(i).getFcaducidad() %>
		</td>
		<td>
		<%= bonos.get(i).getTipo() %>
		</td>
	</tr>
	<% } %>
</table>
<%
}
%>
</body>
</html>