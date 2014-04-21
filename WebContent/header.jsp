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

		<div id="header_user" class="visible">

			<img src="images/default_icon.png">
			<p>Вы вошли как:</p>
			<p>NickName</p>
			<form>
				<input type="submit" name="logout" value="Выйти">
			</form>
		</div>

		<div class="head_btn b5radius invisible">
			<a class="text16" href="#">Регистрация</a>
		</div>
		<div class="head_btn b5radius invisible" onclick="showLogin('block')">
			<p class="text16" href="#">Вход</p>
		</div>

	</div> <!-- header -->

<!--   
<header>
<div class="row-fluid">
    <div class="span10 offset1">
        <div class="hero-unit header">
			<div class="row-fluid">
				<div class="span5">
					<img src="images/header_title.png">
				</div>
				<c:choose>
				<c:when test="${empty sessionScope.loggedProfile }">
				<div class="span1 offset6">
					<form method="post" action="/Forum/SignInServlet">
						<ul type="list-style: none" class="top_login">
						 	<li>
					<input type="text" name="login" placeholder="Логин" class="b4radius login_style">
						 	</li>
							<li>
								<input type="password" name="password" placeholder="Пароль" class="b4radius login_style">
							</li>
							<li>
								<div class="btn-group">
  									<button type="submit" class="btn btn_sing">Вход</button>
  									<a href="registerForm.jsp" class="btn btn_reg">Регистрация</a>
								</div>
							</li>
						</ul>
					</form>
				</div>								
				</c:when>
				<c:otherwise>
				
				<div class="span2 offset5" id="logged">
					<div id="icon"></div>
					<div>
					Вы вошли как: ${sessionScope.loggedProfile.nickName }
					</div>
					<div>
					<a href="/Forum/LogOutServlet">Log out</a>
					</div>
				</div>								
				</c:otherwise>
				</c:choose>

				
			</div>            			
          </div> <!-- header-->
	</div>
</div>
</header>
--> 