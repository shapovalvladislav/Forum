package dao;

import javax.persistence.PersistenceException;

import model.Section;
import model.User;

/**
 * Интерфейс слоя dao для работы с классом {@link Section}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface IUserDao extends IGenericDao<User> {
	
	public User findByLogin(String login) throws PersistenceException;
	public User findByLoginAndPassword(String login, String password) throws PersistenceException;
	
}
