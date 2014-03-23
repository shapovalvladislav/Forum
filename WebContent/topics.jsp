<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.util.Collection" %>
<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/topics.css">
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
					<a href="registerForm.htm" id="btn_register" class="b4radius button button_reg">Регистрация</a>
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
				<li id="section"><a href="#">ТОП 10</a>
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
		<table id="central_table">
				<th id="tb_topic">Темы</th>
				<th id="tb_msg">Сообщения</th>
				<th id="tb_last_msg">Последнее сообщение</th>
				
				<%
				Collection<Topic> lst = Test.getTopics( request.getParameter("id") );
				if (lst != null) {
				for (Topic topic : lst) {
				%>	
				
				<tr>
					<td class="section_in_table"><a href="messages.jsp?id=<%=topic.getId()%>"><%=topic.getName()%></a></td>
					<td>3232</td>
					<td>
						<ul style="list-style: none">
							<li id="last_msg_date"><%= topic.getLastChange() %></li>
							<li><a href="#">MegaNagibator</a>
							<a href=""><img src="images/icon_topic_latest.gif"></a>
							</li>
						</ul>
					</td>

				</tr>
				<%  }
				}
				%>
		
		</table>
		</div>

		<div id="footer" class="b4radius">
			
		</div>

		<div class="clr"></div>
</div>
</body>
</html>
