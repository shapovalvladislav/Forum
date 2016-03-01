package ua.net.forum.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.Profile;
import ua.net.forum.model.User;
import ua.net.forum.service.IProfileService;
import ua.net.forum.service.IUserService;
import ua.net.forum.service.ServiceFactory;
import ua.net.forum.db.DBQuery;
import ua.net.forum.db.HashPassword;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignInServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = HashPassword.passwordToHash(request.getParameter("password"));
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        IUserService userService = ServiceFactory.DEFAULT.getUserService();
        if (userService.isUserExists(login, password)) {
            IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
            Profile p = profileService.findProfileByLogin(login);
            request.getSession().setAttribute("loggedProfileId", p.getId());
            request.getSession().setAttribute("login", login);
            User user = null;
            try {
                user = userService.findByLogin(login);
            } catch (ServiceException e) {
            }
            if (user != null)
                request.getSession().setAttribute("userRole", user.getRoleBean().getName());
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("canCreateTopic", DBQuery.canCreateTopic(p.getId()));

            response.sendRedirect(prevPage);
        } else {
            request.getRequestDispatcher("/signInError.jsp").forward(request, response);
        }
    }

}
