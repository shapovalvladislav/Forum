<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
			<script type="text/javascript">
      		function showLogin(state) {
      	  		document.getElementById('login_window').style.display = state;      
          		document.getElementById('wrap').style.display = state;	
      		} 
  		</script>

	<div id="header" class="b5radius">
		<img id="header_title" src="images/header_title.png">

		<div id="header_user">

			<img src="images/default_icon.png">
			<p>Вы вошли как:</p>
			<p>${sessionScope.loggedProfile.nickName }</p>
				<a href="/Forum/LogOutServlet" name="logout">Выйти</a>

		</div>

		<div id="registerBtn" class="head_btn b5radius">
			<a class="text16" href="/Forum/registerForm.jsp">Регистрация</a>
		</div>
		<div id="signInBtn" class="head_btn b5radius" onclick="showLogin('block')">
			<p class="text16" href="#">Вход</p>
		</div>

	</div> <!-- header -->
	
	<c:choose>
				<c:when test="${not empty sessionScope.loggedProfile }">
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