package ua.net.forum.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.db.DBQuery;
import ua.net.forum.model.Role;
import ua.net.forum.model.User;
import ua.net.forum.service.IRoleService;
import ua.net.forum.service.IUserService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/SetUserRoleServlet")
public class SetUserRoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SetUserRoleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String profileId = request.getParameter("profileId");
        String userRole = request.getParameter("userRole");
        int userId = DBQuery.getUserIdByProfileId(Integer.parseInt(profileId));

        IUserService userService = ServiceFactory.DEFAULT.getUserService();
        User user = userService.getEntityById(userId);

        IRoleService roleService = ServiceFactory.DEFAULT.getRoleService();
        Role role = roleService.getEntityById(Integer.parseInt(userRole));

        user.setRoleBean(role);

        userService.updateEntity(userId, user);

        response.sendRedirect("/Forum");
    }

}