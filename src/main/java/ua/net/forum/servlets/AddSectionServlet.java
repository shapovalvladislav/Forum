package ua.net.forum.servlets;


import java.io.IOException;

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

@WebServlet("/AddSectionServlet")
public class AddSectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddSectionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String sectionName = request.getParameter("sectionName");
        String sectionDescription = request.getParameter("sectionDescription");
        int profileId = (Integer) request.getSession().getAttribute("loggedProfileId");
        IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
        Profile profile = profileService.getEntityById(profileId);
        Section section = new Section();
        section.setName(sectionName);
        section.setDescription(sectionDescription);
        section.setProfile(profile);
        ISectionService sectionService = ServiceFactory.DEFAULT.getSectionService();
        sectionService.addEntity(section);
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        response.sendRedirect(prevPage);
    }

}
