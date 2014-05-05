package service;

import serviceImpl.ServiceFactoryImpl;

/**
 * Класс абстрактной фабрики для сервисов.
 * Данный класс только содержит объявления для методов, возвращающих реализации
 * интерфейсов сервисов. Сами же методы определены в конкретной реализации фабрики.
 * Данный класс содержит статические методы получения используемой фабрики.
 * 
 * @author bsa
 */
public abstract class ServiceFactory {

	/** Фабрика, используемая по умолчанию */
	public static final ServiceFactory DEFAULT = new ServiceFactoryImpl();
	
	/**
	 * @return Реализация сервиса для {@link Department}
	 */
	public abstract ISectionService getSectionService();

	/**
	 * @return Реализация сервиса для {@link JobPosition}
	 */
	public abstract ITopicService getTopicService();

	/**
	 * @return Реализация сервиса для {@link Job}
	 */
	public abstract IMessageService getMessageService();
	
	/**
	 * @return Реализация сервиса для {@link Worker}
	 */
	public abstract IProfileService getProfileService();
	
	/**
	 * @return Реализация сервиса для {@link JobHistory}
	 */
	public abstract IUserService getUserService();
	
	/**
	 * @return Реализация сервиса для {@link JobHistory}
	 */
	public abstract IRoleService getRoleService();
	
	/**
	 * @return Реализация сервиса для {@link JobHistory}
	 */
	public abstract IForbiddenWordService getForbiddenWordService();
}
