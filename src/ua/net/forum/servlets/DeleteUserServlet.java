package ua.net.forum.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ServiceException;
import model.Message;
import model.Profile;
import model.Role;
import model.Section;
import model.Topic;
import model.User;
import service.IProfileService;
import service.IRoleService;
import service.ISectionService;
import service.ITopicService;
import service.IUserService;
import service.ServiceFactory;

/**
 * Servlet implementation class AddMessageServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserServlet() {
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
		IProfileService profileService = ServiceFactory.DEFAULT
				.getProfileService();
		String profileId = request.getParameter("profileId");
		profileService.delEntity(Integer.parseInt(profileId));
		response.sendRedirect("/Forum");
	}

}