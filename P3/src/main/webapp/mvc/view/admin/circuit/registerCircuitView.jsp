<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.ArrayList, pw.p3.business.circuit.*, pw.p3.data.Dificultad" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Pista</title>
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
	messageNextPage = "registerCircuitView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<form method="get" action="/P3/registerCircuit">
	<label for="nombre">Nombre: </label>
	<input type="text" name="nombre" value="" maxLength="30" required>
	<br/>
	<label for="estado">Estado: </label>
	<input type="radio" name="estado" value="true" checked>Reservado
	<input type="radio" name="estado" value="false">Disponible
	<br/>
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
</table>
<%
}
%>
</body>
</html>