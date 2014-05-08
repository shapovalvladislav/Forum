package ua.net.forum.servlets;


import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IForbiddenWordService;
import service.IMessageService;
import service.IProfileService;
import service.ITopicService;
import service.IUserService;
import service.ServiceFactory;
import ua.net.forum.db.DBQuery;
import model.Message;
import model.Profile;
import model.Topic;
import model.User;

/**
 * Servlet implementation class AddMessageServlet
 */
@WebServlet("/AddMessageServlet")
public class AddMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMessageServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("msgContent");
		
		IForbiddenWordService forbiddenService = ServiceFactory.DEFAULT.getForbiddenWordService();
		String hidden = forbiddenService.hideForbiddenWords(content);
		if (hidden != null)
			content = hidden;
		int topicId = Integer.parseInt(request.getParameter("topic"));
		int profileId = (int) request.getSession().getAttribute("loggedProfileId");
		IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
		Profile profile = profileService.getEntityById(profileId);
		ITopicService topicService = ServiceFactory.DEFAULT.getTopicService();
		Topic topic = topicService.getEntityById(topicId);
		Message msg = new Message();
		msg.setTopicBean(topic);
		msg.setProfile(profile);
		msg.setContent(content);
		msg.setDate(new Timestamp(new Date().getTime()));
		IMessageService messageService = ServiceFactory.DEFAULT.getMessageService();
		messageService.addEntity(msg);
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		response.sendRedirect(prevPage);
}

}