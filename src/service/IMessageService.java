package service;

import java.util.Collection;

import javax.persistence.PersistenceException;

import exceptions.ServiceException;
import model.Message;
import model.Section;
import model.Topic;

public interface IMessageService extends IGenericService<Message> {
	public Collection<Message> findByTopic(Topic topic);
}
