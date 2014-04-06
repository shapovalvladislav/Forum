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
import model.Section;
import model.Topic;
import ua.net.forum.db.DBQuery;
import ua.net.forum.view.SectionForView;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sectionId = request.getParameter("id");
		Collection<Topic> topics = DBQuery.getTopics(sectionId);
		List<TopicForView> topicsForView = new ArrayList<TopicForView>();
		for (Topic topic : topics) {
			int id = topic.getId();
			String name = topic.getName();
			int msgCount = topic.getMsgCount();
			Timestamp lastMsgDate = null;
			String lastMsgUserName = null;
			int lastMsgUserId = 0;

			for (Message msg : topic.getMessages()) {
				Timestamp curMsgDate = msg.getDate();
				if (lastMsgDate == null || curMsgDate.after(lastMsgDate)) {
					lastMsgDate = curMsgDate;
					lastMsgUserName = msg.getProfile().getFullName();
					lastMsgUserId = msg.getProfile().getId();
					continue;
				}
			}
			TopicForView topicForView = new TopicForView(id, name, msgCount, lastMsgDate, lastMsgUserName, lastMsgUserId);
			topicsForView.add(topicForView);
		}
		
		request.setAttribute("topics", topicsForView);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
