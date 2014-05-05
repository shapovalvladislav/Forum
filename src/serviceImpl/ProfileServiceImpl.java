package serviceImpl;

import java.util.Collection;

import dao.DaoFactory;
import dao.IMessageDao;
import dao.IProfileDao;
import model.Profile;
import model.User;
import exceptions.ServiceException;
import service.IProfileService;
import service.IUserService;
import service.ServiceFactory;
import serviceImpl.GenericServiceImpl;

public class ProfileServiceImpl extends GenericServiceImpl<Profile> implements
		IProfileService {

	protected IProfileDao profileDao;
	
	@Override
	public Collection<Profile> findTopProfiles() throws ServiceException {
		ServiceFactory factory = ServiceFactory.DEFAULT;
		IProfileService service = factory.getProfileService();
		return service.findTopProfiles();
	}

	@Override
	protected void initDaoInterface() {
		profileDao = DaoFactory.OPENJPA.getProfileDao();
		dao = profileDao;
	}

	@Override
	protected Profile createNewEntityFromTheOneToAdd(Profile newEntity) {
		Profile p = new Profile();
		p.setFullName(newEntity.getFullName());
		p.setNickName(newEntity.getNickName());
		p.setAbout(newEntity.getAbout());
		p.setBirthDate(newEntity.getBirthDate());
		p.setEmail(newEntity.getEmail());
		p.setIcon(newEntity.getIcon());
		p.setMessages(newEntity.getMessages());
		p.setMsgCount(newEntity.getMsgCount());
		p.setSections(newEntity.getSections());
		p.setSex(newEntity.getSex());
		p.setTopicCount(newEntity.getTopicCount());
		p.setTopics(newEntity.getTopics());
		return p;
	}

	@Override
	protected void updateEntityWithOneNewValues(Profile savedEntity,
			Profile newEntity) {
		if (newEntity.getFullName() != null)
			savedEntity.setFullName(newEntity.getFullName());
		if (newEntity.getNickName() != null)
			savedEntity.setNickName(newEntity.getNickName());
		if (newEntity.getAbout() != null)
			savedEntity.setAbout(newEntity.getAbout());
		if (newEntity.getBirthDate() != null)
			savedEntity.setBirthDate(newEntity.getBirthDate());
		if (newEntity.getEmail() != null)
			savedEntity.setEmail(newEntity.getEmail());
		if (newEntity.getIcon() != null)
			savedEntity.setIcon(newEntity.getIcon());
		if (newEntity.getMessages() != null)
			savedEntity.setMessages(newEntity.getMessages());
		if (newEntity.getMsgCount() != null)
			savedEntity.setMsgCount(newEntity.getMsgCount());
		if (newEntity.getSections() != null)
			savedEntity.setSections(newEntity.getSections());
		if (newEntity.getSex() != null)
			savedEntity.setSex(newEntity.getSex());
		if (newEntity.getTopicCount() != null)
			savedEntity.setTopicCount(newEntity.getTopicCount());
		if (newEntity.getTopics() != null)
			savedEntity.setTopics(newEntity.getTopics());
	}

	@Override
	public Profile findProfileByLogin(String login) {
		IUserService userService = ServiceFactory.DEFAULT.getUserService();
		User user = null;
		try {
			user = userService.findByLogin(login);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return user.getProfileBean();
	}

}