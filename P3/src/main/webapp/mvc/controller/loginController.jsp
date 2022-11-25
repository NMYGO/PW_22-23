<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="pw.p3.business.user.UserDTO, pw.p3.data.dao.UserDAO" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
	2) customerBean no está logado:
		a) Hay parámetros en el request  -> procede de la vista 
		b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
	*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean == null || customerBean.getEmailUser().equalsIgnoreCase("")) {
	String emailUser = request.getParameter("email");
	String passwordUsser = request.getParameter("password");

	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (emailUser != null) {
		if(!emailUser.equalsIgnoreCase("") && !passwordUsser.equalsIgnoreCase("")) {
			//Se accede a bases de datos para obtener el usuario
			UserDAO userDAO = new UserDAO();
			UserDTO user = userDAO.solicitarUsuario(emailUser);
	
			//Se realizan todas las comprobaciones necesarias del dominio
			//Aquí sólo comprobamos que exista el usuario
			if (user != null && user.getCorreo().equalsIgnoreCase(emailUser)){
			// Usuario válido		
			%>
			<jsp:setProperty property="emailUser" value="<%=emailUser%>" name="customerBean"/>
			<%
			} else {
				// Usuario no válido
				nextPage = "../view/loginView.jsp";
				mensajeNextPage = "El usuario que ha indicado no existe o no es valido";
			}
		} else {
			nextPage = "../view/loginView.jsp";
			mensajeNextPage = "Introduzca el correo y la contraseña";
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../view/loginView.jsp";		
	}
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>