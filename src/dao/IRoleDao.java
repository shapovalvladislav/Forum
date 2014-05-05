package dao;

import javax.persistence.PersistenceException;

import model.Role;
import model.Section;

/**
 * Интерфейс слоя dao для работы с классом {@link Section}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface IRoleDao extends IGenericDao<Role> {
	
	/**
	 * Получаем работника по имени
	 * @return Объект worker или null
	 * @throws PersistenceException
	 */
	public Role findByName(String name) throws PersistenceException;
	
}
