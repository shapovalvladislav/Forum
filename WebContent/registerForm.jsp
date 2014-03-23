<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/registerForm.css">

<script src="js/jquery-1.8.2.js"></script>
<script src="js/jquery.ajaxfileupload.js"></script>
<script language="Javascript">
$(document).ready(function(){	
	 $('#imageUpload').ajaxfileupload({
	      'action': 'UploadServlet',	      	    
	  'onComplete': function(response) {	        
	        var str = "uploads/" + document.getElementById("imageUpload").value;
	        document.getElementById("icon").src = str;
	      },
	 });
});
</script>

</head>
<body>
<div id="page_align" class="b4radius">
		<div id="header">
			<div id="header_title">
				<img src="images/header_title.png">
			</div>

			<form method="" action="">
				<ul type="list-style: none" class="top_login">
					<li><input type="text" name="text_login"
					placeholder="Логин" class="b4radius login_style"></li>
					<br>
					<li><input type="text" name="text_pass"
					placeholder="Пароль" class="b4radius login_style">
					</li>
					<li>
					<button id="btn_login" class="b4radius button_login button">Вход</button>
					<a href="registerForm.html" id="btn_register" class="b4radius button button_reg">Регистрация</a>
					</li>

				</ul>
			</form>


			<div id="header_nav">
				<ul>
					<li><a href="index.html">ГЛАВНАЯ</a></li>
					<li><a href="#">РАЗДЕЛЫ</a></li>
					<li><a href="#">ТЕМЫ</a></li>
					<li><a href="#">ТОП 10</a></li>
					<li><a href="#">ПРОФИЛЬ</a></li>
				</ul>
			<form method="" action="">
			<a href="#"><img src="images/search.png" width="20px" height="20px"
			alt="Поиск по сайту" class="search_img"></a>
			<input type="text" name="header_search" placeholder="Поиск.." class="b4radius">

			 </form>
			</div>
		</div>

		<div id="sidebar" >
			<ul id="side_section">
				<li id="section"><a href="topics.html">ТОП 10</a>
					<ul id="sub_section">
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
						<li><a href="#">test</a>
					</ul>
				</li>
			</ul>
		</div>
		<div id="content" class="b4radius">
			 <div id="registrationForm">
 <form action="RegisterServlet" method="post">
 <h1 id="formHeader">Форма регистрации</h1>
 <br>
 <ul>
 <li>
 <label for="name">Имя</label>
 <input id="name" name="name" type="text">
 </li>
 <li>
 <label for="nickname">Nickname</label>
 <input id="nickname" name="nickname" type="text">
 </li>
 <li>
 <label for="login">Логин</label>
 <input id="login" name="login" ="login" type="text">
 </li>
 <li>
 <label for="password">Пароль</label>
 <input name="password" type="password">
 </li>
 <li>
 <label for="email">E-mail</label>
 <input id="email" name="email" type="text">
 </li>

<li>
<h4>Дата рождения</h4>

<div id="dayDiv" style="display:inline-block;">
<p>Число</p>
<select id="day" name="day">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
</select>
</div>

<div id="monthDiv" style="display:inline-block;">
<p>Месяц</p>
<select id="month" name="month">
<option value="1">Январь</option>
<option value="2">Февраль</option>
<option value="3">Март</option>
<option value="4">Апрель</option>
<option value="5">Май</option>
<option value="6">Июнь</option>
<option value="7">Июль</option>
<option value="8">Август</option>
<option value="9">Сентябрь</option>
<option value="10">Октябрь</option>
<option value="11">Ноябрь</option>
<option value="12">Декабрь</option>
</select>
</div>

<div id="yearDiv" style="display:inline-block;">
<p>Год</p>
<input id="year" type="year">
</select>
</div>

</li>
<li>
<p>Пол</p>
<select name="sex">
<option value="1">Мужской</option>
<option value="2">Женский</option>
</select>
</li>
<li>
<p>О себе</p>
<p><textarea rows="10" cols="45" name="about"></textarea></p>
</li>
<li>
<img id="icon">
<form>
    <input id="imageUpload" type="file" name="datafile" />
</form>
</li>
<br>
<li>
<input type="submit" value="Зарегистрироваться">
</li>
</ul>

 </form>
 </div>
		</div>

		<div id="footer" class="b4radius">
			
		</div>
		
		<div class="clr"></div>
</div>



</body>
</html>


