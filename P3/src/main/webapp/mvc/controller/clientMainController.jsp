<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<%
/* Posibles flujos:
	1) customerBean no está logado -> Se redirige al index.jsp
	2) customerBean está logado (!= null && != "") -> Se redirige al clientMainView.jsp
	*/
//Caso 1: Por defecto, vuelve al index
String nextPage = "../../index.jsp";
String mensajeNextPage = "clientMainController";
//Caso 2
if (customerBean != null && !customerBean.getCorreoUser().equalsIgnoreCase("") && !customerBean.getPasswordUser().equalsIgnoreCase("")) {
	nextPage = "../view/clientMainView.jsp";
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>