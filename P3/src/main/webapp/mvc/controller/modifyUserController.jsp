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
String nextPage = "./mainController.jsp";
String mensajeNextPage = "";
//Caso 2
if (customerBean != null && !customerBean.getCorreoUser().equalsIgnoreCase("") && !customerBean.getPasswordUser().equalsIgnoreCase("")) {
	String path = getServletContext().getRealPath("/WEB-INF/sql.properties.txt");
	String nombreUser = request.getParameter("nombre");
	String apellidosUser = request.getParameter("apellidos");
	String nacimientoUser_string = request.getParameter("nacimiento");
	String contrasenaUser = request.getParameter("password");
	
	//Caso 2.a: Hay parámetros -> procede de la VISTA
	if (contrasenaUser != null) {
		LocalDate nacimientoUser = LocalDate.MAX;
		UserDAO userDAO = new UserDAO();
		UserDTO usuario = userDAO.solicitarUsuario(path, customerBean.getCorreoUser());
		if(!nombreUser.equalsIgnoreCase("")){
			usuario.setNombre(nombreUser);
		}
		if(!apellidosUser.equalsIgnoreCase("")){
			usuario.setApellidos(apellidosUser);
			}
		if(!nacimientoUser_string.equalsIgnoreCase("")){
			nacimientoUser = LocalDate.parse(nacimientoUser_string);
			usuario.setNacimiento(nacimientoUser);
			}
		if(!contrasenaUser.equalsIgnoreCase("")){
			usuario.setPassword(contrasenaUser);
			}
			//Se accede a bases de datos para actualizar el usuario
			//Se realizan todas las comprobaciones necesarias del dominio
			//Aquí sólo comprobamos que exista el usuario
		if(!nacimientoUser_string.equalsIgnoreCase("")){
			if(nacimientoUser.isAfter(LocalDate.now())){
				nextPage = "../view/modifyUserView.jsp";
				mensajeNextPage = "La fecha no es valida;";
			}
		}
		if(userDAO.escribirUsuarioUpdate(path, usuario) == 0) {
			// Usuario no modificado
			nextPage = "../view/modifyUserView.jsp";
			mensajeNextPage = "El usuario no es valido; " + usuario.toString();
		} else {
			// Usuario modificado
			mensajeNextPage = "El usuario se ha modificado correctamente";
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