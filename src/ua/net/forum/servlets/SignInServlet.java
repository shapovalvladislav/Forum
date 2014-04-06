package ua.net.forum.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Profile;
import ua.net.forum.db.DBQuery;
import ua.net.forum.db.HashPassword;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = HashPassword.passwordToHash(request.getParameter("password"));
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		System.out.println(prevPage);
		if (DBQuery.userExists(login, password)) {
			Profile p = DBQuery.getProfileByLogin(login);
			request.getSession().setAttribute("loggedProfile", p);
			response.sendRedirect(prevPage);
		} else {
			request.getRequestDispatcher("/signInError.jsp").forward(request, response);
		}
		
      
	}

}
