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

import ua.net.forum.model.Message;
import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;
import ua.net.forum.model.Topic;
import ua.net.forum.service.IProfileService;
import ua.net.forum.service.ISectionService;
import ua.net.forum.service.ITopicService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/AddTopicServlet")
public class AddTopicServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddTopicServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String topicName = request.getParameter("topicName");
        String messageContent = request.getParameter("message");
        int profileId = (Integer) request.getSession().getAttribute("loggedProfileId");
        IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
        Profile profile = profileService.getEntityById(profileId);
        Message message = new Message();
        message.setContent(messageContent);
        message.setProfile(profile);
        message.setDate(new Timestamp(new Date().getTime()));
        Topic topic = new Topic();
        topic.setLastChange(new Timestamp(new Date().getTime()));

        topic.setName(topicName);
        topic.setProfile(profile);
        topic.setMsgCount(0);
        message.setTopicBean(topic);

        List<Message> listMsgs = new ArrayList<Message>();
        listMsgs.add(message);

        topic.setMessages(listMsgs);


        int sectionId = Integer.parseInt(request.getParameter("section"));
        ISectionService sectionService = ServiceFactory.DEFAULT.getSectionService();
        Section sectionBean = sectionService.getEntityById(sectionId);
        topic.setSectionBean(sectionBean);

        ITopicService topicService = ServiceFactory.DEFAULT.getTopicService();
        topicService.addEntity(topic);

        String prevPage = (String) request.getSession().getAttribute("prevPage");
        response.sendRedirect(prevPage);
    }

}
