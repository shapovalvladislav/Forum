package service;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.PersistenceException;

import exceptions.ServiceException;
import model.Profile;
import model.Section;
import model.Topic;

public interface ITopicService extends IGenericService<Topic> {
	public Collection<Topic> findBySection(Integer sectionId) throws ServiceException;
	public Profile getLastMsgProfile(Topic topic);
	public Timestamp getLastMsgDate(Topic topic);
}
