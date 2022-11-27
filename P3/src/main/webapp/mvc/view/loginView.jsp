<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acceder</title>
</head>
<body>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede del controlador /con mensaje 
		b) No hay parámetros en el request -> procede del controlador /sin mensaje
	*/
String nextPage = "../controller/loginController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) {
	messageNextPage = "loginView";
}

if (customerBean != null && !customerBean.getCorreoUser().equals("") && !customerBean.getPasswordUser().equalsIgnoreCase("")) {
	//No debería estar aquí -> flujo salta a index.jsp
	nextPage = "../../index.jsp";
} else { %>
<%= messageNextPage %><br/><br/>
<form method="post" action="../controller/loginController.jsp">
	<label for="correo">Correo: </label>
	<input type="email" name="correo" value="" placeholder="correo@dominio" required>
	<br/>
	<label for="password">Contraseña: </label>
	<input type="password" name="password" value="" required>	
	<br/><br/>
	<input type="submit" value="Acceder">
</form>
<%
}
%>
</body>
</html>