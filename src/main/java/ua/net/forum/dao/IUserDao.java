package ua.net.forum.dao;

import javax.persistence.PersistenceException;

import ua.net.forum.model.User;

public interface IUserDao extends IGenericDao<User> {

    public User findByLogin(String login) throws PersistenceException;
    public User findByLoginAndPassword(String login, String password) throws PersistenceException;

}
