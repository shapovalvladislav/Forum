<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Форум - Профиль</title>
		<meta charset="utf-8">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/profile_user.css" rel="stylesheet">
		<script type="text/javascript">
      		function showLogin(state) {
      	  		document.getElementById('login_window').style.display = state;
          		document.getElementById('wrap').style.display = state;
      		}
  		</script>
	</head>

<c:if test="${param.id == sessionScope.loggedProfile.id }">
	<c:redirect url="ownProfile.jsp" />
</c:if>
<jsp:include page="/ProfileServlet?id=${param.id }"></jsp:include>

<body>


<div id="main">

	<jsp:include page="header.jsp" />

	<jsp:include page="nav.jsp" />
	    <jsp:param name="page" value="profile" />
    </jsp:include>

<div id="content" class="b5radius">


	<p id="user_title">Пользователь - ${profile.nickName }</p>

	<img id="user_icon" src="/Forum/DisplayIcon?id=${profile.id }">

	<div class="user_info">
		<p>Имя: ${profile.fullName }</p>
	</div>

	<div class="user_info">
		<p>E-Mail: ${profile.email }</p>
	</div>


	<div class="user_info">
		<p>Дата рождения: ${profile.birthDate }</p>
	</div>


	<div class="user_info">
		<p>Пол: ${profile.sex }</p>
	</div>

	<div class="user_info">
		<p>О себе: ${profile.about }</p>
	</div>

	<div class="user_info">
		<p>Количество тем: ${profile.topicCount }</p>
	</div>

	<div class="user_info">
		<p>Количество сообщений: ${profile.msgCount }</p>
	</div>

	<c:if test="${ not empty sessionScope.loggedProfileId }">
		<c:if test="${ sessionScope.userRole == 'admin' }">
		<form method="post" action="/Forum/DeleteUserServlet">
    		<input type="text" class="invisible" value="${param.id }" name="profileId">
    		<input type="submit" value="Удалить пользователя" id="yes_btn" class="dialog_del_btn fleft text16 b5radius">
    	<div style="clear: both"></div>
		</form>
		</c:if>
	</c:if>

	<c:if test="${ not empty sessionScope.loggedProfileId }">
		<c:if test="${ sessionScope.userRole == 'admin' }">
		<form method="post" action="/Forum/SetUserRoleServlet">
    		<input type="text" class="invisible" value="${param.id}" name="profileId">
    		<input type="text" class="invisible" value="1" name="userRole">
    		<input type="submit" value="Назначить админом" id="yes_btn" class="dialog_del_btn fleft text16 b5radius">
    	<div style="clear: both"></div>
		</form>
		<form method="post" action="/Forum/SetModeratorServlet">
    		<input type="text" class="invisible" value="${param.id}" name="profileId">
 			<p>Разделы:</p>
 			<select name="sectionId">
 			<c:forEach var="section" items="${sections}">
  				<option value="${section.id}">${section.name}</option>
			</c:forEach>
    		</select>
    		<input type="submit" value="Назначить модератором раздела" id="yes_btn" class="dialog_del_btn fleft text16 b5radius">
    	<div style="clear: both"></div>
		</form>
		</c:if>
	</c:if>

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

</div> <!--main -->

</body>

</html>