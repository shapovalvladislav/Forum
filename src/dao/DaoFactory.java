package dao;

import daoImpl.DaoFactoryJpa;

/**
 * Класс абстрактной фабрики для DAO объектов.
 * Данный класс только содержит объявления для методов, возвращающих реализации
 * DAO интерфейсов. Сами же методы определены в конкретной реализации фабрики.
 * Данный класс содержит статические методы получения используемой фабрики.
 * 
 * @author bsa
 */
public abstract class DaoFactory {
	
	/** Фабрика, для интерфейсов dao, работающих с OpenJPA */
	public static final DaoFactory OPENJPA = new DaoFactoryJpa();

	/**
	 * @return Реалізація dao
	 */
	public abstract ISectionDao getSectionDao();
	public abstract ITopicDao getTopicDao();
	public abstract IProfileDao getProfileDao();
	public abstract IUserDao getUserDao();
	public abstract IRoleDao getRoleDao();
	public abstract IMessageDao getMessageDao();
	public abstract IForbiddenWordDao getForbiddenWordDao();
}
