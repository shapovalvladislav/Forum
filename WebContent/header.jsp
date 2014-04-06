<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
  
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
				
				<div style="background-color: white;" class="span2 offset5" id="logged">
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
