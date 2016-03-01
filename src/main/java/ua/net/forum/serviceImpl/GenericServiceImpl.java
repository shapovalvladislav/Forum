package ua.net.forum.serviceImpl;

import java.util.Collection;

import ua.net.forum.dao.IGenericDao;
import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.service.IGenericService;
import ua.net.forum.model.DomainSuperClass;

public abstract class GenericServiceImpl<T extends DomainSuperClass> implements
IGenericService<T> {

    protected IGenericDao<T> dao;

    public GenericServiceImpl() {
        super();
        initDaoInterface();
    }

    protected abstract void initDaoInterface();

    protected void beforeEntityAddUpdate(T entity) {
    }

    public Integer addEntity(T entity) {
        beforeEntityAddUpdate(entity);
        try {
            T newEntity = entity;
            newEntity = dao.save(newEntity);
            if (newEntity != null)
                return newEntity.getId();
            else
                throw new ServiceException("Failed to add "
                        + entity.getClass().getSimpleName() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract T createNewEntityFromTheOneToAdd(T newEntity);
    public void delEntity(Integer id) throws SecurityException {
        T savedEntity = getEntityById(id);
        try {
            dao.delete(savedEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<T> getAllEntites() throws SecurityException {
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getEntityById(int id) {
        try {
            T savedEntity = dao.findById(id);
            if ((savedEntity == null) || (savedEntity.getId() == 0))
                throw new Exception("Incorrect id " + id);
            return savedEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateEntity(Integer id, T entity) throws SecurityException {
        beforeEntityAddUpdate(entity);
        try {
            T savedEntity = getEntityById(id);
            updateEntityWithOneNewValues(savedEntity, entity);
            savedEntity = dao.save(savedEntity);
            if (savedEntity == null)
                throw new ServiceException("Failed to update "
                        + entity.getClass().getSimpleName() + " with id " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void updateEntityWithOneNewValues(T savedEntity, T newEntity);

}
