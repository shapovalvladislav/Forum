<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>\

<jsp:include page="/TopUsersServlet" />
<!DOCTYPE html>
<html>
	<head>
		<title>Форум - ТОП 10</title>
		<meta charset="utf-8">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/index.css" rel="stylesheet">
		<script type="text/javascript">
      		function showLogin(state) {
      	  		document.getElementById('login_window').style.display = state;      
          		document.getElementById('wrap').style.display = state;	
      		}
  		</script>
	</head>

	

<body>


<div id="main">
	<jsp:include page="header.jsp" />
	<jsp:include page="nav.jsp?page=top" />
	
	<div style="clear: both"></div>

	<div id="content">
		<table border="1px" id="central_table" class="b5radius">
			<th id="tb_section">Изображение</th>
			<th id="tb_section">Пользователь</th>
			<th id="tb_msg">Сообщения</th>
			<th id="tb_last_msg">Последнее сообщение</th>
				
				
				<jsp:include page="/TopUsersServlet" />

				<c:forEach var="user" items="${topUsers }">
					<tr>
					<td>
					<img src="/Forum/DisplayIcon?id=${user.id }">
					</td>
					<td class="section_in_table">
						<a href="topics.html">${user.nickName }</a>
					</td>
					<td>${user.msgCount }</td>
					<td>
						<ul style="list-style: none">
							<li id="last_msg_date">22.02 21.02.14</li>
							<li><a href="#"></a>
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