<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.util.Collection" %>
<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>

<% 
	String uri = request.getRequestURI();
	String pageName = uri.substring(uri.lastIndexOf("/")+1);
	session.setAttribute("previousPage", pageName); 
%>

<div id="page_align" class="b4radius">
		<div id="header">
			<div id="header_title">
				<img src="images/header_title.png">
			</div>

			<form method="post" action="login.jsp">
				<ul type="list-style: none" class="top_login">
					<li><input type="text" name="login"
					placeholder="Логин" class="b4radius login_style"></li>
					<br>
					<li><input type="text" name="password"
					placeholder="Пароль" class="b4radius login_style">
					</li>
					<li>
					<input type="submit" id="btn_login" class="b4radius button_login button" value="Вход">
					<a href="registerForm.jsp" id="btn_register" class="b4radius button button_reg">Регистрация</a>
					</li>
					<% if (session.getAttribute("user") != null) { %>
					<li>
					<%= session.getAttribute("user") %>
					</li>
					<li>
					<a href="logout.jsp">Log out</a>
					</li>
					<% } %>
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
						<% 
							Collection<Profile> topUsers = Test.getTopUsers();
							for (Profile p : topUsers) { %>	
						<li><a href="#"><%= p.getNickName() + " - " + p.getMsgCount() %></a>
					<% } %>
					</ul>
				</li>
			</ul>
		</div>
		<div id="content" class="b4radius">
		<table id="central_table">
				<th id="tb_section">Раздел</th>
				<th id="tb_topic">Темы</th>
				<th id="tb_msg">Сообщения</th>
				<th id="tb_last_msg">Последнее сообщение</th>

			<%
			Collection<Section> lst = Test.getSections();
			if (lst != null) {
			for (Section section : lst) {
			%>	
			<tr>
			<td class="section_in_table"><a href="topics.jsp?id=<%=section.getId()%>" > <%= section.getName() %> </a></td>
			<td>43</td>
			<td>3232</td>
			<td>
				<ul style="list-style: none">
					<li id="last_msg_date">22.02 21.02.14</li>
					<li><a href="#">MegaNagibator</a>
					<a href=""><img src="images/icon_topic_latest.gif"></a>
					</li>
				</ul>
			</td>
			</tr>
			<% 	}	
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
