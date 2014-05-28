package ua.net.forum.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IProfileService;
import service.ServiceFactory;

/**
 * Servlet implementation class AddMessageServlet
 */
@WebServlet("/DeleteOwnProfileServlet")
public class DeleteOwnProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteOwnProfileServlet() {
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
		
		profileService.delEntity((int)request.getSession().getAttribute("loggedProfileId"));
		
		request.getSession().removeAttribute("loggedProfileId");
		request.getSession().removeAttribute("login");
		
		request.getSession().removeAttribute("userId");
		response.sendRedirect("/Forum");
		
	
	}

}