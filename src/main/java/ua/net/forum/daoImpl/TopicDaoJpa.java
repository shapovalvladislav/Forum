package ua.net.forum.daoImpl;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import ua.net.forum.dao.ITopicDao;
import ua.net.forum.model.DBQueries;
import ua.net.forum.model.Topic;

public class TopicDaoJpa extends GenericDaoJpa<Topic> implements ITopicDao {

    public TopicDaoJpa(EntityManagerFactory emf) {
        super(Topic.class, emf);
    }

    public Collection<Topic> findBySection(Integer sectionId)
            throws PersistenceException {
        return executeQuery(DBQueries.GET_TOPICS_BY_SECTION, true, false, sectionId);
    }

}
