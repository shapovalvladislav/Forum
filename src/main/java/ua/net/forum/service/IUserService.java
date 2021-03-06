package ua.net.forum.service;

import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.User;

public interface IUserService extends IGenericService<User> {
    public User findByLogin(String login) throws ServiceException;
    public User findByLoginAndPassword(String login, String password) throws ServiceException;
    public boolean isUserExists(String login, String password);

}
