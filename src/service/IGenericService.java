package service;

import java.util.Collection;

import exceptions.ServiceException;
import model.DomainSuperClass;


/**
 * Интерфейс, определяющий общие методы для всех интерфейсов сервисов.
 * Интерфейс шаблонируется классом объектов, которые будут участвовать в операциях.
 * Данный интерфейс содержит CRUD (create, read, update, delete) операции.
 * 
 * @author Prepodi
 * 
 * @param <T>
 *            Класс объектов домена, для которых будет использоваться данный сервис
 */
public interface IGenericService<T extends DomainSuperClass> {

	/**
	 * Добавление сущности в базу
	 * 
	 * @param entity
	 *            Сущность типа T для добавления
	 * @return Уникальный идентификатор добавленной сущности
	 * @throws ServiceException
	 *             Если будет ошибка при выполнении метода
	 */
	public Integer addEntity(T entity);

	/**
	 * Удаление сущности из базы
	 * 
	 * @param id
	 *            Идентификатор сущности для удаления
	 * @throws ServiceException
	 *             ServiceException Если будет ошибка при выполнении метода
	 */
	public void delEntity(Integer id);

	/**
	 * Получение сущности по ее уникальному идентификатору
	 * 
	 * @param id
	 *            Идентификатор сущности для поиска
	 * @return Сущность типа T или null
	 * @throws ServiceException
	 *             Если будет ошибка при выполнении метода
	 */
	public T getEntityById(int id);

	/**
	 * Получаем все сущности
	 * 
	 * @return Коллекция всех сущностей типа T
	 * @throws ServiceException
	 *             Если будет ошибка при выполнении метода
	 */
	public Collection<T> getAllEntites();

	/**
	 * Обновляем сохраненную сущность с id новыми данными из entity
	 * 
	 * @param id
	 *            Идентификатор сущности для обновления
	 * @param entity
	 *            Сущность типа T с новой информацией
	 * @throws ServiceException
	 *             ServiceException Если будет ошибка при выполнении метода
	 */
	public void updateEntity(Integer id, T entity);

}
