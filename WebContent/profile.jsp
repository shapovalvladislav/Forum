<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	

<body>


<div id="main">

	<jsp:include page="header.jsp" />

	<jsp:include page="nav.jsp" />

<div id="content" class="b5radius">
	<p id="user_title">Пользователь - Test</p>
	
	<img id="user_icon" src="images/default_icon.png">

	<div class="user_info">
		<p>NickName: Test</p>
	</div>

	<div class="user_info">
		<p>Имя: Test</p>
	</div>

	<div class="user_info">
		<p>E-Mail: Test</p>
	</div>


	<div class="user_info">
		<p>Дата рождения: Test</p>
	</div>


	<div class="user_info">
		<p>Пол: Test</p>
	</div>


	<div class="user_info">
		<p>Количество сообщений: Test</p>
	</div>


	<form action="" class="invisible">
		<input type="submit" value="Удалить пользователя">
	</form>


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