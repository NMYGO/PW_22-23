<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.ArrayList, java.time.LocalDate, pw.p3.business.reservation.*" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean  id="dateBean" scope="application" class="pw.p3.display.javabean.ReservationBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar reservas</title>
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
	messageNextPage = "ConsultarReservasView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("") ||
	request.getParameter("fechaFinal") == null || request.getParameter("fechaInicio") == null) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<%
LocalDate hoy = LocalDate.now();
LocalDate init = LocalDate.parse(request.getParameter("fechaInicio"));
LocalDate fin = LocalDate.parse(request.getParameter("fechaFinal"));

ArrayList<RInfantileDTO> reservasInfantiles = (ArrayList<RInfantileDTO>)request.getAttribute("reservasInfantiles");
ArrayList<RAdultDTO> reservasAdultas = (ArrayList<RAdultDTO>)request.getAttribute("reservasAdultas");
ArrayList<RFamiliarDTO> reservasFamiliares = (ArrayList<RFamiliarDTO>)request.getAttribute("reservasFamiliares");
%>
<br/><br/>
<table>
	<tr>
	<th>ID</th>
	<th>FECHA</th>
	<th>PISTA</th>
	<th>ESTADO</th>
	</tr>
	<tr><td colspan="4"><p>RESERVAS INFANTILES</p></td></tr>
	<% for (int i = 0; i < reservasInfantiles.size(); i++) { %>
	<tr>
		<%if(customerBean.getCorreoUser().equals(reservasInfantiles.get(i).getUsuario()) 
				&& reservasInfantiles.get(i).getFecha().isBefore(fin) && reservasInfantiles.get(i).getFecha().isAfter(init)){  %>
		<td>
		<%= reservasInfantiles.get(i).getIdReserva() %>
		</td>
		<td>
		<%= reservasInfantiles.get(i).getFecha() %>
		</td>
		<td>
		<%= reservasInfantiles.get(i).getPista() %>
		</td>
		<%if(reservasInfantiles.get(i).getFecha().isBefore(hoy)){ %>
			<td>FINALIZADA</td>
		<%}else{ %>
			<td>FUTURA</td>
		<%} %>
	</tr>
	<% }} %>
	<tr><td colspan="4"><p>RESERVAS ADULTAS</p></td></tr>
	<% for (int i = 0; i < reservasAdultas.size(); i++) { %>
	<tr>
		<%if(customerBean.getCorreoUser().equals(reservasAdultas.get(i).getUsuario())
				&& reservasAdultas.get(i).getFecha().isBefore(fin) && reservasAdultas.get(i).getFecha().isAfter(init)){  %>
		<td>
		<%= reservasAdultas.get(i).getIdReserva() %>
		</td>
		<td>
		<%= reservasAdultas.get(i).getFecha() %>
		</td>
		<td>
		<%= reservasAdultas.get(i).getPista() %>
		</td>
		<%if(reservasAdultas.get(i).getFecha().isBefore(hoy)){ %>
			<td>FINALIZADA</td>
		<%}else{ %>
			<td>FUTURA</td>
		<%} %>
	</tr>
	<% }} %>
	<tr><td colspan="4"><p>RESERVAS FAMILIARES</p></td></tr>
	<% for (int i = 0; i < reservasFamiliares.size(); i++) { %>
	<tr>
		<%if(customerBean.getCorreoUser().equals(reservasFamiliares.get(i).getUsuario())
				&& reservasFamiliares.get(i).getFecha().isBefore(fin) && reservasFamiliares.get(i).getFecha().isAfter(init)){  %>
		<td>
		<%= reservasFamiliares.get(i).getIdReserva() %>
		</td>
		<td>
		<%= reservasFamiliares.get(i).getFecha() %>
		</td>
		<td>
		<%= reservasFamiliares.get(i).getPista() %>
		</td>
		<%if(reservasFamiliares.get(i).getFecha().isBefore(hoy)){ %>
			<td>FINALIZADA</td>
		<%}else{ %>
			<td>FUTURA</td>
		<%} %>
	</tr>
	<% }} %>
</table>
<%
}
%>
</body>
</html>