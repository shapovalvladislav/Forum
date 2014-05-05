package dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import model.Section;
import model.Topic;

/**
 * Интерфейс слоя dao для работы с классом {@link Section}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface ITopicDao extends IGenericDao<Topic> {
	
	public Collection<Topic> findBySection(Integer sectionId) throws PersistenceException;
	
}
