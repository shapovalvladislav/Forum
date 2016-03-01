package ua.net.forum.daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;

import ua.net.forum.dao.DaoFactory;
import ua.net.forum.dao.IForbiddenWordDao;
import ua.net.forum.dao.IMessageDao;
import ua.net.forum.dao.IProfileDao;
import ua.net.forum.dao.IRoleDao;
import ua.net.forum.dao.ISectionDao;
import ua.net.forum.dao.ITopicDao;
import ua.net.forum.dao.IUserDao;

public class DaoFactoryJpa extends DaoFactory {

    private static final String PERSISTENT_UNIT = "Forum";

    @PersistenceUnit
    private static EntityManagerFactory emf;

    public DaoFactoryJpa(){
        super();
        try {
            initEntityManagerFactory();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }

    private void initEntityManagerFactory() throws PersistenceException {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
        } catch (Exception e) {
            throw new PersistenceException("Failed to create EntityManagerFactory", e);
        }

        if (emf == null) {
            throw new PersistenceException(
                    "EntityManagerFactory was not created. See log for details.");
        }
    }

    @Override
    public ISectionDao getSectionDao() {
        return new SectionDaoJpa(emf);
    }

    @Override
    public ITopicDao getTopicDao() {
        return new TopicDaoJpa(emf);
    }

    @Override
    public IProfileDao getProfileDao() {
        return new ProfileDaoJpa(emf);
    }

    @Override
    public IUserDao getUserDao() {
        return new UserDaoJpa(emf);
    }

    @Override
    public IRoleDao getRoleDao() {
        return new RoleDaoJpa(emf);
    }

    @Override
    public IMessageDao getMessageDao() {
        return new MessageDaoJpa(emf);
    }

    @Override
    public IForbiddenWordDao getForbiddenWordDao() {
        return new ForbiddenWordDaoJpa(emf);
    }

}
