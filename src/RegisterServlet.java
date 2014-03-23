

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

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

import model.Profile;
import model.Role;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String fullName = (String) request.getParameter("name");
		String nickName = (String) request.getParameter("nickname");
		String login = (String) request.getParameter("login");
		String password = (String) request.getParameter("password");
		String email = (String) request.getParameter("email");
		Integer day = Integer.parseInt((String) request.getParameter("day"));
		Integer month = Integer.parseInt((String) request.getParameter("day"));
		Integer year = Integer.parseInt((String) request.getParameter("day"));
		String birth = year + "/" + month + "/" + day; 
		Date birthDate = new Date(year, month, day);
		String sex = (String) request.getParameter("sex");
		String about = (String) request.getParameter("about");
		String file = (String) request.getParameter("datafile");
		
		String path = "/home/bsa/apache-tomcat-7.0.52/wtpwebapps/Forum/uploads/" + file;
		
		
		File icon = new File(path);
	   	FileInputStream fileInputStream=new FileInputStream(icon);;

	    byte[] bFile = new byte[(int) icon.length()];

	    fileInputStream.read(bFile);
	    fileInputStream.close();
		
	    Profile profile = new Profile();
	    profile.setFullName(fullName);
	    profile.setNickName(nickName);
	    profile.setEmail(email);
	    profile.setBirthDate(birthDate);
	    profile.setSex(sex);
	    profile.setAbout(about);
	    profile.setIcon(bFile);
	    
	    User user = new User();
	    user.setLogin(login);
	    user.setPassword(password);
	    user.setProfileBean(profile);
	    user.setRoleBean(getUserRole());
	    
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		try {
			em.persist(profile);
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
		String nextJSP = "/main.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	    
	}
	
	private Role getUserRole() {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Role role = null;
		try {
			Query query = em.createQuery("SELECT r FROM Role r WHERE r.name=:name").setMaxResults(1);
			query.setParameter("name", "user");
			role = (Role) query.getResultList().get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return role;
		}
	}

}
