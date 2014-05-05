package dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import model.Message;
import model.Section;

/**
 * Интерфейс слоя dao для работы с классом {@link Section}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface IMessageDao extends IGenericDao<Message> {
	
	/**
	 * Получаем работника по имени
	 * @return Объект worker или null
	 * @throws PersistenceException
	 */
	public Collection<Message> findByTopic(Integer topicId) throws PersistenceException;
	
}
