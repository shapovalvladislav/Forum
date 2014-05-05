package ua.net.forum.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ServiceException;
import service.IMessageService;
import service.ITopicService;
import service.ServiceFactory;
import ua.net.forum.db.DBQuery;
import model.Message;
import model.Topic;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int topicId = Integer.parseInt(request.getParameter("topic"));
		IMessageService messageService = ServiceFactory.DEFAULT.getMessageService();
		Collection<Message> messages = null;

	
		ITopicService topicService = ServiceFactory.DEFAULT.getTopicService();
		Topic topic = topicService.getEntityById(topicId);
		System.out.println(topic.getName());
		
		messages = messageService.findByTopic(topic);
		
		request.setAttribute("messages", messages);
		request.setAttribute("topicAutor", topic.getProfile().getNickName());
		request.setAttribute("topicName", topic.getName());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
