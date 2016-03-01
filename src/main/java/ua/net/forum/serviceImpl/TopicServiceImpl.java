package ua.net.forum.serviceImpl;

import java.sql.Timestamp;
import java.util.Collection;

import ua.net.forum.dao.DaoFactory;
import ua.net.forum.dao.ITopicDao;
import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.Message;
import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;
import ua.net.forum.model.Topic;
import ua.net.forum.service.ISectionService;
import ua.net.forum.service.ITopicService;
import ua.net.forum.service.ServiceFactory;

public class TopicServiceImpl extends GenericServiceImpl<Topic> implements ITopicService {

    protected ITopicDao topicDao;

    public Collection<Topic> findBySection(Integer sectionId)
            throws ServiceException {
        ServiceFactory factory = ServiceFactory.DEFAULT;
        ISectionService sectionService = factory.getSectionService();
        Section section = sectionService.getEntityById(sectionId);

        return section.getTopics();
    }

    @Override
    protected void initDaoInterface() {
        topicDao = DaoFactory.OPENJPA.getTopicDao();
        dao = topicDao;
    }

    @Override
    protected Topic createNewEntityFromTheOneToAdd(Topic newEntity) {
        Topic topic = new Topic();
        topic.setName(newEntity.getName());
        topic.setProfile(newEntity.getProfile());
        topic.setMessages(newEntity.getMessages());
        topic.setLastChange(newEntity.getLastChange());
        topic.setSectionBean(newEntity.getSectionBean());
        topic.setMsgCount(newEntity.getMsgCount());
        return topic;
    }

    @Override
    protected void updateEntityWithOneNewValues(Topic savedEntity,
            Topic newEntity) {
        if (newEntity.getName() != null)
            savedEntity.setName(newEntity.getName());
        if (newEntity.getProfile() != null)
            savedEntity.setProfile(newEntity.getProfile());
        if (newEntity.getMessages() != null)
            savedEntity.setMessages(newEntity.getMessages());
        if (newEntity.getSectionBean() != null)
            savedEntity.setSectionBean(newEntity.getSectionBean());
        if (newEntity.getMsgCount() != null)
            savedEntity.setMsgCount(newEntity.getMsgCount());
        if (newEntity.getLastChange() != null)
            savedEntity.setLastChange(newEntity.getLastChange());
    }

    public Profile getLastMsgProfile (Topic topic) {
        Timestamp lastMsgDate = null;
        Profile lastMsgProfile = null;
        for (Message msg : topic.getMessages()) {

            Timestamp curMsgDate = msg.getDate();
            if (lastMsgDate == null || curMsgDate.after(lastMsgDate)) {
                lastMsgDate = curMsgDate;
                lastMsgProfile = msg.getProfile();
                continue;
            }
        }

        return lastMsgProfile;
    }

    public Timestamp getLastMsgDate(Topic topic) {
        Timestamp lastMsgDate = null;
        for (Message msg : topic.getMessages()) {
            Timestamp curMsgDate = msg.getDate();
            if (lastMsgDate == null || curMsgDate.after(lastMsgDate)) {
                lastMsgDate = curMsgDate;
                continue;
            }
        }
        return lastMsgDate;
    }

}
