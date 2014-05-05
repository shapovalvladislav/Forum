package ua.net.forum.servlets;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Profile;
import model.User;
import service.IUserService;
import service.ServiceFactory;
import ua.net.forum.db.DBQuery;
import ua.net.forum.db.HashPassword;
import ua.net.forum.mail.SendEmail;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = (String) request.getParameter("fullName");
		String nickName = (String) request.getParameter("nickName");
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		String hashedPassword = HashPassword.passwordToHash(password);
		String email = (String) request.getParameter("email");
		Integer day = Integer.parseInt((String) request.getParameter("day"));
		Integer month = Integer.parseInt((String) request.getParameter("month"));
		Integer year = Integer.parseInt((String) request.getParameter("year"));
		Date birthDate = new Date(year, month, day);
		String sex = (String) request.getParameter("sex");
		String about = (String) request.getParameter("about");
		String file = (String) request.getParameter("datafile");
		
		String path = "/var/tmp/uploads/" + file;
		
		
		File icon = new File(path);
	   	FileInputStream fileInputStream=new FileInputStream(icon);

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
	    user.setPassword(hashedPassword);
	    user.setProfileBean(profile);
	    user.setRoleBean(DBQuery.getUserRole());
	    IUserService userService = ServiceFactory.DEFAULT.getUserService();
	    userService.addEntity(user);
	    Thread sendEmail = new Thread(new SendEmail(email, login, password));
	    sendEmail.start();
	   
		String nextJSP = "/Forum";
		response.sendRedirect(nextJSP);
	}
}
