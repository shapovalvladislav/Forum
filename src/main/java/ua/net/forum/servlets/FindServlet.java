package ua.net.forum.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.model.Message;
import ua.net.forum.service.IMessageService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FindServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pattern = request.getParameter("find");
        IMessageService msgService = ServiceFactory.DEFAULT.getMessageService();
        Collection<Message> messages = msgService.getAllEntites();
        for (Message msg : messages) {
            String content = msg.getContent();
            if (content.lastIndexOf(pattern) != -1) {
                System.out.println(content);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
