<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page import="model.*" %>
<%@page import="java.io.*" %>
<%@page import="java.util.Collection" %>

<link rel="stylesheet" href="css/tinyeditor.css">
<script src="js/tiny.editor.packed.js"></script>

<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}?${pageContext.request.queryString }"/> 

<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/messages.css">
</head>
<body>
<div id="page_align" class="b4radius">
	<jsp:include page="header.jsp" />
	<jsp:include page="nav.jsp" />
	<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}?${pageContext.request.queryString }"/> 
	<jsp:include page="/MessageServlet?topic=${param.id }" />
	
	<div class="row-fluid ">
	<div class="span10 offset1 back_color">
		<div id="content" class="b4radius">

	
			<div class="message">
				<a class="topic_link" href="topics.jsp?id=">Раздел</a>
				<p class="topic_name" align="right">Тема </p>
			</div>


	<c:forEach var="message" items="${messages }">
		<div class="message">
				<div class="msgInfo">
				<div class="nick_name">
					<a href="#">${message.profile.nickName }</a>
				</div>
				<div  class="msg_icon">
					<img src="/Forum/DisplayIcon?id=${message.profile.id }">
				</div>
				<div align="left" class="msg_date">
					<p>${message.date }</p>
				</div>				
				
				</div>
				<div class="msg_content">
				${message.content }
				</div>



				<div align="right" class="msg_del">
					<a href="#">Удалить</a>
				</div>
			</div>
	</c:forEach>
			
				
				<c:if test="${not empty sessionScope.loggedProfile }">
				
				<div class="message msg_send">
				<div class="nick_name">
					<a href="#">${sessionScope.loggedProfile.nickName }</a>
				</div>
	  			<div  class="msg_icon">
					<img src="/Forum/DisplayIcon?id=${sessionScope.loggedProfile.id }">
				</div> 
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

		</div>
		
		</div>
		
		</div>

		<jsp:include page="footer.jsp" />

		<div class="clr"></div>

					
</div>
</body>
</html>
