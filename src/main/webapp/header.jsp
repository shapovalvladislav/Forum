<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tagLib" prefix="ct"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<script type="text/javascript">

		function showLogin(state) {
		 	document.getElementById('login_window').style.display = state;
		  	document.getElementById('wrap').style.display = state;
		}

		function newSection(state) {
		 		document.getElementById('new_section_window').style.display = state;
		  		document.getElementById('wrap').style.display = state;
		}

		function newTopic(state) {
		 		document.getElementById('new_topic_window').style.display = state;
		  		document.getElementById('wrap').style.display = state;
		}

		function delOwnProfile(state) {
		 		document.getElementById('delete_window').style.display = state;
		  		document.getElementById('wrap').style.display = state;
		}

		function delUserProfile(state) {
		 		document.getElementById('delete_user_window').style.display = state;
		  		document.getElementById('wrap').style.display = state;
		}

 	</script>

	<div id="header" class="b5radius">
	<img id="header_title" src="images/header_title.png">
	<c:if test="${ not empty sessionScope.loggedProfileId }">
		<ct:loggedProfile profileId="${sessionScope.loggedProfileId}" />
		<div id="header_user">

			<img id="icon" src="/Forum/DisplayIcon?id=${sessionScope.loggedProfileId }">
			<p>Вы вошли как:</p>
			<p>${profile.nickName}</p>
			<a href="/Forum/LogOutServlet" name="logout">Выйти</a>
		</div>
	</c:if>

	<div id="registerBtn" class="head_btn b5radius">
		<a class="text16" href="/Forum/registerForm.jsp">Регистрация</a>
	</div>
	<div id="signInBtn" class="head_btn b5radius" onclick="showLogin('block')">
		<p class="text16" href="#">Вход</p>
	</div>

	</div>

	<c:choose>
		<c:when test="${not empty sessionScope.loggedProfileId}">
			<script type="text/javascript">
				document.getElementById("header_user").className = " visible";
				document.getElementById("signInBtn").className += " invisible";
				document.getElementById("registerBtn").className += " invisible";
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				document.getElementById("header_user").className = " invisible";
				document.getElementById("signInBtn").className += " visible";
				document.getElementById("registerBtn").className += " visible";
			</script>
		</c:otherwise>
	</c:choose>


	<div id="wrap">
		<div id="login_window">
		<form action="/Forum/SignInServlet" method="post">
    		<img onclick="showLogin('none')" id="close_img" src="images/close.png">
    		<p>Вход</p>
    		<div style="clear: both"></div>
    		<input type="text" placeholder="Логин" name="login" class="login_input">
    		<input type="password" placeholder="********" name="password" class="login_input">
    		<input type="submit" value="Войти" id="login_btn" class="text16 b5radius">
			<div style="clear: both"></div>
		</form>
		</div>
		<div id="new_section_window">
			<form method="post" action="/Forum/AddSectionServlet">
	    		<img onclick="newSection('none')" id="close_img" src="images/close.png">
	    		<p>Новый раздел</p>
	    		<div style="clear: both"></div>
	    		<input type="text" placeholder="Имя раздела" name="sectionName" class="login_input">
	    		<input type="text" placeholder="Краткое описание" name="sectionDescription" class="login_input">
	    		<input type="submit" value="Добавить" id="login_btn" class="text16 b5radius">
				<div style="clear: both"></div>
			</form>
		</div>

		<div id="new_topic_window">
		<form method="post" action="/Forum/AddTopicServlet">
    		<img onclick="newSection('none')" id="close_img" src="images/close.png">
    		<p>Новая тема</p>
    		<div style="clear: both"></div>
    		<input type="hidden" name="section" value="${param.id }" />
    		<input type="text" placeholder="Название темы" name="topicName" class="login_input">
    		<textarea id="new_topic_textarea" name="message" placeholder="Cooбщение"></textarea>
    		<input type="submit" value="Добавить" id="login_btn" class="text16 b5radius">
			<div style="clear: both"></div>
		</form>
		</div>
		<div id="delete_window">
		<form method="post" action="/Forum/DeleteOwnProfileServlet">
    		<img onclick="showDelete('none')" id="close_img" src="images/close.png">
    		<br>
    		<p id="del_title">Вы удаляете профиль. Уверены? Точно?</p>
    		<div style="clear: both"></div>
    		<input type="submit" value="Удалиться" id="yes_btn" class="dialog_del_btn fleft text16 b5radius">
    		<input type="reset" onclick="showDelete('none')" value="Отмена" class="dialog_del_btn text16 b5radius fleft">
			<div style="clear: both"></div>
		</form>
		<div style="clear: both"></div>
		</div>

		<div id="delete_user_window">
		<form method="post" action="/Forum/DeleteUserServlet">
    		<img onclick="showDelete('none')" id="close_img" src="images/close.png">
    		<br>
    		<p id="del_title">Вы удаляете пользователя. Уверены? Точно?</p>
    		<div style="clear: both"></div>
    		<input type="text" class="invisible" value="${param.id }" name="profileId">
    		<input type="submit" value="Удалиться" id="yes_btn" class="dialog_del_btn fleft text16 b5radius">
    		<input type="reset" onclick="showDelete('none')" value="Отмена" class="dialog_del_btn text16 b5radius fleft">
			<div style="clear: both"></div>
		</form>
		<div style="clear: both"></div>
		</div>

 	</div>