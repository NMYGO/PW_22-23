<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList, pw.p3.business.reservation.*" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reserva a eliminar</title>
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
	messageNextPage = "ConsultarReservasView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<%
ArrayList<RInfantileDTO> reservasInfantiles = (ArrayList<RInfantileDTO>)request.getAttribute("reservasInfantiles");
ArrayList<RAdultDTO> reservasAdultas = (ArrayList<RAdultDTO>)request.getAttribute("reservasAdultas");
ArrayList<RFamiliarDTO> reservasFamiliares = (ArrayList<RFamiliarDTO>)request.getAttribute("reservasFamiliares");
%>
<form method="get" action="/P3/modifyReservation">
	<label for="id">Id de reserva: </label>
	<input type="number" name="id" value="" required>
	<br/><br/>
	<input type="submit" value="Continuar">
</form>
<br/><br/>
<table>
	<tr>
	<th>ID</th>
	<th>FECHA</th>
	<th>PISTA</th>
	</tr>
	<tr><td colspan="3"><p>RESERVAS INFANTILES</p></td></tr>
	<% for (int i = 0; i < reservasInfantiles.size(); i++) { %>
	<tr>
		<%if(customerBean.getCorreoUser().equals(reservasInfantiles.get(i).getUsuario())){  %>
		<td>
		<%= reservasInfantiles.get(i).getIdReserva() %>
		</td>
		<td>
		<%= reservasInfantiles.get(i).getFecha() %>
		</td>
		<td>
		<%= reservasInfantiles.get(i).getPista() %>
		</td>
	</tr>
	<% }} %>
	<tr><td colspan="3"><p>RESERVAS ADULTAS</p></td></tr>
	<% for (int i = 0; i < reservasAdultas.size(); i++) { %>
	<tr>
		<%if(customerBean.getCorreoUser().equals(reservasAdultas.get(i).getUsuario())){  %>
		<td>
		<%= reservasAdultas.get(i).getIdReserva() %>
		</td>
		<td>
		<%= reservasAdultas.get(i).getFecha() %>
		</td>
		<td>
		<%= reservasAdultas.get(i).getPista() %>
		</td>
	</tr>
	<% }} %>
	<tr><td colspan="3"><p>RESERVAS FAMILIARES</p></td></tr>
	<% for (int i = 0; i < reservasFamiliares.size(); i++) { %>
	<tr>
		<%if(customerBean.getCorreoUser().equals(reservasFamiliares.get(i).getUsuario())){  %>
		<td>
		<%= reservasFamiliares.get(i).getIdReserva() %>
		</td>
		<td>
		<%= reservasFamiliares.get(i).getFecha() %>
		</td>
		<td>
		<%= reservasFamiliares.get(i).getPista() %>
		</td>
	</tr>
	<% }} %>
</table>
<%
}
%>
</body>
</html>