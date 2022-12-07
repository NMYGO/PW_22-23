<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="pw.p3.business.user.UserDTO, pw.p3.data.dao.UserDAO, java.time.LocalDate" %>
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
if (customerBean != null && !customerBean.getCorreoUser().equalsIgnoreCase("") && !customerBean.getPasswordUser().equalsIgnoreCase("")) {
	String nombreUser = request.getParameter("nombre");
	String apellidosUser = request.getParameter("apellidos");
	String nacimientoUser_string = request.getParameter("nacimiento");
	String contrasenaUser = request.getParameter("password");
	
	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (contrasenaUser != null) {
		if(!nombreUser.equalsIgnoreCase("") && !apellidosUser.equalsIgnoreCase("") &&
		!nacimientoUser_string.equalsIgnoreCase("") && !contrasenaUser.equalsIgnoreCase("")) {
			LocalDate nacimientoUser = LocalDate.parse(nacimientoUser_string);
			UserDAO userDAO = new UserDAO();
			UserDTO usuario = userDAO.solicitarUsuario(customerBean.getCorreoUser());
				usuario.setNombre(nombreUser);
				usuario.setApellidos(apellidosUser);
				usuario.setNacimiento(nacimientoUser);
				usuario.setPassword(contrasenaUser);
			//Se accede a bases de datos para actualizar el usuario
			//Se realizan todas las comprobaciones necesarias del dominio
			//Aquí sólo comprobamos que exista el usuario
			if(userDAO.escribirUsuarioUpdate(usuario) == 0) {
				// Usuario no modificado
				nextPage = "../view/modifyUserView.jsp";
				mensajeNextPage = "El usuario no es valido; " + usuario.toString();
			} else {
				// Usuario modificado
				mensajeNextPage = "El usuario se ha modificado correctamente";
			}
		}  else {
			nextPage = "../view/modifyUserView.jsp";
			mensajeNextPage = "Introduzca todos los datos necesarios";
		}
	//Caso 2.b -> se debe ir a la vista por primera vez
	} else {
		nextPage = "../view/modifyUserView.jsp";		
	}
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>