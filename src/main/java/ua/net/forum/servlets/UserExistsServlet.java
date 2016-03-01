package ua.net.forum.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.db.DBQuery;

@WebServlet("/UserExistsServlet")
public class UserExistsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserExistsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        PrintWriter out = response.getWriter();
        if (DBQuery.loginExists(login)) {
            System.out.println("true");
            out.print("false");
        }
        else {
            System.out.println("fasle");
            out.print("true");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
