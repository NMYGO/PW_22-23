<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import ="java.util.ArrayList, pw.p3.business.circuit.*, pw.p3.data.Dificultad" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pistas Disponibles</title>
<link href= "<%= request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%
ArrayList<CircuitDTO> pistas = (ArrayList<CircuitDTO>)request.getAttribute("pistas");
%>
<br/><br/>
<p>PISTAS EXISTENTES</p>
<% if (pistas.size() != 0) { %>
<table>
	<tr>
	<th>NOMBRE</th>
	<th>ESTADO</th>
	<th>MAXKARTS</th>
	</tr>
	<tr><td colspan="3"><p>TIPO <%= pistas.get(0).getDificultad().toString() %></p></td></tr>
	<% for (int i = 0; i < pistas.size(); i++) { %>
	<tr>
		<td>
		<%= pistas.get(i).getNombre() %>
		</td>
		<td>
		<%= pistas.get(i).isEstado() %>
		</td>
		<td>
		<%= pistas.get(i).getMaxkarts() %>
		</td>
	</tr>
	<% } %>
</table>
<% } else { %>
	<p>NO HAY NINGUNA PISTA DISPONIBLE</p>
<% } %>
</body>
</html>