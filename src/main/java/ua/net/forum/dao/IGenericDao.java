package ua.net.forum.dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import ua.net.forum.model.DomainSuperClass;

public interface IGenericDao<T extends DomainSuperClass> {

    T findById(int id) throws PersistenceException;
    Collection<T> findAll() throws PersistenceException;
    T save(T entity) throws PersistenceException;
    void delete(T entity) throws PersistenceException;
    public Long getAllCount() throws PersistenceException;
    void delete(Integer entityId) throws PersistenceException;

}
