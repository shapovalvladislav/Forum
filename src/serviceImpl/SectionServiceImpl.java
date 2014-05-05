package serviceImpl;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.PersistenceException;

import model.Message;
import model.Profile;
import model.Section;
import model.Topic;
import model.User;
import dao.DaoFactory;
import dao.ISectionDao;
import exceptions.ServiceException;
import service.ISectionService;
import service.ServiceFactory;

public class SectionServiceImpl extends GenericServiceImpl<Section> implements ISectionService {
	
	protected ISectionDao sectionDao;

	/* (non-Javadoc)
	 * @see ua.cn.stu.oop.example.service.dist.DistGenericService#createNewEntityFromTheOneToAdd(ua.cn.stu.oop.example.domain.DomainSuperClass)
	 */
	@Override
	protected Section createNewEntityFromTheOneToAdd(Section newEntity) {
		Section section = new Section();
		section.setName(newEntity.getName());
		section.setDescription(newEntity.getDescription());
		section.setProfile(newEntity.getProfile());
		section.setTopics(newEntity.getTopics());
		return section;
	}

	/* (non-Javadoc)
	 * @see ua.cn.stu.oop.example.service.dist.DistGenericService#initDaoInterface()
	 */
	@Override
	protected void initDaoInterface() {
		sectionDao = DaoFactory.OPENJPA.getSectionDao();
		dao = sectionDao;
	}

	/* (non-Javadoc)
	 * @see ua.cn.stu.oop.example.service.dist.DistGenericService#updateEntityWithOneNewValues(ua.cn.stu.oop.example.domain.DomainSuperClass, ua.cn.stu.oop.example.domain.DomainSuperClass)
	 */
	@Override
	protected void updateEntityWithOneNewValues(Section savedEntity,
			Section newEntity) {

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
