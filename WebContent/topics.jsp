<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Форум - Темы</title>
		<meta charset="utf-8">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/index.css" rel="stylesheet">
	</head>
<body>

	<div id="main">
	<jsp:include page="header.jsp" />
	<jsp:include page="nav.jsp" />
	<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}?${pageContext.request.queryString }"/>  
	<jsp:include page="/TopicServlet" />

	<div style="clear: both"></div>

	<div id="content">
		<table border="1px" id="central_table" class="b5radius">
			<th id="tb_section">Тема</th>
			<th id="tb_msg">Сообщения</th>
			<th id="tb_last_msg">Последнее сообщение</th>
				
				<c:forEach var="topic" items="${topics }">
				
				<tr>
					<td class="section_in_table">
						<a href="messages.jsp?id=${topic.id }">${topic.name }</a>
					</td>
					<td>${topic.msgCount }</td>
					<td>
						<ul style="list-style: none">
							<li id="last_msg_date">${ topic.lastMsgDate }</li>
							<li><a href="profiles.jsp?id=${topic.lastMsgUserId }">${topic.lastMsgUserName }</a>
						<a href=""><img src="images/icon_topic_latest.gif"></a>
							</li>
						</ul>
					</td>
				</tr>
				
				</c:forEach>

		</table>
	</div> <!--content-->

	<jsp:include page="footer.jsp" />

	<div id="wrap">
		<div id="login_window"> 
		<form action="post">
    		<img onclick="showLogin('none')" id="close_img" src="images/close.png">
    		<p>
    			Вход
    		</p>
    		<div style="clear: both"></div>
    		<input type="text" placeholder="Логин" name="login" class="login_input">
    		<input type="password" placeholder="********" name="password" class="login_input">
    		<input type="submit" value="Войти" id="login_btn" class="text16 b5radius">
			<div style="clear: both"></div>
		</form>
		</div>
 	</div>
</div> <!--maint -->

</body>

</html>
