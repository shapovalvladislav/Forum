package ua.net.forum.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.model.Profile;
import ua.net.forum.service.IProfileService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/DisplayIcon")
public class DisplayIcon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayIcon() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int profileId = Integer.parseInt(request.getParameter("id"));
        IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
        Profile profile = profileService.getEntityById(profileId);
        byte[] icon = profile.getIcon();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
