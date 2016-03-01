package ua.net.forum.serviceImpl;

import ua.net.forum.dao.DaoFactory;
import ua.net.forum.dao.IUserDao;
import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.User;
import ua.net.forum.service.IUserService;

public class UserServiceImpl extends GenericServiceImpl<User> implements IUserService {

    protected IUserDao userDao;

    public User findByLogin(String login) throws ServiceException {
        return userDao.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password)
            throws ServiceException {
        return userDao.findByLoginAndPassword(login, password);
    }

    public boolean isUserExists(String login, String password) {
        return userDao.findByLoginAndPassword(login, password) != null;
    }

    @Override
    protected void initDaoInterface() {
        userDao = DaoFactory.OPENJPA.getUserDao();
        dao = userDao;
    }

    @Override
    protected User createNewEntityFromTheOneToAdd(User newEntity) {
        User u = new User();
        u.setLogin(newEntity.getLogin());
        u.setPassword(newEntity.getPassword());
        u.setProfileBean(newEntity.getProfileBean());
        u.setRoleBean(newEntity.getRoleBean());
        return u;
    }

    @Override
    protected void updateEntityWithOneNewValues(User savedEntity, User newEntity) {
        if (newEntity.getLogin() != null)
            savedEntity.setLogin(newEntity.getLogin());
        if (newEntity.getPassword() != null)
            savedEntity.setPassword(newEntity.getPassword());
        if (newEntity.getProfileBean() != null)
            savedEntity.setProfileBean(newEntity.getProfileBean());
        if (newEntity.getRoleBean() != null)
            savedEntity.setRoleBean(newEntity.getRoleBean());
    }


}
