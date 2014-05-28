package daoImpl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import dao.IGenericDao;
import model.DomainSuperClass;


/**
 * Класс, реализующий все методы интерфейса {@link IGenericDao} для работы с БД,
 * используя JPA.
 * 
 * @author bsa
 * 
 * @param <T>
 *            Класс объектов, которые участвуют в операциях с базой данных
 */
public abstract class GenericDaoJpa<T extends DomainSuperClass> implements
		IGenericDao<T> {

	private static final String QUERY_SELECT_ALL = "SELECT x FROM %s x";

	private static final String QUERY_COUNT_ALL = "SELECT COUNT(x) FROM %s x";

	/** Entity manager factory that will be used for creating entity managers */
	protected EntityManagerFactory entityManagerFactory;

	/** Persistent class that this dao works with */
	protected Class<T> persistentClass;

	/**
	 * Constructor with fields
	 * 
	 * @param persistentClass
	 *            Class that this dao will work with
	 * @param entityManager
	 *            EntityManager to use
	 */
	public GenericDaoJpa(Class<T> persistentClass, EntityManagerFactory emf) {
		super();
		this.persistentClass = persistentClass;
		this.entityManagerFactory = emf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.cn.stu.oop.example.dao.IGenericDao#delete(ua.cn.stu.oop.example.domain
	 * .DomainSuperClass)
	 */
	public void delete(T entity) throws PersistenceException {
		// Checking for null parameters
		if (entity == null) {
			throw new PersistenceException(
					"Entity for deleting cannot be null!");
		}

		// Creating entity manager to work with entity
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		entityManager.getTransaction().begin();

		try {

			// Finding entity by its id. If it is already loaded, it will not
			// travel to the db
			T savedEntity = entityManager.find(persistentClass, entity.getId());

			// Removing entity
			entityManager.remove(savedEntity);
			
			// Commit the transaction, which will cause the entity to
			// be stored in the database
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			
			// Catching exceptions and rollback of transaction
			entityManager.getTransaction().rollback();

			// throwing exception further
			throw new PersistenceException(e);

		} finally {

			// It is always good practice to close the EntityManager so that
			// resources are conserved.
			entityManager.close();

		}
	}
	
	/* (non-Javadoc)
	 * @see ua.cn.stu.oop.example.dao.IGenericDao#delete(java.lang.Integer)
	 */
	public void delete(Integer entityId) throws PersistenceException {
		delete(findById(entityId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.cn.stu.oop.example.dao.IGenericDao#findAll()
	 */
	public Collection<T> findAll() throws PersistenceException {
		return executeQuery(String.format(QUERY_SELECT_ALL, persistentClass.getSimpleName()), false, false);
	}
	
	/* (non-Javadoc)
	 * @see ua.cn.stu.oop.example.dao.IGenericDao#getAllCount()
	 */
	public Long getAllCount() throws PersistenceException {
		return executeQuery(String.format(QUERY_COUNT_ALL, persistentClass.getSimpleName()), false, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.cn.stu.oop.example.dao.IGenericDao#findById(java.lang.Integer)
	 */
	public T findById(int id) throws PersistenceException {
		// Checking for null parameters

		// Creating entity manager to work with entity
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		entityManager.getTransaction().begin();

		T savedEntity = null;

		try {

			// Finding entity by its id
			savedEntity = entityManager.find(persistentClass, id);

			// Commit the transaction, which will cause the entity to
			// be stored in the database
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Catching exceptions and rollback of transaction
			entityManager.getTransaction().rollback();

			// throwing exception further
		//	e.printStackTrace();

		} finally {

			// It is always good practice to close the EntityManager so that
			// resources are conserved.
			entityManager.close();

		}

		return savedEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ua.cn.stu.oop.example.dao.IGenericDao#save(ua.cn.stu.oop.example.domain
	 * .DomainSuperClass)
	 */
	public T save(T entity) throws PersistenceException {
		// Checking for null parameters
		if (entity == null) {
			throw new PersistenceException("Entity for saving cannot be null!");
		}

		// Creating entity manager to work with entity
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		entityManager.getTransaction().begin();

		T savedEntity = null;

		try {

			// Creating new entity, if id is null or merging the detached object
			// with the current state
			System.out.println("entity id: " + entity.getId());
			if (entity.getId() == 0) {
				entityManager.persist(entity);
				savedEntity = entity;
				
			} else {
				savedEntity = entityManager.merge(entity);
			}

			// Commit the transaction, which will cause the entity to
			// be stored in the database
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Catching exceptions and rollback of transaction
			entityManager.getTransaction().rollback();

			// throwing exception further
			throw new PersistenceException(e);

		} finally {

			// It is always good practice to close the EntityManager so that
			// resources are conserved.
			entityManager.close();

		}

		return savedEntity;
	}

	/**
	 * <p>
	 * This template method executes query with performing all needed
	 * operations, like creating EntityManager, creating transaction,
	 * committing, or rolling it back.
	 * </p>
	 * <p>
	 * Methods sets parameters for the query as they appear in the parameters
	 * list, by number starting from 1. So, the first parameter in your named
	 * query should be referenced as <code>?1</code>, second as <code>?2</code>
	 * and so on.
	 * </p>
	 * <p>
	 * If <code>singleResult = true</code> and no result is found, then
	 * <code>null</code> is returned.
	 * </p>
	 * <p>
	 * Be aware, that when multiple results are returned, they are being
	 * dynamically casted to <code>REZ</code> class. It should be able to cast
	 * to {@link java.util.List}. When returning single result, <code>REZ</code>
	 * should be a single persistent entity class.
	 * </p>
	 * 
	 * @param <REZ>
	 *            Class of the result
	 * @param queryOrQueryName
	 *            Query string or NamedQuery name
	 * @param namedQuery
	 * 			  Specifies, whether query is named query
	 * @param singleResult
	 *            Specifies, whether single result should be returned
	 * @param parameters
	 *            Parameters. You can specify multiple parameters separated by comma
	 * @return Result of the query
	 * @throws PersistenceException
	 *             If error occurs
	 */
	@SuppressWarnings("unchecked")
	protected <REZ> REZ executeQuery(String queryOrQueryName,
			boolean namedQuery, boolean singleResult, Object... parameters)
			throws PersistenceException {

		// Creating entity manager to work with entity
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		entityManager.getTransaction().begin();

		REZ result;

		try {

			Query q;
			
			// Creating either named or simple query
			if (namedQuery) {
				q = entityManager.createNamedQuery(queryOrQueryName);
			} else {
				q = entityManager.createQuery(queryOrQueryName);
			}
			
			// Setting query parameters
			for (int i = 0; i < parameters.length; i++) {
				q.setParameter(i + 1, parameters[i]);
			}

			// Executing query
			if (singleResult) {
				
				List<?> list = q.getResultList();
				if (CollectionUtils.isNotEmpty(list)) {
					result = (REZ) list.get(0);
				} else {
					result = null;
				}

			} else {

				result = (REZ) q.getResultList();

			}

			// Commit the transaction, which will cause the entity to
			// be stored in the database
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			// Catching exceptions and rollback of transaction
			entityManager.getTransaction().rollback();

			// throwing exception further
			throw new PersistenceException(e);

		} finally {

			// It is always good practice to close the EntityManager so that
			// resources are conserved.
			entityManager.close();

		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected <REZ> REZ executeNativeQuery(String query,
			boolean singleResult, Object... parameters)
			throws PersistenceException {

		// Creating entity manager to work with entity
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		entityManager.getTransaction().begin();

		REZ result;

		try {

			Query q;
			
			// Creating either named or simple query
			
			q = entityManager.createNativeQuery(query);
			

			// Executing query
			if (singleResult) {
				
				List<?> list = q.getResultList();
				if (CollectionUtils.isNotEmpty(list)) {
					result = (REZ) list.get(0);
				} else {
					result = null;
				}

			} else {

				result = (REZ) q.getResultList();

			}

			// Commit the transaction, which will cause the entity to
			// be stored in the database
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			// Catching exceptions and rollback of transaction
			entityManager.getTransaction().rollback();

			// throwing exception further
			throw new PersistenceException(e);

		} finally {

			// It is always good practice to close the EntityManager so that
			// resources are conserved.
			entityManager.close();

		}

		return result;
	}

}
