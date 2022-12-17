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
ArrayList<CircuitDTO> pistasInfantiles = new ArrayList<CircuitDTO>();
ArrayList<CircuitDTO> pistasAdultos = new ArrayList<CircuitDTO>();
ArrayList<CircuitDTO> pistasFamiliares = new ArrayList<CircuitDTO>();
for (int i = 0; i < pistas.size(); i++) {
	if(pistas.get(i).getDificultad() == Dificultad.INFANTIL){
		pistasInfantiles.add(pistas.get(i));
	} else if(pistas.get(i).getDificultad() == Dificultad.ADULTO) {
		pistasAdultos.add(pistas.get(i));
	} else {
		pistasFamiliares.add(pistas.get(i));
	}
}
%>
<br/><br/>
<p>PISTAS EXISTENTES</p>
<table>
	<tr>
	<th>NOMBRE</th>
	<th>ESTADO</th>
	<th>MAXKARTS</th>
	</tr>
	<% if(pistasInfantiles.size() != 0) { %>
		<tr><td colspan="3"><p>PISTAS INFANTILES</p></td></tr>
		<% for (int i = 0; i < pistasInfantiles.size(); i++) { %>
		<tr>
			<td>
			<%= pistasInfantiles.get(i).getNombre() %>
			</td>
			<td>
			<%= pistasInfantiles.get(i).isEstado() %>
			</td>
			<td>
			<%= pistasInfantiles.get(i).getMaxkarts() %>
			</td>
		</tr>
		<% } %>
	<% } else { %>
	<tr><td colspan="3">NO HAY NINGUNA PISTA DISPONIBLE INFANTIL</td></tr>
	<% } %>
	<% if(pistasAdultos.size() != 0) { %>
		<tr><td colspan="3"><p>PISTAS ADULTOS</p></td></tr>
		<% for (int i = 0; i < pistasAdultos.size(); i++) { %>
		<tr>
			<td>
			<%= pistasAdultos.get(i).getNombre() %>
			</td>
			<td>
			<%= pistasAdultos.get(i).isEstado() %>
			</td>
			<td>
			<%= pistasAdultos.get(i).getMaxkarts() %>
			</td>
		</tr>
		<% } %>
	<% } else { %>
	<tr><td colspan="3">NO HAY NINGUNA PISTA DISPONIBLE ADULTA</td></tr>
	<% } %>
	<% if(pistasFamiliares.size() != 0) { %>
		<tr><td colspan="3"><p>PISTAS FAMILIARES</p></td></tr>
		<% for (int i = 0; i < pistasFamiliares.size(); i++) { %>
		<tr>
			<td>
			<%= pistasFamiliares.get(i).getNombre() %>
			</td>
			<td>
			<%= pistasFamiliares.get(i).isEstado() %>
			</td>
			<td>
			<%= pistasFamiliares.get(i).getMaxkarts() %>
			</td>
		</tr>
		<% } %>
	<% } else { %>
	<tr><td  colspan="3">NO HAY NINGUNA PISTA DISPONIBLE FAMILIAR</td></tr>
	<% } %>
</table>
</body>
</html>