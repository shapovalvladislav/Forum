package serviceImpl;

import service.IForbiddenWordService;
import service.IMessageService;
import service.IProfileService;
import service.IRoleService;
import service.ISectionService;
import service.ITopicService;
import service.IUserService;
import service.ServiceFactory;

/**
 * Реализация фабрики серсисов
 * 
 * @author Prepodi
 */
public class ServiceFactoryImpl extends ServiceFactory {

	@Override
	public ISectionService getSectionService() {
		return new SectionServiceImpl();
	}

	@Override
	public ITopicService getTopicService() {
		return new TopicServiceImpl();
	}

	@Override
	public IMessageService getMessageService() {
		return new MessageServiceImpl();
	}

	@Override
	public IProfileService getProfileService() {
		return new ProfileServiceImpl();
	}

	@Override
	public IUserService getUserService() {
		return new UserServiceImpl();
	}

	@Override
	public IRoleService getRoleService() {
		return new RoleServiceImpl();
	}

	@Override
	public IForbiddenWordService getForbiddenWordService() {
		return new ForbiddenWordServiceImpl();
	}
	
}
