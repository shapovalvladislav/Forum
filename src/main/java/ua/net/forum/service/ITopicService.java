package ua.net.forum.service;

import java.sql.Timestamp;
import java.util.Collection;

import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.Profile;
import ua.net.forum.model.Topic;

public interface ITopicService extends IGenericService<Topic> {
    public Collection<Topic> findBySection(Integer sectionId) throws ServiceException;
    public Profile getLastMsgProfile(Topic topic);
    public Timestamp getLastMsgDate(Topic topic);
}
