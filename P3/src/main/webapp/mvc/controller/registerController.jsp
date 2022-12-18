<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="pw.p3.business.user.UserDTO, pw.p3.data.dao.UserDAO, java.time.LocalDate" %>
<%@ page errorPage="errorPage.jsp" %>
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
if (customerBean == null || customerBean.getCorreoUser().equalsIgnoreCase("") || customerBean.getPasswordUser().equalsIgnoreCase("")) {
	String nombreUser = request.getParameter("nombre");
	String apellidosUser = request.getParameter("apellidos");
	String nacimientoUser_string = request.getParameter("nacimiento");
	String correoUser = request.getParameter("correo");
	String contrasenaUser = request.getParameter("password");
	
	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (correoUser != null) {
		if(!nombreUser.equalsIgnoreCase("") && !apellidosUser.equalsIgnoreCase("") &&
			!nacimientoUser_string.equalsIgnoreCase("") && !correoUser.equalsIgnoreCase("") &&
			!contrasenaUser.equalsIgnoreCase("")) {
			String path = getServletContext().getRealPath("/WEB-INF/sql.properties.txt");
			LocalDate nacimientoUser = LocalDate.parse(nacimientoUser_string);
			UserDTO usuario = new UserDTO(nombreUser, apellidosUser, nacimientoUser, correoUser, contrasenaUser);
			//Se accede a bases de datos para insertar el usuario
			UserDAO userDAO = new UserDAO();
			//Se realizan todas las comprobaciones necesarias del dominio
			//Aquí sólo comprobamos que no exista el usuario
			if(userDAO.escribirUsuarioInsert(path, usuario) == 0) {
				// Usuario no válido
				nextPage = "../view/registerView.jsp";
				mensajeNextPage = "El usuario no es valido";
			} else {
				// Usuario registrado
				nextPage = "../view/loginView.jsp";
				mensajeNextPage = "El usuario se ha registrado correctamente";
			}
		} else {
			nextPage = "../view/registerView.jsp";
			mensajeNextPage = "Introduzca todos los datos necesarios";
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../view/registerView.jsp";		
	}
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>