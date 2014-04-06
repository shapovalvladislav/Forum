<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Forum</title>
	<meta charset="utf-8">
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/registerForm.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
	
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery.ajaxfileupload.js"></script>
	<script language="javascript">
	
	
	function checkIfEmpty(form) {
		result = true;
		
		for (var i = 0; i < form.length; i++) {
			form.elements[i].style.border = "none";
			if (form.elements[i].type == "submit")
				continue;
			if (form.elements[i].value == null || form.elements[i].value == "") {
				form.elements[i].style.border = "1px solid red";
				result = false;
			}
		}
		return result;
	}
	
	function validateForm(form) {
		var empty = checkIfEmpty(form);
		return empty;
	}
	
	
	
	$(document).ready(function(){	
		 $('#imageUpload').ajaxfileupload({
		      'action': 'UploadServlet',	      	    
		  'onComplete': function(response) {	        
		        var str = "DisplayImage?path=/var/tmp/uploads/" + document.getElementById("imageUpload").value;
		        document.getElementById("icon").height = 128;
		        document.getElementById("icon").width = 128;
		        document.getElementById("icon").src = str;
		  },
		 });
	});
	</script>
	
</head>
<body>
<jsp:include page="header.jsp" />

<jsp:include page="nav.jsp" />


	<div class="row-fluid ">
	<div class="span10 offset1 back_color b4radius" id="inputForm">
	<div class="row-fluid ">
	<div class="span2 offset5" id="title">
	Форма регистрации
	</div>
	</div>	
	<form class="form-horizontal" method="post"  onsubmit="return validateForm(this)" action="/Forum/RegisterServlet">
	  
	  <div class="control-group">
	    <label class="control-label" for="inputName">Имя</label>
	    <div class="controls">
	      <input type="text" id="inputName" name="fullName" placeholder="Имя">
	    </div>
	  </div>   
	     
	  <div class="control-group">
	    <label class="control-label" for="inputNickname">Nickname</label>
	    <div class="controls">
	      <input type="text" id="inputNickname" name="nickName" placeholder="Nickname">
	    </div>
	  </div>

	  <div class="control-group">
	  	<label class="control-label" for="inputLogin">Логин</label>
	    <div class="controls">
	      <input type="text" id="inputLogin" name="login" placeholder="Логин">
	    </div>
	  </div>
	  
	  <div class="control-group">
	  	<label class="control-label" for="inputPassword">Пароль</label>
	    <div class="controls">
	      <input type="password" id="inputNickname" name="password" placeholder="Пароль">
	    </div>
	  </div>

	  <div class="control-group">
	  	<label class="control-label" for="inputEmail">E-mail</label>
	    <div class="controls">
	      <input type="text" id="inputEmail" name="email" placeholder="Email">
	    </div>
	  </div>

	  <div class="control-group">
		  <label class="control-label" id="inputDateLabel">Дата рождения</label>
		  <div class="controls">
		 
			 <div class="form-inline">
				
				<script type="text/javascript">
				document.write("<select id=\"day\" name=\"day\">");
				for (var i=1; i<=31; i++)
				{ 
					document.write("<option value=\"" + i + "\">" + i + "</option>");
				}
				document.write("</select>");
				document.write("<select id=\"month\" name=\"month\">");
				var months=["Январь","Февраль","Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"];
				for (var i=0; i<months.length; i++)
					document.write("<option value=\"" + i + "\">" + months[i] + "</option>");
				document.write("</select>");
				</script>
				<input id="year" name="year" type="year">
			</div>
		</div>
	</div>
	  
	  <div class="control-group">
	  	<label class="control-label" for="inputSex">Пол</label>
	    <div class="controls">
	      <select id="inputSex" name="sex">
	      <option value="male">M</option>
	      <option value="female">Ж</option>
	  	  </select>
	    </div>
	  </div>

	  <label class="control-label" id="inputAboutLabel" for="inputAbout">О себе</label>
	  <textarea id="inputAbout" class="form-control" name="about" rows="3"></textarea>

	  	<div class="control-group">
	  	<label class="control-label" for="inputImage">Фото</label>
	    <div class="controls">
	    	<img id="icon" />
	    	<input type="file" id="imageUpload" name="datafile"> 
	    </div>
	  </div>

	  	<div class="control-group">
	    <div class="controls">
	    	<button type="submit" class="btn btn-success">Зарегистрироваться</button>
	    </div>
	  </div>
	  
	</form>

	</div>
	</div>

	<jsp:include page="footer.jsp" />

	<div class="clr"></div>
	
	</div>
	</body>
	</html>
