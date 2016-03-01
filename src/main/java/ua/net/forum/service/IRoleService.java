package ua.net.forum.service;

import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.Role;

public interface IRoleService extends IGenericService<Role> {
    public Role findByName(String name) throws ServiceException;
}
