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
import model.Section;
import model.User;
import service.IProfileService;
import service.IRoleService;
import service.ISectionService;
import service.IUserService;
import service.ServiceFactory;
import ua.net.forum.db.DBQuery;

/**
 * Servlet implementation class AddMessageServlet
 */
@WebServlet("/SetModeratorServlet")
public class SetModeratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetModeratorServlet() {
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
		String sectionId = request.getParameter("sectionId");
		
		IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
		Profile profile = profileService.getEntityById(Integer.parseInt(profileId));
		
		ISectionService sectionService = ServiceFactory.DEFAULT.getSectionService();
		Section section = sectionService.getEntityById(Integer.parseInt(sectionId));
		
		section.setProfile(profile);
		
		sectionService.updateEntity(Integer.parseInt(sectionId), section);
		
		response.sendRedirect("/Forum");
	}

}