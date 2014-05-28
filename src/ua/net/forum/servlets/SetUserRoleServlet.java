package ua.net.forum.servlets;

import java.awt.image.DataBufferUShort;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Profile;
import model.Role;
import model.User;
import service.IProfileService;
import service.IRoleService;
import service.IUserService;
import service.ServiceFactory;
import ua.net.forum.db.DBQuery;

/**
 * Servlet implementation class AddMessageServlet
 */
@WebServlet("/SetUserRoleServlet")
public class SetUserRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetUserRoleServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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