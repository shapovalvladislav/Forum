package ua.net.forum.servlets;


import java.io.IOException;

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
		Message msg = new Message();
		String content = request.getParameter("content");
		Integer topicId = Integer.parseInt((String) request.getSession().getAttribute("topic"));
		System.out.println("TOPIC " + topicId);
		msg.setContent(content);
	
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		try {
			Query query = em.createQuery("SELECT t FROM Topic t WHERE t.id=:id").setMaxResults(1);
			query.setParameter("id", topicId);
			Topic topic = (Topic) query.getResultList().get(0);
			msg.setTopicBean(topic);
			query = em.createQuery("SELECT u FROM User u WHERE u.login=:login").setMaxResults(1);
			query.setParameter("login", request.getSession().getAttribute("user"));
			User user = (User) query.getResultList().get(0);
			msg.setProfile(user.getProfileBean());
			em.persist(msg);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
		String nextJSP = "/" + (String) request.getSession().getAttribute("previousPage");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

}
