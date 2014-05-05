package serviceImpl;

import java.sql.Timestamp;
import java.util.Collection;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import dao.DaoFactory;
import dao.ISectionDao;
import dao.ITopicDao;
import daoImpl.ProfileDaoJpa;
import model.Message;
import model.Profile;
import model.Section;
import model.Topic;
import exceptions.ServiceException;
import service.IProfileService;
import service.ISectionService;
import service.ITopicService;
import service.ServiceFactory;

public class TopicServiceImpl extends GenericServiceImpl<Topic> implements ITopicService {

	protected ITopicDao topicDao;
	
	@Override
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

	@Override
	public Profile getLastMsgProfile (Topic topic) {
		Timestamp lastMsgDate = null;
		Profile lastMsgProfile = null;
		int profileId = 0;
		for (Message msg : topic.getMessages()) {
			
			Timestamp curMsgDate = msg.getDate();
			if (lastMsgDate == null || curMsgDate.after(lastMsgDate)) {
				lastMsgDate = curMsgDate;
				lastMsgProfile = msg.getProfile();
				profileId = lastMsgProfile.getId();
				continue;
			}
		}
		System.out.println("ID EQUALS " + profileId);
		//lastMsgProfile = ServiceFactory.DEFAULT.getProfileService().getEntityById(profileId);
		return lastMsgProfile;
	}

	@Override
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
