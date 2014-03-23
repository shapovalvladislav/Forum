<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.io.*" %>
<%@ page import="model.*" %>
<%
	String fullName = (String) request.getParameter("name");
	String nickName = (String) request.getParameter("nickname");
	String login = (String) request.getParameter("login");
	String password = (String) request.getParameter("password");
	String email = (String) request.getParameter("email");
	Integer day = Integer.parseInt((String) request.getParameter("day"));
	Integer month = Integer.parseInt((String) request.getParameter("day"));
	Integer year = Integer.parseInt((String) request.getParameter("day"));
	String birth = year + "/" + month + "/" + day; 
	Date birthDate = new Date(year, month, day);
	String sex = (String) request.getParameter("sex");
	String about = (String) request.getParameter("about");
	String file = (String) request.getParameter("datafile");
	
	String path = "/home/bsa/apache-tomcat-7.0.52/wtpwebapps/Forum/uploads/" + file;
	
	
	File icon = new File(path);
   	FileInputStream fileInputStream=new FileInputStream(icon);;

    byte[] bFile = new byte[(int) icon.length()];

    fileInputStream.read(bFile);
    fileInputStream.close();
	
    Profile profile = new Profile();
    profile.setFullName(fullName);
    profile.setNickName(nickName);
    profile.setEmail(email);
    profile.setBirthDate(birthDate);
    profile.setSex(sex);
    profile.setAbout(about);
    profile.setIcon(bFile);
    
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    user.setProfileBean(profile);
 
    
    

    
    
	System.out.println(fullName);
	System.out.println(nickName);
	System.out.println(login);
	System.out.println(password);
	System.out.println(email);
	System.out.println(day);
	System.out.println(month);
	System.out.println(year);
	System.out.println(birth);
	System.out.println(birthDate);
	System.out.println(sex);
	System.out.println(about);
	System.out.println(file);

%>