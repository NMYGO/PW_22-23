<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="pw.p3.business.user.*, pw.p3.data.dao.UserDAO, java.time.LocalDate, java.time.format.DateTimeFormatter" %>
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
//mensajeNextPage = "loginController";
//Caso 2
if (customerBean == null || customerBean.getCorreoUser().equalsIgnoreCase("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	String correoUser = request.getParameter("correo");
	String passwordUser = request.getParameter("password");

	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (correoUser != null) {
		if(!correoUser.equalsIgnoreCase("") && !passwordUser.equalsIgnoreCase("")) {
			//Se accede a bases de datos para obtener el usuario
			String path = getServletContext().getRealPath("/WEB-INF/sql.properties.txt");
			UserDAO userDAO = new UserDAO();
			UserDTO usuario = userDAO.solicitarUsuario(path, correoUser);
			
			//Se realizan todas las comprobaciones necesarias del dominio
			//Aquí sólo comprobamos que exista el usuario
			if (usuario != null && usuario.getCorreo().equalsIgnoreCase(correoUser) && usuario.getPassword().equalsIgnoreCase(passwordUser)) {
			// Usuario válido	
			UserManager userManager = new UserManager();
			
			%>
			<jsp:setProperty property="nombreUser" value="<%=usuario.getNombre()%>" name="customerBean"/>
			<jsp:setProperty property="antiguedadUser" value="<%=userManager.calcularAntiguedad(usuario)%>" name="customerBean"/>
			<jsp:setProperty property="correoUser" value="<%=usuario.getCorreo()%>" name="customerBean"/>
			<jsp:setProperty property="passwordUser" value="<%=passwordUser%>" name="customerBean"/>
			<jsp:setProperty property="adminUser" value="<%=usuario.getAdministrador()%>" name="customerBean"/>
			<%
			nextPage = "../controller/mainController.jsp";
			} else {
				// Usuario no válido
				nextPage = "../view/loginView.jsp";
				mensajeNextPage = "El usuario no existe o no es valido";
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