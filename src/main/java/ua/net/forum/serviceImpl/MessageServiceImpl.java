package ua.net.forum.serviceImpl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ua.net.forum.dao.DaoFactory;
import ua.net.forum.dao.IMessageDao;
import ua.net.forum.dao.ISectionDao;
import ua.net.forum.model.Message;
import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;
import ua.net.forum.model.Topic;
import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.service.IMessageService;
import ua.net.forum.service.ISectionService;
import ua.net.forum.service.ITopicService;
import ua.net.forum.service.ServiceFactory;
import ua.net.forum.serviceImpl.GenericServiceImpl;

public class MessageServiceImpl extends GenericServiceImpl<Message> implements
IMessageService {

    protected IMessageDao messageDao;

    public Collection<Message> findByTopic(Topic topic) {
        return messageDao.findByTopic(topic.getId());
    }

    @Override
    protected void initDaoInterface() {
        messageDao = DaoFactory.OPENJPA.getMessageDao();
        dao = messageDao;
    }

    @Override
    protected Message createNewEntityFromTheOneToAdd(Message newEntity) {
        Message msg = new Message();
        msg.setContent(newEntity.getContent());
        msg.setDate(newEntity.getDate());
        msg.setProfile(newEntity.getProfile());
        msg.setTopicBean(newEntity.getTopicBean());
        return msg;
    }

    @Override
    protected void updateEntityWithOneNewValues(Message savedEntity,
            Message newEntity) {
        if (newEntity.getContent() != null)
            savedEntity.setContent(newEntity.getContent());
        if (newEntity.getDate() != null)
            savedEntity.setDate(newEntity.getDate());
        if (newEntity.getProfile() != null)
            savedEntity.setProfile(newEntity.getProfile());
        if (newEntity.getTopicBean() != null)
            savedEntity.setTopicBean(newEntity.getTopicBean());
    }

}
