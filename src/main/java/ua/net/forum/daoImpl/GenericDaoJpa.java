package ua.net.forum.daoImpl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import ua.net.forum.dao.IGenericDao;
import ua.net.forum.model.DomainSuperClass;

public abstract class GenericDaoJpa<T extends DomainSuperClass> implements IGenericDao<T> {

    private static final String QUERY_SELECT_ALL = "SELECT x FROM %s x";
    private static final String QUERY_COUNT_ALL = "SELECT COUNT(x) FROM %s x";

    protected EntityManagerFactory entityManagerFactory;

    protected Class<T> persistentClass;

    public GenericDaoJpa(Class<T> persistentClass, EntityManagerFactory emf) {
        super();
        this.persistentClass = persistentClass;
        this.entityManagerFactory = emf;
    }

    public void delete(T entity) throws PersistenceException {
        if (entity == null) {
            throw new PersistenceException(
                    "Entity for deleting cannot be null!");
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            T savedEntity = entityManager.find(persistentClass, entity.getId());
            entityManager.remove(savedEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            entityManager.close();
        }
    }

    public void delete(Integer entityId) throws PersistenceException {
        delete(findById(entityId));
    }

    public Collection<T> findAll() throws PersistenceException {
        return executeQuery(String.format(QUERY_SELECT_ALL, persistentClass.getSimpleName()), false, false);
    }

    public Long getAllCount() throws PersistenceException {
        return executeQuery(String.format(QUERY_COUNT_ALL, persistentClass.getSimpleName()), false, true);
    }

    public T findById(int id) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        T savedEntity = null;

        try {
            savedEntity = entityManager.find(persistentClass, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return savedEntity;
    }

    public T save(T entity) throws PersistenceException {
        if (entity == null) {
            throw new PersistenceException("Entity for saving cannot be null!");
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        T savedEntity = null;
        try {
            System.out.println("entity id: " + entity.getId());
            if (entity.getId() == 0) {
                entityManager.persist(entity);
                savedEntity = entity;
            } else {
                savedEntity = entityManager.merge(entity);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            entityManager.close();
        }

        return savedEntity;
    }

    @SuppressWarnings("unchecked")
    protected <REZ> REZ executeQuery(String queryOrQueryName,
            boolean namedQuery, boolean singleResult, Object... parameters)
                    throws PersistenceException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        REZ result;
        try {
            Query q;
            if (namedQuery) {
                q = entityManager.createNamedQuery(queryOrQueryName);
            } else {
                q = entityManager.createQuery(queryOrQueryName);
            }

            for (int i = 0; i < parameters.length; i++) {
                q.setParameter(i + 1, parameters[i]);
            }

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

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            entityManager.close();
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    protected <REZ> REZ executeNativeQuery(String query,
            boolean singleResult, Object... parameters) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        REZ result;
        try {
            Query q;
            q = entityManager.createNativeQuery(query);
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

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            entityManager.close();
        }

        return result;
    }

}
