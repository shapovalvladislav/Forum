package ua.net.forum.servlets;


import java.io.IOException;
import java.nio.charset.Charset;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.db.DBQuery;
import model.Message;
import model.Profile;
import model.Topic;
import model.User;

/**
 * Servlet implementation class AddMessageServlet
 */
@WebServlet("/AddMessageServlet")
public class AddMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMessageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String content = request.getParameter("msgContent");
		String s = new String(Charset.forName("UTF-8").encode(content).array());
		System.out.println(s);
		System.out.println("Content " + content);
		int topicId = Integer.parseInt(request.getParameter("topic"));
		Profile profile = (Profile) request.getSession().getAttribute("loggedProfile");
		DBQuery.addMessage(topicId, content, profile);
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		response.sendRedirect(prevPage);
	}

}
