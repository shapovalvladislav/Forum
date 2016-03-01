<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<div id="nav_bar" class="b5radius">
	<ul class="nav_text" id="nav">

	<jsp:include page="/NavigationServlet" />

	<li
		<c:if test="${ curPage == 'index' }">
		    class="active_nav"
		</c:if>
	>
	   <a href="/Forum/index.jsp">Главная</a>
	</li>
	<li
		<c:if test="${ curPage == 'top' }">
		    class="active_nav"
		</c:if>
	>
	    <a href="/Forum/topUsers.jsp">ТОП 10</a>
	</li>

	<c:if test="${ not empty sessionScope.loggedProfileId }">

		<li
			<c:if test="${ curPage == 'profile' }">
			    class="active_nav"
			</c:if>
		>
	    <a href="/Forum/ownProfile.jsp">Профиль</a>
		</li>

	</c:if>
    </ul>

	</div>