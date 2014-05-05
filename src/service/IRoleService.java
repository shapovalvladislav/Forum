package service;

import javax.persistence.PersistenceException;

import exceptions.ServiceException;
import model.Role;
import model.Section;

public interface IRoleService extends IGenericService<Role> {
	public Role findByName(String name) throws ServiceException;
}
