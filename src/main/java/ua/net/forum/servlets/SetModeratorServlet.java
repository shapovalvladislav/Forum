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

@WebServlet("/SetModeratorServlet")
public class SetModeratorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SetModeratorServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String profileId = request.getParameter("profileId");
        String sectionId = request.getParameter("sectionId");

        IProfileService profileService = ServiceFactory.DEFAULT.getProfileService();
        Profile profile = profileService.getEntityById(Integer.parseInt(profileId));

        ISectionService sectionService = ServiceFactory.DEFAULT.getSectionService();
        Section section = sectionService.getEntityById(Integer.parseInt(sectionId));

        section.setProfile(profile);

        sectionService.updateEntity(Integer.parseInt(sectionId), section);

        response.sendRedirect("/Forum");
    }

}