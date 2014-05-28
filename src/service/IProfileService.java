package service;

import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import exceptions.ServiceException;
import model.Profile;
import model.Section;

public interface IProfileService extends IGenericService<Profile> {
	public Collection<Object> findTopProfiles() throws ServiceException;
	public Profile findProfileByLogin(String login);
}
