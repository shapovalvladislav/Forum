<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tagLib" prefix="ct"%>
<!DOCTYPE html>
<html>
<head>
<title>Форум - Главная</title>
<meta charset="utf-8">
<link href="css/style.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
</head>

<body>

	<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}" />

	<div id="main">

		<jsp:include page="header.jsp" />
		<jsp:include page="nav.jsp">
            <jsp:param name="page" value="index"/>
        </jsp:include>

		<c:if test="${ not empty sessionScope.loggedProfileId}">
			<c:if test="${not empty sessionScope.userRole && sessionScope.userRole == 'admin' }">
				<div style="clear: both"></div>
				<div class="add_btn b5radius visible" onclick="newSection('block')">
					<p class="text16">Добавить</p>
				</div>
			</c:if>
		</c:if>
		<div style="clear: both"></div>

		<div id="content">
			<table border="1px" id="central_table" class="b5radius">
				<th id="tb_section">Раздел</th>
				<th id="tb_topic">Темы</th>
				<th id="tb_msg">Сообщения</th>
				<th id="tb_last_msg">Последнее сообщение</th>

				<ct:sections attrName="sections" />
				<c:forEach var="section" items="${sections}">
					<tr>
						<td class="section_in_table">
						  <a href="topics.jsp?id=${section.id }">
						      ${ section.name }
						  </a>
						  <p>
							  Модератор:
							  <a href="profile.jsp?id=${section.moderatorId}">${section.moderatorNickName}</a>
						  </p>
						</td>
						<td>
						    ${section.topicCount}
						</td>
						<td>
						    ${section.msgCount}
						</td>
						<td>
							<ul style="list-style: none">
								<li id="last_msg_date">
								    ${section.lastMsgDate }
								</li>
								<li>
								    <a href="profile.jsp?id=${section.userId }">
								        ${section.userName }
								    </a>
									<a href="">
									   <img src="images/icon_topic_latest.gif">
									</a>
								</li>
							</ul>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<jsp:include page="footer.jsp" />

	</div>
</body>

</html>