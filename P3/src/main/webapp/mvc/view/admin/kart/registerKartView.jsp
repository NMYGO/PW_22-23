<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.util.ArrayList, pw.p3.business.kart.*" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Kart</title>
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
	messageNextPage = "registerKartView";
}

if (customerBean == null || customerBean.getCorreoUser().equals("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else {
%>
<h2><%= messageNextPage %></h2><br/><br/>
<form method="get" action="/P3/registerKart">
	<label for="id">ID: </label>
	<input type="number" name="id" value="" min="1" required>
	<br/>
	<label for="tipo">Tipo: </label>
	<input type="radio" name="tipo" value="true" checked>Niño
	<input type="radio" name="tipo" value="false">Adulto
	<br>
	<label for="estado">Estado: </label>
	<select name="estado" required>
		<option value="DISPONIBLE">Disponible</option>
		<option value="MANTENIMIENTO">Mantenimiento</option>
	</select>
	<br/><br/>
	<input type="submit" value="Registrar Kart">
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
%>
<br/><br/>
<p>KARTS EXISTENTES</p>
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
	<% for (int i = 0; i < kartsAdultos.size(); i++) { %>
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
<%
}
%>
</body>
</html>