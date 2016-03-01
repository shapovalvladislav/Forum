package ua.net.forum.dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import ua.net.forum.model.Message;

public interface IMessageDao extends IGenericDao<Message> {

    public Collection<Message> findByTopic(Integer topicId) throws PersistenceException;

}
