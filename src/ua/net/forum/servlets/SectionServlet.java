package ua.net.forum.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.db.DBQuery;
import ua.net.forum.view.SectionForView;
import model.Message;
import model.Section;
import model.Topic;

/**
 * Servlet implementation class SectionServlet
 */
@WebServlet("/SectionServlet")
public class SectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Section> sections = DBQuery.getSections();
		List<SectionForView> sectionsForView = new ArrayList<SectionForView>();
		for (Section section : sections) {
			int id = section.getId();
			String name = section.getName();
			int topicCount = section.getTopics().size();
			int msgCount = 0;
			Timestamp lastMsgDate = null;
			String lastMsgUserName = null;
			int lastMsgUserId = 0;
		
			for (Topic topic : section.getTopics() ) {
				msgCount += topic.getMsgCount();
				for (Message msg : topic.getMessages()) {
					Timestamp curMsgDate = msg.getDate();
					if (lastMsgDate == null || curMsgDate.after(lastMsgDate)) {
						lastMsgDate = curMsgDate;
						lastMsgUserName = msg.getProfile().getFullName();
						lastMsgUserId = msg.getProfile().getId();
						continue;
					}
				}
			}
			SectionForView sectionForView = new SectionForView(id, name, topicCount, msgCount, lastMsgDate, lastMsgUserName, lastMsgUserId);
			sectionsForView.add(sectionForView);
		}
		
		request.setAttribute("sections", sectionsForView);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
