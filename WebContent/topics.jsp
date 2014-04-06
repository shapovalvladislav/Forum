<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/topics.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp" />

<jsp:include page="nav.jsp" />
<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}?${pageContext.request.queryString }"/>  
<jsp:include page="/TopicServlet" />

	<div class="row-fluid ">
	<div class="span10 offset1 back_color">
		<table border="1px" id="central_table" class="b4radius">
			<th id="tb_topic">Название</th>
			<th id="tb_msg">Количество сообщений</th>
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
							</li>
						</ul>					
					</td>
				</tr>
				
				</c:forEach>

		</table>
	</div>

</div>	

<jsp:include page="footer.jsp" />

		<div class="clr"></div>
</div>
</body>
</html>

