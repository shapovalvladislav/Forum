package serviceImpl;

import java.util.Collection;

import dao.IGenericDao;
import exceptions.ServiceException;
import service.IGenericService;
import model.DomainSuperClass;

/**
 * Класс, реализующий все методы интерфейса {@link IGenericService}, используя
 * методы {@link IGenericDAO} для операций с dao. Класс шаблонируется типом
 * объектов, с которыми он будет работать, и данный тип дакже используется в
 * dao. Данный класс определяет некоторые методы, которые обязательно должны
 * быть реализованы в подклассах. Это такие методы, которые позволяют обновить
 * объект новой информацией и проинициализировать интерфейс {@link IGenericDAO}.
 * 
 * @author Yuriy Tkach
 * 
 * @param <T>
 *            Класс объектов, с которыми будет идти работа
 */
public abstract class GenericServiceImpl<T extends DomainSuperClass> implements
		IGenericService<T> {

	/** Dao интерфейс для работы */
	protected IGenericDao<T> dao;

	/**
	 * Пустой конструктор, который получает dao
	 */
	public GenericServiceImpl() {
		super();
		initDaoInterface();
	}

	/** Этот метод устанавливает {@link #dao} интерфейс для работы */
	protected abstract void initDaoInterface();

	/**
	 * Метод, который вызывается перед тем, как сущность добавляют или
	 * обновляют. Данный метод можно переопределить в подклассе для выполнения
	 * каких-либо проверок.
	 * 
	 * @param entity
	 *            Сущность, которая будет обновлена или добавлена
	 * @throws ServiceException
	 *             Если будет ошибка
	 */
	protected void beforeEntityAddUpdate(T entity) {
	}

	/**
	 * Добавление новой сущности. Вызываем
	 * {@link #beforeEntityAddUpdate(DomainSuperClass)} перед выполнением
	 * каких-либо действий. После этого вызываем
	 * {@link #createNewEntityFromTheOneToAdd(DomainSuperClass)} для получения
	 * новой сущности из переданной в метод информации. Вызываем метод
	 * {@link IGenericDao#save(DomainSuperClass)} для сохранения сущности.
	 * 
	 * @param entity
	 *            Сущность типа T для добавления
	 * 
	 * @see IGenericService#addEntity(DomainSuperClass)
	 */
	public Integer addEntity(T entity) {

		beforeEntityAddUpdate(entity);

		try {

			// Создаем новую сущность, которая будет сохранена в бд
			T newEntity = entity;

			newEntity = dao.save(newEntity);

			if (newEntity != null)
				return newEntity.getId();
			else
				throw new ServiceException("Failed to add "
						+ entity.getClass().getSimpleName() + "");
		} catch (Exception e) {
			// Отлавливаем все runtime exceptions, которые могут произойти
			// при операциях. Возвращаем наш service exception в таком случае
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Создаем новую сущность на базе той информации, которая передана в
	 * newEntity
	 * 
	 * @param newEntity
	 *            Сущность, с информацией, которую надо сохранить
	 * @return Новая созданная сущность
	 */
	protected abstract T createNewEntityFromTheOneToAdd(T newEntity);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.cn.stu.yet.gradingSys.services.IGenericService#delEntity(java.lang.Long)
	 */
	public void delEntity(Integer id) throws SecurityException {

		// Получаем сущность по id
		T savedEntity = getEntityById(id);

		try {
			// Удаляем сущность из БД
			dao.delete(savedEntity);
		} catch (Exception e) {
			// Отлавливаем все runtime exceptions, которые могут произойти
			// при операциях. Возвращаем наш service exception в таком случае
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.cn.stu.yet.gradingSys.services.IGenericService#getAllEntites()
	 */
	public Collection<T> getAllEntites() throws SecurityException {
		try {

			// Получаем все сущности
			return dao.findAll();

		} catch (Exception e) {
			// Отлавливаем все runtime exceptions, которые могут произойти
			// при операциях. Возвращаем наш service exception в таком случае
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.cn.stu.yet.gradingSys.services.IGenericService#getEntityById(java.lang.Long)
	 */
	public T getEntityById(int id) {

		try {

			// Получаем сущность по id
			T savedEntity = dao.findById(id);
			
			// Проверяем, получили ли мы сущность, которая не NULL и у которой
			// есть id
			if ((savedEntity == null) || (savedEntity.getId() == 0))
				throw new Exception("Incorrect id " + id);

			return savedEntity;
		} catch (Exception e) {
			// Отлавливаем все runtime exceptions, которые могут произойти
			// при операциях. Возвращаем наш service exception в таком случае
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Обновление сущности. Вызываем
	 * {@link #beforeEntityAddUpdate(DomainSuperClass)} перед выполнением
	 * каких-либо действий. После этого вызываем
	 * {@link #updateEntityWithOneNewValues(DomainSuperClass, DomainSuperClass)}
	 * для обновления сущности информацией из переданной в метод сущности.
	 * Вызываем метод {@link IGenericDao#save(DomainSuperClass)} для сохранения
	 * сущности.
	 * 
	 * @see IGenericService#updateEntity(Long, DomainSuperClass)
	 */
	public void updateEntity(Integer id, T entity) throws
			SecurityException {

		beforeEntityAddUpdate(entity);

		try {

			// Getting saved entity by id
			T savedEntity = getEntityById(id);

			// Setting new values for saved entity
			updateEntityWithOneNewValues(savedEntity, entity);

			// Saving saved entity object
			savedEntity = dao.save(savedEntity);

			if (savedEntity == null)
				throw new ServiceException("Failed to update "
						+ entity.getClass().getSimpleName() + " with id " + id);
		} catch (Exception e) {
			// Отлавливаем все runtime exceptions, которые могут произойти
			// при операциях. Возвращаем наш service exception в таком случае
			e.printStackTrace();
		}
	}

	/**
	 * Обновляем инфомрацию для savedEntity из newEntity
	 * 
	 * @param savedEntity
	 *            Сохраненная сущность
	 * @param newEntity
	 *            Сущность с новой информацией
	 */
	protected abstract void updateEntityWithOneNewValues(T savedEntity,
			T newEntity);

}
