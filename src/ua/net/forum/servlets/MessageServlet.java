package ua.net.forum.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ServiceException;
import service.IForbiddenWordService;
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
		Message[] messagesFromPage = Arrays.copyOfRange(allMessages, beg, end);
		
		selectPageList(18, 20);
		
		request.setAttribute("messages", messagesFromPage);
		request.setAttribute("pageCount", pageCount);
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
	
	private void selectPageList(int pageNumber, int pageCount) {
		if (pageNumber == 1) {
			int right = pageCount;
			while (right > 5)
				right /= 2;
			for (int i = 1; i <= right; i++)
				System.out.println(i);
			return;
		}
		int left = pageNumber - 1;
		int right = pageCount - left;
		int min = (left < right) ? left : right;
		if (min > 5)
			min = 5;
		for (int i = (pageNumber - min); i < pageNumber; i++)
			System.out.println(i);
		System.out.println(pageNumber);
		for (int i = pageNumber + 1; i < (pageNumber + min); i++)
			System.out.println(i);
		
	}

}
