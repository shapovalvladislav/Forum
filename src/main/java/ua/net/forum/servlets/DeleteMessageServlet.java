package ua.net.forum.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.service.IMessageService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/DeleteMessageServlet")
public class DeleteMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteMessageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String messageId = request.getParameter("messageId");
        System.out.println(messageId);
        IMessageService messageService = ServiceFactory.DEFAULT
                .getMessageService();
        messageService.delEntity(Integer.parseInt(messageId));
        response.sendRedirect("/Forum");
    }

}