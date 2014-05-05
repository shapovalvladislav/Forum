package daoImpl;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import model.DBQueries;
import model.Section;
import model.Topic;
import dao.ISectionDao;
import dao.ITopicDao;

/**
 * Implementation of worker dao
 * @author bsa
 */
public class TopicDaoJpa extends GenericDaoJpa<Topic> implements ITopicDao {

	public TopicDaoJpa(EntityManagerFactory emf) {
		super(Topic.class, emf);
	}

	@Override
	public Collection<Topic> findBySection(Integer sectionId)
			throws PersistenceException {
		return executeQuery(DBQueries.GET_TOPICS_BY_SECTION, true, false, sectionId);
	}

}
