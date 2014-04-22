<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page import="model.*" %>
<%@page import="java.io.*" %>
<%@page import="java.util.Collection" %>



<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}?${pageContext.request.queryString }"/> 


<!DOCTYPE html>
<html>
	<head>
		<title>Форум - Главная</title>
		<meta charset="utf-8">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/messages.css" rel="stylesheet">
		<link href="css/tinyeditor.css" rel="stylesheet">
		<script src="js/tiny.editor.packed.js"></script>
	</head>

	

<body>


<div id="main">
	<jsp:include page="header.jsp" />
	<jsp:include page="nav.jsp" />
	<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}?${pageContext.request.queryString }"/> 
	<jsp:include page="/MessageServlet?topic=${param.id }" />

	<div style="clear: both"></div>

	<div id="content" class="b5radius">

		<hr class="line">

		<div>
			<p id="topic">Teма:
			${ topicName }
			 </p>
			<p id="author">Создал:
			${ topicAutor }
			</p>
		</div>
		<div style="clear: both"></div>
		<hr class="line">


<c:forEach var="message" items="${ messages }">

	
		<div class="message">
			<div class="fleft">
				<img class="user_icon fleft" src="/Forum/DisplayIcon?id=${message.profile.id }">
				<a class="nick fleft ml_10" href="#">${message.profile.nickName }</a>
				<p class="ml_20 fleft nick">Дата: </p>
				<p class="ml_10 fleft nick">${ message.date }</p>
				<div class="fleft msg_content">
				${message.content }		
				</div> 
			</div>
	
			
			<div style="clear: both"></div>
		</div> <!-- message -->
 
</c:forEach>

		
<c:if test="${ not empty sessionScope.loggedProfile }">
				
				<div class="message msg_send">
					
				<div class="msg_send_content" >
					<form onsubmit="window.editor.post()" method="post" action="/Forum/AddMessageServlet">
				<textarea  name="msgContent"  id="tinyeditor" style="width: 100%; height: 200px"></textarea>
				<script>
				window.editor = new TINY.editor.edit('editor', {
					id: 'tinyeditor',
					width: '100%',
					height: 175,
					cssclass: 'tinyeditor',
					controlclass: 'tinyeditor-control',
					rowclass: 'tinyeditor-header',
					dividerclass: 'tinyeditor-divider',
					controls: ['bold', 'italic', 'underline', 'strikethrough', '|', 'subscript', 'superscript', '|',
						'orderedlist', 'unorderedlist', '|', 'outdent', 'indent', '|', 'leftalign',
						'centeralign', 'rightalign', 'blockjustify', '|', 'unformat', '|', 'undo', 'redo', 'n',
						'font', 'size', 'style', '|', 'image', 'hr', 'link', 'unlink', '|', 'print'],
					footer: false,
					fonts: ['Verdana','Arial','Georgia','Trebuchet MS'],
					xhtml: true,
					cssfile: 'custom.css',
					bodyid: 'editor',
					footerclass: 'tinyeditor-footer',
					resize: {cssclass: 'resize'}
				});
				</script>
			
						<input type="hidden" name="topic" value="${param.id }" />
						<div class="control-group">
					    <div class="controls">
					    	<button type="submit" class="btn btn-success">Отправить</button>
					    </div>
					  	</div>
					</form>
				</div>
				
				</div>
				</c:if>


		<div style="clear: both"></div>
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