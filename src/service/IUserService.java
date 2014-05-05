package service;

import javax.persistence.PersistenceException;

import exceptions.ServiceException;
import model.Section;
import model.User;

public interface IUserService extends IGenericService<User> {
	public User findByLogin(String login) throws ServiceException;
	public User findByLoginAndPassword(String login, String password) throws ServiceException;
	public boolean isUserExists(String login, String password);

}
