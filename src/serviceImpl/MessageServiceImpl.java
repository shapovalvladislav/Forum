package serviceImpl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.DaoFactory;
import dao.IMessageDao;
import dao.ISectionDao;
import model.Message;
import model.Profile;
import model.Section;
import model.Topic;
import exceptions.ServiceException;
import service.IMessageService;
import service.ISectionService;
import service.ITopicService;
import service.ServiceFactory;
import serviceImpl.GenericServiceImpl;

public class MessageServiceImpl extends GenericServiceImpl<Message> implements
		IMessageService {

	protected IMessageDao messageDao;
	
	@Override
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
