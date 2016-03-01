package ua.net.forum.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.model.Message;
import ua.net.forum.model.Topic;
import ua.net.forum.service.IForbiddenWordService;
import ua.net.forum.service.IMessageService;
import ua.net.forum.service.ITopicService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MessageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        int topicId = Integer.parseInt(request.getParameter("topic"));
        IMessageService messageService = ServiceFactory.DEFAULT.getMessageService();

        ITopicService topicService = ServiceFactory.DEFAULT.getTopicService();
        Topic topic = topicService.getEntityById(topicId);

        IForbiddenWordService forbiddenService = ServiceFactory.DEFAULT.getForbiddenWordService();

        Collection<Message> messages = messageService.findByTopic(topic);
        for (Message msg : messages) {
            String content = msg.getContent();
            content = forbiddenService.hideForbiddenWords(content);
            if (content != null)
                msg.setContent(content);
        }

        int pageNumber = 1;
        String pageNumberParameter = request.getParameter("page");
        if (pageNumberParameter != null)
            pageNumber = Integer.parseInt(pageNumberParameter);
        int messagesPerPage = 10;
        int pageCount = (int) Math.ceil((double) messages.size() / 10);


        Message[] allMessages = messages.toArray(new Message[0]);
        int beg = (pageNumber-1)*messagesPerPage;
        int end = beg + 9;
        if (pageNumber == pageCount)
            end = messages.size() - 1;

        Message[] messagesFromPage = Arrays.copyOfRange(allMessages, beg, end+1);
        System.out.println(messagesFromPage.length);
        Collection<String> links = selectPageList(pageNumber, pageCount, topicId);

        for (String s : links)
            System.out.println(s);
        request.setAttribute("moderatorId", topic.getSectionBean().getProfile().getId());
        request.setAttribute("pageLinks", links);
        request.setAttribute("messages", messagesFromPage);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("topicAutor", topic.getProfile().getNickName());
        request.setAttribute("topicName", topic.getName());
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

    private Collection<String> selectPageList(int pageNumber, int pageCount, int topicId) {
        Map<Integer, String> page_link = new TreeMap<Integer, String>();

        if (pageNumber == 1) {
            int right = pageCount;
            while (right > 5)
                right /= 2;
            for (int i = 1; i <= right; i++)
                page_link.put(i, "<a class='pageLinks' href='/Forum/messages.jsp?id=" + topicId + "&page=" + i + "'>" + i + "</a>");
        } else {
            int left = pageNumber - 1;
            int right = pageCount - left;
            int min = (left < right) ? left : right;
            if (min > 5)
                min = 5;
            for (int i = (pageNumber - min); i <= pageNumber; i++)
                page_link.put(i, "<a class='pageLinks' href='/Forum/messages.jsp?id=" + topicId + "&page=" + i + "'>" + i + "</a>");
            for (int i = pageNumber + 1; i < (pageNumber + min); i++)
                page_link.put(i, "<a class='pageLinks' href='/Forum/messages.jsp?id=" + topicId + "&page=" + i + "'>" + i + "</a>");
        }
        if (!page_link.containsKey(1))
            page_link.put(1, "<a class='pageLinks' href='/Forum/messages.jsp?id=" + topicId + "&page=1'>" + 1 + "</a>");
        if (!page_link.containsKey(pageCount))
            page_link.put(pageCount, "<a class='pageLinks' href='/Forum/messages.jsp?id=" + topicId + "&page=" + pageCount + "'>" + pageCount + "</a>");

        String curLink = page_link.get(pageNumber);
        curLink = curLink.replaceFirst("<a class='pageLinks'", "<a class='pageLinks pageVisited'");
        page_link.put(pageNumber, curLink);

        return page_link.values();
    }

}
