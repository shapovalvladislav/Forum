<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap-responsive.css" rel="stylesheet">

<script type='text/javascript'>
function scrollFunc(){
	var str = document.getElementById('central_table').offsetHeight;
	str -= 30;
	if (str < 693) {
		document.getElementById('scrollbar').style.height = str + 'px';
	} else {
		document.getElementById('scrollbar').style.height = '693px';
	}
    
	if (str < 440) {
		document.getElementById('scrollbar').style.height = '447px';
	}
}
</script>

</head>

<body onload='scrollFunc()'>
	
<jsp:include page="header.jsp" />

<jsp:include page="nav.jsp" />

<c:set var="prevPage" scope="session" value="${pageContext.request.requestURI}"/>  

<div class="row-fluid ">
	<div class="span8 offset1 back_color">
		<table border="1px" id="central_table" class="b4radius">
			<th id="tb_section">Раздел</th>
			<th id="tb_topic">Темы</th>
			<th id="tb_msg">Сообщения</th>
			<th id="tb_last_msg">Последнее сообщение</th>

			<jsp:include page="SectionServlet"></jsp:include>
			   
			   
			   <c:forEach var="section" items="${sections}">
			   
			   <tr>
					<td class="section_in_table">
						<a href="topics.jsp?id=${section.id }">${ section.name }</a>
					</td>
					<td>${section.topicCount }</td>

					<td>${section.msgCount }</td>
					
					<td>
						<ul style="list-style: none">
							<li id="last_msg_date">${ section.lastMsgDate }</li>
							<li><a href="profiles.jsp?id=${section.userId }">${section.userName }</a>
							</li>
						</ul>
					</td>
				</tr>
			   
			   </c:forEach>
			    	
		</table>

		
	</div>

	<div class="span2 b4radius" id="side_bar">
		<div>
			
			<ul class="nav">
   			   	<li><a class="btn" href="#">ТОП 10</a></li>
   			<div id="scrollbar" class="myscroll" >
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>

   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	<li>
   			   		<div class="side_bar_user b4radius">
   			   			<img class="side_bar_icon b4radius" src="images/default_icon.png">
   			   			<ul>
   			   				<li><a href="#">NickName</a></li>
   			   				<li><em>Сообщений: 1232</em></li>
   			   			</ul>
   			   		</div>
   			   	</li>
   			   	</div> <!-- scroll -->
   			</ul>
   			
		</div>
	</div>
	
	
<jsp:include page="footer.jsp" />


</div>	




</body>
</html>
    