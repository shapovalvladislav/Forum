<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>\

<jsp:include page="/TopUsersServlet" />

<c:forEach var="user" items="${topUsers }">
		<li>
					<div class="side_bar_user b4radius">
						<img class="side_bar_icon b4radius"
							src="/Forum/DisplayIcon?id=${user.id }">
					<ul>
							<li><a href="#">${user.nickName }</a></li>
							<li><em>Сообщений: ${user.msgCount }</em></li>
					</ul>
					</div>
		</li>
</c:forEach>
  