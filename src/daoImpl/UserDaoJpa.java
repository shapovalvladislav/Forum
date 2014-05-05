package daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import model.DBQueries;
import model.Section;
import model.User;
import dao.ISectionDao;
import dao.IUserDao;

public class UserDaoJpa extends GenericDaoJpa<User> implements IUserDao {

	public UserDaoJpa(EntityManagerFactory emf) {
		super(User.class, emf);
	}

	@Override
	public User findByLogin(String login) throws PersistenceException {
		return executeQuery(DBQueries.GET_USER_BY_LOGIN, true, true, login);
	}

	@Override
	public User findByLoginAndPassword(String login, String password)
			throws PersistenceException {
		return executeQuery(DBQueries.GET_USER_BY_LOGIN_AND_PASSWORD, true, true, login, password);
	}

}
