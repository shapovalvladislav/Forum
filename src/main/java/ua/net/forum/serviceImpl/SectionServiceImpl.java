package ua.net.forum.serviceImpl;

import java.sql.Timestamp;

import ua.net.forum.dao.DaoFactory;
import ua.net.forum.dao.ISectionDao;
import ua.net.forum.model.Message;
import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;
import ua.net.forum.model.Topic;
import ua.net.forum.service.ISectionService;
import ua.net.forum.service.ServiceFactory;

public class SectionServiceImpl extends GenericServiceImpl<Section> implements ISectionService {

    protected ISectionDao sectionDao;

    @Override
    protected Section createNewEntityFromTheOneToAdd(Section newEntity) {
        Section section = new Section();
        section.setName(newEntity.getName());
        section.setDescription(newEntity.getDescription());
        section.setProfile(newEntity.getProfile());
        section.setTopics(newEntity.getTopics());

        return section;
    }

    @Override
    protected void initDaoInterface() {
        sectionDao = DaoFactory.OPENJPA.getSectionDao();
        dao = sectionDao;
    }

    @Override
    protected void updateEntityWithOneNewValues(Section savedEntity, Section newEntity) {

        if (newEntity.getName() != null)
            savedEntity.setName(newEntity.getName());

        if (newEntity.getDescription() != null)
            savedEntity.setDescription(newEntity.getDescription());

        if (newEntity.getProfile() != null)
            savedEntity.setProfile(newEntity.getProfile());

        if (newEntity.getTopics() != null)
            savedEntity.setTopics(newEntity.getTopics());
    }

    public int getTopicCount(Section section) {
        return section.getTopics().size();
    }

    public int getMsgCount(Section section) {
        int msgCount = 0;
        for (Topic topic : section.getTopics() )
            msgCount += topic.getMsgCount();
        return msgCount;
    }

    public Timestamp getLastMsgDate(Section section) {
        ISectionService service = ServiceFactory.DEFAULT.getSectionService();
        Timestamp lastMsgDate = null;
        for (Topic topic : section.getTopics() ) {
            for (Message msg : topic.getMessages()) {
                Timestamp curMsgDate = msg.getDate();
                if (lastMsgDate == null || curMsgDate.after(lastMsgDate)) {
                    lastMsgDate = curMsgDate;
                    continue;
                }
            }
        }
        return lastMsgDate;
    }

    public Profile getLastMsgProfile(Section section) {
        ISectionService service = ServiceFactory.DEFAULT.getSectionService();
        Profile p = null;
        Timestamp lastMsgDate = null;
        for (Topic topic : section.getTopics() ) {
            for (Message msg : topic.getMessages()) {
                Timestamp curMsgDate = msg.getDate();
                if (lastMsgDate == null || curMsgDate.after(lastMsgDate)) {
                    lastMsgDate = curMsgDate;
                    p = msg.getProfile();
                    continue;
                }
            }
        }
        return p;
    }
}
