package model;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import model.Message;
import model.Profile;
import model.Role;
import model.Section;
import model.Topic;
import model.User;



public class Test {
	
	
	private static void read(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		try {
			Query query = em.createQuery("SELECT x FROM Message x");
			List<?> list = query.getResultList();
			if (CollectionUtils.isNotEmpty(list)) {
				for (Object o : list) {
					Message msg = (Message) o;
					System.out.println("Message: " + msg.getContent());
					System.out.println("Profile: " + msg.getProfile().getFullName());
				}
			} else {
				System.out.println("No messages");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
	
	public static boolean userExists(String login, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		try {
			Query query = em.createQuery("SELECT x FROM User x WHERE x.login=:login AND x.password=:password");
			query.setParameter("login", login);
			query.setParameter("password", password);
			if (query.getResultList().size() == 1)
				result = true;
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}
	

	public static String getIcon(String login, String path) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		byte[] icon = null;
		User user = null;
		Profile profile = null;
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.login=:name");
			query.setParameter("name", login);
			
			profile = ((User) query.getResultList().get(0)).getProfileBean();
			icon = profile.getIcon();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
		BufferedOutputStream bos;
		String fileName = null;
		try {
			fileName = path + profile.getNickName();
			System.out.println("FILENAME " + fileName);
			File f = new File(fileName);
			System.out.println(f.getAbsolutePath());
			bos = new BufferedOutputStream(new FileOutputStream(fileName));
			bos.write(icon);
			bos.flush();
			bos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profile.getNickName();
	}
	
	
	public static Collection<Profile> getTopUsers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createQuery("SELECT p FROM Profile p ORDER BY p.msgCount DESC").setMaxResults(10);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return (Collection<Profile>) list;
		}
	}
	
	public static Collection<Message> getMessages(String topic) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createQuery("SELECT x FROM Message x WHERE x.topicBean.id=" + topic);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return (Collection<Message>) list;
		}
	}
	
	public static Collection<Topic> getTopics(String section) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createQuery("SELECT t FROM Topic t WHERE t.sectionBean.id=" + section);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return (Collection<Topic>) list;
		}
	}
	
	public static Collection<Section> getSections() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createQuery("SELECT x FROM Section x");
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return (Collection<Section>) list;
		}
	}
	
	public static void addMessage() {

		
	}
	
	private static void save(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			
			Profile profile = new Profile();
			profile.setFullName("Fullname");
			profile.setNickName("Nickname 1");
			profile.setSex("M");
			profile.setBirthDate(new Date());
			profile.setEmail("qq@mail.ru");
			
			em.persist(profile);
			
			Role role = new Role();
			role.setName("User");
			em.persist(role);
			
			User user = new User();

			user.setLogin("login");
			user.setPassword("password");
			user.setProfileBean(profile);
			user.setRoleBean(role);
			

			
			Section section = new Section();
			section.setName("Section 1");
			section.setDescription("Description");
			
			Topic topic = new Topic();
			topic.setName("Topic 1");
			topic.setProfile(profile);
			
			Message msg = new Message();
			msg.setContent("Message content");
			msg.setProfile(profile);
			msg.setDate(new Timestamp(0));
			msg.setTopicBean(topic);
			
			topic.setMessages(new ArrayList<Message>());
			topic.getMessages().add(msg);
			topic.setSectionBean(section);
			
			em.persist(user);
			em.persist(section);
			em.persist(topic);
			em.persist(msg);
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
		read(emf);
	}
	
	
}
