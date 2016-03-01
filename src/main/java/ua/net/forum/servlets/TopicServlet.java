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

import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;
import ua.net.forum.model.Topic;
import ua.net.forum.service.ISectionService;
import ua.net.forum.service.ITopicService;
import ua.net.forum.service.ServiceFactory;
import ua.net.forum.view.TopicForView;

@WebServlet("/TopicServlet")
public class TopicServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TopicServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sectionId = request.getParameter("id");
        ITopicService topicService = ServiceFactory.DEFAULT.getTopicService();
        Collection<Topic> topics = null;
        try {
            topics = topicService.findBySection(Integer.parseInt(sectionId));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
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
        ISectionService sectionService = ServiceFactory.DEFAULT.getSectionService();
        Section section = sectionService.getEntityById(Integer.parseInt(sectionId));
        request.setAttribute("moderatorId", section.getProfile().getId());
        request.setAttribute("topics", topicsForView);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
