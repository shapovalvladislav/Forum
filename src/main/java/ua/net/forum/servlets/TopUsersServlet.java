package ua.net.forum.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.net.forum.db.DBQuery;
import ua.net.forum.model.Profile;

@WebServlet("/TopUsersServlet")
public class TopUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TopUsersServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Object> objects = null;
        objects = DBQuery.getTopProfiles();
        Collection<Profile> topUsers = new ArrayList<Profile>();
        if (objects != null) {
            for (Object ob: objects){
                Object[] os = (Object[])ob;
                Profile p = new Profile();
                p.setId((Integer)os[0]);
                p.setNickName((String)os[1]);
                p.setFullName((String)os[2]);
                p.setSex((String)os[3]);
                p.setBirthDate((Date)os[4]);
                p.setAbout((String)os[5]);
                p.setEmail((String)os[6]);
                p.setMsgCount((Integer)os[7]);
                p.setTopicCount((Integer)os[8]);
                p.setIcon((byte[])os[9]);
                if (p.getId() != 1)
                    topUsers.add(p);
            }
        }
        request.setAttribute("topUsers", topUsers);
        int profileId = (Integer) request.getSession().getAttribute("loggedProfileId");
        if (DBQuery.canCreateTopic(profileId))
            System.out.println(true);
        else System.out.println(false);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
