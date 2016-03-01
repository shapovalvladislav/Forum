package ua.net.forum.service;

import java.util.Collection;

import ua.net.forum.model.DomainSuperClass;


public interface IGenericService<T extends DomainSuperClass> {

    public Integer addEntity(T entity);
    public void delEntity(Integer id);
    public T getEntityById(int id);
    public Collection<T> getAllEntites();
    public void updateEntity(Integer id, T entity);

}
