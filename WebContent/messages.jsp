<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.io.*" %>
<%@page import="java.util.Collection" %>


<% 
	String uri = request.getRequestURI() + "?" + request.getQueryString();
	String pageName = uri.substring(uri.lastIndexOf("/")+1);
	System.out.println(pageName);
	session.setAttribute("topic", request.getParameter("id"));
	session.setAttribute("previousPage", pageName); 
%>

<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/messages.css">
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

			<div class="message">
				<a class="topic_link" href="topics.html">Раздел ----</a>
				<p class="topic_name" align="right">Тема ----</p>
			</div>

			<%
			Collection<Message> lst = Test.getMessages( request.getParameter("id") );
			if (lst != null) {
			for (Message msg : lst) {
			%>	

			<div class="message">
				<div class="nick_name">
					<a href="#"><%=msg.getProfile().getNickName() %></a>
				</div>
				<div  class="msg_icon">
					<img src="">
				</div>
				<div class="msg_content">
					<%=msg.getContent() %>
				</div>

				<div align="left" class="msg_date">
					<p><%=msg.getDate() %></p>
				</div>

				<div align="right" class="msg_del">
					<a href="#">Удалить</a>
				</div>
			</div>


			<%
			}
			}
			%>

			<% if (session.getAttribute("user") != null) { %>
				<div class="message msg_send">
				<div class="nick_name">
					<a href="#"><%=session.getAttribute("user")%></a>
				</div>
				<div  class="msg_icon">
					<img src="<%= Test.getIcon((String) session.getAttribute("user"), request.getRealPath("/")) %>">
				</div>
				<div class="msg_send_content" >
					<form method="post" action="AddMessageServlet">
						<textarea class="input_msg" name="content"></textarea>
				</div>

				<div>
					
						<input class="btn_msg button_reg button" type="submit" value="Отправить">
					</form>
				</div>
	
			</div>
			
			<% } %>
			
			
			


		
		</div>

		<div id="footer" class="b4radius">
			
		</div>

		<div class="clr"></div>
</div>
</body>
</html>
