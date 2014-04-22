package ua.net.forum.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import ua.net.forum.db.DBQuery;

/**
 * Servlet implementation class DisplayIcon
 */
@WebServlet("/DisplayIcon")
public class DisplayIcon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayIcon() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String profileId = request.getParameter("id");
		byte[] icon = DBQuery.getIcon(profileId);
		if (icon == null) {
		
			FileInputStream fileInputStream=null;
	        File file = new File(request.getSession().getServletContext().getRealPath("/") + "images/default_icon.png"); 
	        icon = new byte[(int) file.length()];
	
		    fileInputStream = new FileInputStream(file);
		    fileInputStream.read(icon);
		    fileInputStream.close();
		}
		response.setContentType("image/jpeg");  
	    ServletOutputStream out;  
	    out = response.getOutputStream();  
	    out.write(icon);
	    out.close();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
