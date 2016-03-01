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

import ua.net.forum.model.Profile;
import ua.net.forum.model.User;
import ua.net.forum.service.IUserService;
import ua.net.forum.service.ServiceFactory;
import ua.net.forum.db.DBQuery;
import ua.net.forum.db.HashPassword;
import ua.net.forum.mail.SendEmail;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

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
        System.out.println(request.getServerName());


        String basePath = request.getSession().getServletContext().getRealPath("/");
        String path = (file != null) ? basePath + "uploads/" + file : basePath + "images/default_icon.png";
        System.out.println(path);

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
        profile.setMsgCount(0);
        profile.setTopicCount(0);

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
