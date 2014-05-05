package dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import model.DomainSuperClass;

/**
 * Интерфейс, определяющий общие методы для всех интерфейсов DAO (Data Access
 * Object - Объект доступа к данным). Интерфейс шаблонируется классом объектов,
 * которые будут участвовать в операциях с БД.
 * 
 * @author bsa
 * 
 * @param <T>
 *            Класс объектов, которые участвуют в операциях с базой данных
 */
public interface IGenericDao<T extends DomainSuperClass> {
	
	/**
	 * Находим объект по его уникальному идентификатору
	 * 
	 * @param id
	 *            Уникальный идентификатор объекта
	 * @return Найденный объект или <code>null</code>
	 * @throws PersistenceException 
	 */
	T findById(int id) throws PersistenceException;

	/**
	 * Получаем коллекцию всех объектов
	 * 
	 * @return Коллекция всех объектов
	 * @throws PersistenceException 
	 */
	Collection<T> findAll() throws PersistenceException;

	/**
	 * Сохраняем новый объект в базу данных.
	 * 
	 * @param entity
	 *            Объект для сохранения
	 * @return Обработанный объект
	 * @throws PersistenceException 
	 */
	T save(T entity) throws PersistenceException;

	/**
	 * Удаление объекта из базы данных
	 * 
	 * @param entity
	 *            Объект для удаления
	 * @throws PersistenceException 
	 */
	void delete(T entity) throws PersistenceException;

	/**
	 * Получаем количество всех объектов
	 * @return Количество всех объектов
	 * @throws PersistenceException
	 */
	public Long getAllCount() throws PersistenceException;

	/**
	 * Удаление объекта из базы данных
	 * 
	 * @param entityId
	 *            Уникальный идентификатор объекта
	 * @throws PersistenceException 
	 */
	void delete(Integer entityId) throws PersistenceException;

}
