package ua.net.forum.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;
import ua.net.forum.service.IProfileService;
import ua.net.forum.service.ISectionService;
import ua.net.forum.service.ServiceFactory;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
        Profile p = profileService.getEntityById(id);
        Collection<Section> sections = null;
        ISectionService sectionService = ServiceFactory.DEFAULT.getSectionService();
        sections = sectionService.getAllEntites();
        request.setAttribute("sections", sections);
        request.setAttribute("profile", p);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
