package ua.net.forum.db;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.DBQueries;
import model.Message;
import model.Profile;
import model.Role;
import model.Section;
import model.Topic;
import model.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBQuery {
	
	private static final String SELECT_PROFILE_BY_ID = "SELECT p FROM Profile p WHERE p.id=:id";
	private static final String SELECT_ROLE_BY_NAME = "SELECT r FROM Role r WHERE r.name=:name";
	private static final String SELECT_SECTION = "SELECT x FROM Section x";
	private static final String SELECT_TOPIC_BY_SECTION = "SELECT t FROM Topic t WHERE t.sectionBean.id=";
	private static final String SELECT_MESSAGES_BY_TOPIC = "SELECT x FROM Message x WHERE x.topicBean.id=:topic ORDER BY x.date";
	private static final String SELECT_TOP_USERS = "SELECT p FROM Profile p ORDER BY p.msgCount DESC";
	private static final String SELECT_USER_BY_LOGIN = "SELECT u FROM User u WHERE u.login=:login";
	private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT x FROM User x WHERE x.login=:login AND x.password=:password";
	private static final String SELECT_TOPIC_BY_ID = "SELECT x FROM Topic x WHERE x.id=:topicId";
	
	
	private static Logger log = LogManager.getLogger(DBQuery.class.getName());
	@PersistenceContext 
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
	
	@SuppressWarnings("unchecked")
	public static Collection<Object> getTopProfiles() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createNativeQuery(DBQueries.GET_TOP_PROC);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return (Collection<Object>)list;
	}

	public static boolean canCreateTopic(int profileId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		boolean result = false;
		try {
			Query query = em.createNativeQuery(DBQueries.CAN_CREATE_TOPIC_PROC);
			query.setParameter(1, profileId);
			list = query.getResultList();
			result = (Boolean) list.get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}
	
	public static int getUserIdByProfileId(int profileId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		int result = 0;
		try {
			Query query = em.createNativeQuery(DBQueries.GET_USER_ID_BY_PROFILE_ID_PROC);
			query.setParameter(1, profileId);
			list = query.getResultList();
			result = (Integer)list.get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}
	
	
	public static Profile getProfileById(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Profile p = null;
		try {
			Query query = em.createQuery(SELECT_PROFILE_BY_ID).setMaxResults(1);
			query.setParameter("id", id);
			p = (Profile) query.getResultList().get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return p;
	}
	
	
	public static boolean userExists(String login, String password) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		try {
			Query query = em.createQuery(SELECT_USER_BY_LOGIN_AND_PASSWORD);
			query.setParameter("login", login);
			query.setParameter("password", password);
			if (query.getResultList().size() == 1)
				result = true;
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}
	
	public static boolean loginExists(String login) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		boolean result = false;
		try {
			Query query = em.createQuery(SELECT_USER_BY_LOGIN);
			query.setParameter("login", login);
			if (query.getResultList().size() == 1)
				result = true;
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}
	

	
	
	public static Collection<Profile> getTopUsers() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createQuery(SELECT_TOP_USERS).setMaxResults(10);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return (Collection<Profile>) list;
		}
	}
	
	public static Collection<Message> getMessages(String topic) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createQuery(SELECT_MESSAGES_BY_TOPIC);
			query.setParameter("topic", Integer.parseInt(topic));
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return (Collection<Message>) list;
		}
	}
	

	public static Topic getTopicById(String topic) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Topic newTopic = null;
		try {
			Query query = em.createQuery(SELECT_TOPIC_BY_ID);
			query.setParameter("topicId", Integer.parseInt(topic));
			newTopic = (Topic) query.getResultList().get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return newTopic;
		}
	}
	
	
	public static User getUserByLogin(String login) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User user = null;
		try {
			Query query = em.createQuery(SELECT_USER_BY_LOGIN);
			query.setParameter("login",login);
			user = (User) query.getResultList().get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return user;
		}
	}
	
	
	public static byte[] getIcon(String profileId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		byte[] icon = null;
		try {
			Query query = em.createQuery("SELECT p FROM Profile p WHERE p.id= " + profileId).setMaxResults(1);
			
			Profile p = (Profile) query.getResultList().get(0);
			icon = p.getIcon();
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return icon;
		}
	}
	
	public static Collection<Topic> getTopics(String section) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<?> list = null;
		try {
			Query query = em.createQuery(SELECT_TOPIC_BY_SECTION + section);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return (Collection<Topic>) list;
		}
	}
	
	public static Collection<Section> getSections() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Section> list = null;
		try {
			Query query = em.createNamedQuery("getSections");
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return list;
		}
	}

	public static Role getUserRole() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Role role = null;
		try {
			Query query = em.createQuery(SELECT_ROLE_BY_NAME).setMaxResults(1);
			query.setParameter("name", "user");
			role = (Role) query.getResultList().get(0);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return role;
		}
	}
	
	public static Profile getProfileByLogin(String login) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Profile profile = null;
		try {
			Query query = em.createQuery(SELECT_USER_BY_LOGIN).setMaxResults(1);
			query.setParameter("login", login);
			User user = (User) query.getResultList().get(0);
			profile = user.getProfileBean();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
			return profile;
		}
		
	}
	
	public static void registerUser(User u) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
	
	public static void updateProfile(Profile p) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Profile profile = em.find(Profile.class, p.getId());
			profile.setAbout(p.getAbout());
			profile.setBirthDate(p.getBirthDate());
			profile.setEmail(p.getEmail());
			profile.setFullName(p.getFullName());
			profile.setIcon(p.getIcon());
			profile.setNickName(p.getNickName());
			profile.setSex(p.getSex());
			em.merge(profile);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
	
	public static void updateUser(User u) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			User user = em.find(User.class, u.getId());
			user.setPassword(u.getPassword());
			user.setRoleBean(u.getRoleBean());
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
	
	
	public static void addMessage(int topicId, String content, Profile profile) {
		Message msg = new Message();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Query query = em.createQuery("SELECT t FROM Topic t WHERE t.id=:id").setMaxResults(1);
			query.setParameter("id", topicId);
			Topic topic = (Topic) query.getResultList().get(0);
			msg.setTopicBean(topic);
			msg.setProfile(profile);
			msg.setContent(content);
			msg.setDate(new Timestamp(new Date().getTime()));
			em.persist(msg);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}
	}
	
	
}
