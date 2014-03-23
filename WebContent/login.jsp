<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%
String login = request.getParameter("login");
String password = request.getParameter("password");
System.out.println(login);
System.out.println(password);
if (Test.userExists(login, password) == true) {
	session.setAttribute("user", login);
	String previousePage = (String)session.getAttribute("previousPage");
	response.sendRedirect(previousePage);
}
%>