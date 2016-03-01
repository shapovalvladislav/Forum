package ua.net.forum.daoImpl;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import ua.net.forum.dao.IMessageDao;
import ua.net.forum.model.DBQueries;
import ua.net.forum.model.Message;

public class MessageDaoJpa extends GenericDaoJpa<Message> implements IMessageDao {

    public MessageDaoJpa(EntityManagerFactory emf) {
        super(Message.class, emf);
    }

    public Collection<Message> findByTopic(Integer topicId) throws PersistenceException {
        return executeQuery(DBQueries.GET_MESSAGES_BY_TOPIC, true, false, topicId);
    }

}
