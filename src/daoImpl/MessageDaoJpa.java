package daoImpl;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import model.DBQueries;
import model.Message;
import model.Section;
import dao.IMessageDao;
import dao.ISectionDao;

public class MessageDaoJpa extends GenericDaoJpa<Message> implements IMessageDao {

	public MessageDaoJpa(EntityManagerFactory emf) {
		super(Message.class, emf);
	}

	@Override
	public Collection<Message> findByTopic(Integer topicId) throws PersistenceException {
		return executeQuery(DBQueries.GET_MESSAGES_BY_TOPIC, true, false, topicId);
	}

}
