<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.ArrayList, pw.p3.business.kart.*, pw.p3.business.circuit.*, pw.p3.data.*" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asociar Kart a Pista</title>
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
	messageNextPage = "KartToCircuitView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<%= messageNextPage %><br/><br/>
<form method="get" action="/P3/kartToCircuit">
	<label for="nombrePista">Nombre de la Pista: </label>
	<input type="text" name="nombrePista" value="" maxLength="30" required>
	<br/>
	<label for="idKart">ID del Kart: </label>
	<input type="number" name="idKart" value="" min="1" required>
	<br/><br/>
	<input type="submit" value="Asociar Kart a Pista">
</form>
<%
ArrayList<KartDTO> karts = (ArrayList<KartDTO>)request.getAttribute("karts");
ArrayList<KartDTO> kartsInfantiles = new ArrayList<KartDTO>();
ArrayList<KartDTO> kartsAdultos = new ArrayList<KartDTO>();
for (int i = 0; i < karts.size(); i++) {
	if(karts.get(i).isTipo() == true){
		kartsInfantiles.add(karts.get(i));
	} else {
		kartsAdultos.add(karts.get(i));
	}
}
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
<table>
	<tr>
	<th>ID</th>
	<th>ESTADO</th>
	<th>PISTA</th>
	</tr>
	<tr><td colspan="3"><p>KARTS INFANTILES</p></td></tr>
	<% for (int i = 0; i < kartsInfantiles.size(); i++) { %>
	<tr>
		<td>
		<%= kartsInfantiles.get(i).getId() %>
		</td>
		<td>
		<%= kartsInfantiles.get(i).getEstado() %>
		</td>
		<td>
		<%= kartsInfantiles.get(i).getNombrePista() %>
		</td>
	</tr>
	<% } %>
	<tr><td colspan="3"><p>KARTS ADULTOS</p></td></tr>
	<% for (int i = 0; i < kartsInfantiles.size(); i++) { %>
	<tr>
		<td>
		<%= kartsAdultos.get(i).getId() %>
		</td>
		<td>
		<%= kartsAdultos.get(i).getEstado() %>
		</td>
		<td>
		<%= kartsAdultos.get(i).getNombrePista() %>
		</td>
	</tr>
	<% } %>
</table>
<br/>
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