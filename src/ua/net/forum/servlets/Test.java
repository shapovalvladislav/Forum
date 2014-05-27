package ua.net.forum.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.ITestDao;
import daoImpl.TestDaoJpa;
import model.Message;
import model.Profile;
import model.Section;
import model.Topic;
import service.IMessageService;
import service.ISectionService;
import service.ITopicService;
import service.ServiceFactory;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ISectionService sectionService = ServiceFactory.DEFAULT.getSectionService();
//		
//		//Profile p = ServiceFactory.DEFAULT.getProfileService().getEntityById(34);
//		Profile p = new Profile();
//		p.setBirthDate(new Date());
//		p.setAbout("about");
//		p.setFullName("full name");
//		p.setNickName("nick name");
//		p.setSex("male");
//		p.setEmail("email");
//		p.setMsgCount(0);
//		
//	
//		Section section = new Section();
//		section.setName("Section1");
//		section.setDescription("Descr");
//		section.setProfile(p);
//		
//		Topic t = new Topic();
//		t.setName("Topic 1");
//		t.setProfile(p);
//		t.setSectionBean(section);
//		
//		
//		Message msg = new Message();
//		msg.setContent("content");
//		msg.setProfile(p);
//		msg.setTopicBean(t);
//		msg.setDate(new Timestamp(2014, 5, 4, 20, 14, 15, 0));
//		
//		List<Message> msgLst = new ArrayList<Message>();
//		msgLst.add(msg);
//		
//		t.setMessages(msgLst);
//
//		List<Topic> topicLst = new ArrayList<Topic>();
//		topicLst.add(t);
//		
//		section.setTopics(topicLst);
//		
//		sectionService.addEntity(section);
		ITestDao dao = DaoFactory.OPENJPA.getTestDao();
		dao.test();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
