<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tagLib" prefix="ct"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Форум - Профиль</title>
		<meta charset="utf-8">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/registration.css" rel="stylesheet">
		<link href="css/delete_dialog.css" rel="stylesheet">
		<script type="text/javascript">
      		function showLogin(state) {
      	  		document.getElementById('login_window').style.display = state;
          		document.getElementById('wrap').style.display = state;
      		}

      		function showDelete(state) {
      	  		document.getElementById('delete_window').style.display = state;
          		document.getElementById('wrap').style.display = state;
      		}

  		</script>
	</head>

<body>

<ct:loggedProfile profileId="${sessionScope.loggedProfileId }" />
<div id="main">

	<jsp:include page="header.jsp" />

	<jsp:include page="nav.jsp">
	   <jsp:param name="page" value="profile" />
	</jsp:include>




<div id="content" class="b5radius">
	<p id="reg_title">Мой профиль</p>
	<form method="post" action="/Forum/UpdateOwnProfileServlet">


		<div id="reg_div_image">
	    	<img id="reg_img" src="images/registration.jpg">
	    </div>


	    <div>
	    	<input type="text" class="inputs" id="inputName" name="fullName" value="${profile.fullName }" placeholder="Имя">
	    </div>

	    <div>
	    	<input type="text" class="inputs"  id="inputNickname" value="${profile.nickName }" name="nickName" placeholder="Nickname">
	    </div>

	    <div>
	    	<input type="text" class="inputs" readonly id="inputLogin" name="login" value="${ login }">
	    </div>


	    <div>
	      	<input type="password" class="inputs"  id="inputNickname" name="old_password" placeholder="Старый пароль">
		</div>

		<div>
	      	<input type="password" class="inputs"  id="inputNickname" name="new_password" placeholder="Новый пароль">
		</div>

		<div>
	    	<input type="text" class="inputs"  id="inputEmail" name="email" value="${profile.email }" placeholder="Email">
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

				document.getElementById("day").value = ${profile.birthDate.day };
				document.getElementById("month").value = ${profile.birthDate.month };
				</script>
				<input id="year" value="${profile.birthDate.year }" name="year" type="year">
		</div>

		<div>
			<p id="p_birth">Пол:</p>
		</div>

		<div>
	      	<select id="inputSex" name="sex">
	      		<option value="male">M</option>
	     	 	<option value="female">Ж</option>
	     	</select>
	     	<script>
	     	document.getElementById("inputSex").value = "${profile.sex }";
	     	</script>
	    </div>

	    <div>
	  		<textarea id="inputAbout" name="about" rows="3">${profile.about }</textarea>
	  	</div>

	  	<div>
	    	<img id="icon" src="/Forum/DisplayIcon?id=${sessionScope.loggedProfileId }">
	    </div>
	    <div>
	    	<input type="file" id="imageUpload" name="datafile">
		</div>

		<div>
	    	<input id="bt_reg" type="submit" value="Сохранить изменения" name="save">
	   </div>

	   <div onclick="delOwnProfile('block')">
	    	<p id="del_btn">Удалить профиль(</p>
	   </div>

	</form>
</div> <!--content-->

	<jsp:include page="footer.jsp" />




</div>

</body>

</html>