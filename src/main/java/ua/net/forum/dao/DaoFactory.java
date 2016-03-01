package ua.net.forum.dao;

import ua.net.forum.daoImpl.DaoFactoryJpa;

public abstract class DaoFactory {

    public static final DaoFactory OPENJPA = new DaoFactoryJpa();

    public abstract ISectionDao getSectionDao();
    public abstract ITopicDao getTopicDao();
    public abstract IProfileDao getProfileDao();
    public abstract IUserDao getUserDao();
    public abstract IRoleDao getRoleDao();
    public abstract IMessageDao getMessageDao();
    public abstract IForbiddenWordDao getForbiddenWordDao();
}
