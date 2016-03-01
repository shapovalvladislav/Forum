<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tagLib" prefix="ct"%>
				
	<ct:sections attrName="sections"/>
	<% 
		System.out.println(pageContext.getAttribute("sections"));
	%>