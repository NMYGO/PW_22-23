<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>
<jsp:useBean id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean  id="auxiliaryBean" scope="session" class="pw.p3.display.javabean.AuxiliaryBean"></jsp:useBean>

<%
String nextPage = "../../index.jsp";
String mensajeNextPage = "logoutController";
//Se comprueba primero que el usuario no está logado
if (customerBean == null || customerBean.getCorreoUser() == "") { %>

<% } else {
	request.getSession().removeAttribute("customerBean");
	request.getSession().removeAttribute("auxiliaryBean");
} %>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>