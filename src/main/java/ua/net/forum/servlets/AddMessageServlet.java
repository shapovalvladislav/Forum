package ua.net.forum.servlets;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.db.DBQuery;
import ua.net.forum.model.Message;
import ua.net.forum.model.Profile;
import ua.net.forum.model.Topic;
import ua.net.forum.service.IMessageService;
import ua.net.forum.service.IProfileService;
import ua.net.forum.service.ITopicService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/AddMessageServlet")
public class AddMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddMessageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String content = request.getParameter("msgContent");

        int topicId = Integer.parseInt(request.getParameter("topic"));
        int profileId = (Integer) request.getSession().getAttribute("loggedProfileId");
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
        request.getSession().setAttribute("canCreateTopic",DBQuery.canCreateTopic(profileId));

        response.sendRedirect(prevPage + "#last");
    }

}