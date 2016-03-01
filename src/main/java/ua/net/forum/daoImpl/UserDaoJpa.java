package ua.net.forum.daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import ua.net.forum.dao.IUserDao;
import ua.net.forum.model.DBQueries;
import ua.net.forum.model.User;

public class UserDaoJpa extends GenericDaoJpa<User> implements IUserDao {

    public UserDaoJpa(EntityManagerFactory emf) {
        super(User.class, emf);
    }

    public User findByLogin(String login) throws PersistenceException {
        return executeQuery(DBQueries.GET_USER_BY_LOGIN, true, true, login);
    }

    public User findByLoginAndPassword(String login, String password)
            throws PersistenceException {
        return executeQuery(DBQueries.GET_USER_BY_LOGIN_AND_PASSWORD, true, true, login, password);
    }

}
