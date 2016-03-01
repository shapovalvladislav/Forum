package ua.net.forum.dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import ua.net.forum.model.Topic;

public interface ITopicDao extends IGenericDao<Topic> {

    public Collection<Topic> findBySection(Integer sectionId) throws PersistenceException;

}
