<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Форум - Регистрация</title>
		<meta charset="utf-8">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/registration.css" rel="stylesheet">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="js/jquery.ajaxfileupload.js"></script>
	
	
	<script type="text/javascript">

	
	(function($,W,D)
	{
	    var JQUERY4U = {};
	
	    JQUERY4U.UTIL =
	    {
	        setupFormValidation: function()
	        {
	            //form validation rules
	            $("#registerForm").validate({
	                rules: {
	                	fullName: "required",
	                	nickName: "required",
	                    login: {
	                    	required: true,
	                    	remote: "/Forum/UserExistsServlet"
	                    },
	                    password: "required",
	                    year: "required",
	                    datafile: {
	                    	accept: "png|jpe?g|gif"
	                    },
	                    email: {
	                        required: true,
	                        email: true
	                    }
	                },
	                messages: {
	                	fullName: "Введите имя",
	                	nickName: "Введите nickname",
	                    login: {
	                    	required: "Введите логин",
	                    	remote: "Пользователь с таким логином уже существует"
	                    },
	                    password: "Введите пароль",
	                    year: "Введите год рождения",
	                    datafile: {
	                   		accept: "Расширение должно быть: png | jpe?g | gif"
	                    },
	                    email: {
	                        required: "Введите электронную почту",
	                        email: "Введите корректный e-mail адрес"
	                    }
	                },
	                submitHandler: function(form) {
	                    form.submit();
	                }
	            });
	        }
	    }
	
	    //when the dom has loaded setup form validation rules
	    $(D).ready(function($) {
	        JQUERY4U.UTIL.setupFormValidation();
	    });
	
	})(jQuery, window, document);
	</script>
		
	<script type="text/javascript">

	$(document).ready(function(){	
		 $('#imageUpload').ajaxfileupload({
		      'action': '/Forum/UploadServlet',	      	    
		  'onComplete': function(response) {	        
				var str = "/Forum/uploads/" + document.getElementById("imageUpload").value;
				document.getElementById("icon_upload").src = str;
		        document.getElementById("icon_upload").height = 128;
		        document.getElementById("icon_upload").width = 128;
		        
		  }
		 });
	});
	</script>
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


	<div style="clear: both"></div>

<div id="content" class="b5radius">
	<p id="reg_title">РЕГИСТРАЦИЯ</p>
	<form id="registerForm" method="post" action="/Forum/RegisterServlet">
	    

		<div id="reg_div_image">
	    	<img id="reg_img" src="images/registration.jpg">
	    </div>


	    <div>
	    	<input type="text" class="inputs" id="inputName" name="fullName" placeholder="Имя">
	    </div>

	    <div>
	    	<input type="text" class="inputs"  id="inputNickname" name="nickName" placeholder="Nickname">
	    </div>

	    <div>
	    	<input type="text" class="inputs"  id="inputLogin" name="login" placeholder="Логин">
	    </div>

	    <div>
	      	<input type="password" class="inputs"  id="inputNickname" name="password" placeholder="Пароль">
		</div>
		
		<div>
	    	<input type="text" class="inputs"  id="inputEmail" name="email" placeholder="Email">
		</div>

		<div>
			<p id="p_birth">Дата рождения:</p>
		</div>

		<div>
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

		<div>
			<p id="p_birth">Пол:</p>
		</div>

		<div>
	      	<select id="inputSex" name="sex">
	      		<option value="male">M</option>
	     	 	<option value="female">Ж</option>
	     	</select>
	    </div>

	    <div>
	  		<textarea id="inputAbout" name="about" rows="3"></textarea>
	  	</div>

	  	<div>
	    	<img id="icon_upload" src="images/default_icon.png">
	    </div>
	    <div>
	    	<input type="file" id="imageUpload" name="datafile"> 
		</div>

		<div>
	    	<input id="bt_reg" type="submit" value="Зарегистрироваться">
	   </div>

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