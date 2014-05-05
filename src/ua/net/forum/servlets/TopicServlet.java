package ua.net.forum.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Message;
import model.Profile;
import model.Topic;
import service.ITopicService;
import service.ServiceFactory;
import ua.net.forum.db.DBQuery;
import ua.net.forum.view.TopicForView;

/**
 * Servlet implementation class TopicServlet
 */
@WebServlet("/TopicServlet")
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sectionId = request.getParameter("id");
		ITopicService topicService = ServiceFactory.DEFAULT.getTopicService();
		Collection<Topic> topics = topicService.getAllEntites();
		List<TopicForView> topicsForView = new ArrayList<TopicForView>();
		for (Topic topic : topics) {
			int id = topic.getId();
			String name = topic.getName();
			int msgCount = topic.getMsgCount();
			Timestamp lastMsgDate = topicService.getLastMsgDate(topic);
			Profile lastMsgProfile = topicService.getLastMsgProfile(topic); 
			String lastMsgProfileNickName = null;
			int lastMsgProfileId = 0;
			if (lastMsgProfile != null)
			{
				lastMsgProfileNickName = lastMsgProfile.getNickName();
				lastMsgProfileId = lastMsgProfile.getId();
			}
			TopicForView topicForView = new TopicForView(id, name, msgCount, lastMsgDate, lastMsgProfileNickName, lastMsgProfileId);
			topicsForView.add(topicForView);
		}
		request.setAttribute("topics", topicsForView);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
