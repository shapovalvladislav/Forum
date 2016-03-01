package ua.net.forum.dao;

import javax.persistence.PersistenceException;

import ua.net.forum.model.Role;

public interface IRoleDao extends IGenericDao<Role> {

    public Role findByName(String name) throws PersistenceException;

}
