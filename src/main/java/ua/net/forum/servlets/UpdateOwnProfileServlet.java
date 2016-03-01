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
import ua.net.forum.db.DBQuery;
import ua.net.forum.db.HashPassword;

@WebServlet("/UpdateOwnProfileServlet")
public class UpdateOwnProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateOwnProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fullName = (String) request.getParameter("fullName");
        String nickName = (String) request.getParameter("nickName");
        String login = (String) request.getParameter("login");
        String old_password = (String) request.getParameter("old_password");
        String new_password = (String) request.getParameter("new_password");
        String email = (String) request.getParameter("email");
        Integer day = Integer.parseInt((String) request.getParameter("day"));
        Integer month = Integer
                .parseInt((String) request.getParameter("month"));
        Integer year = Integer.parseInt((String) request.getParameter("year"));
        year -= 1900;
        @SuppressWarnings("deprecation")
        Date birthDate = new Date(year, month, day);
        String sex = (String) request.getParameter("sex");
        String about = (String) request.getParameter("about");
        String file = (String) request.getParameter("datafile");
        Profile profile = DBQuery.getProfileByLogin(login);
        profile.setFullName(fullName);

        profile.setNickName(nickName);
        profile.setEmail(email);
        profile.setBirthDate(birthDate);
        profile.setSex(sex);
        profile.setAbout(about);

        if (!file.isEmpty()) {
            String path = "/var/tmp/uploads/" + file;
            File icon = new File(path);
            FileInputStream fileInputStream = new FileInputStream(icon);
            byte[] bFile = new byte[(int) icon.length()];
            fileInputStream.read(bFile);
            fileInputStream.close();
            profile.setIcon(bFile);
        }

        DBQuery.updateProfile(profile);

        User user = DBQuery.getUserByLogin(login);
        user.setProfileBean(profile);
        if (!old_password.isEmpty() || !new_password.isEmpty()) {
            String hashOldPass = HashPassword.passwordToHash((String) request
                    .getParameter("old_password"));
            if (hashOldPass == user.getPassword()) {
                String hashNewPass = HashPassword
                        .passwordToHash((String) request
                                .getParameter("new_password"));
                user.setPassword(hashNewPass);
            }
        }
        DBQuery.updateUser(user);

        String nextJSP = "/Forum/ownProfile.jsp";
        response.sendRedirect(nextJSP);
    }
}