package service;

import java.util.Collection;

import javax.persistence.PersistenceException;

import exceptions.ServiceException;
import model.Profile;
import model.Section;

public interface IProfileService extends IGenericService<Profile> {
	public Collection<Profile> findTopProfiles() throws ServiceException;
	public Profile findProfileByLogin(String login);
}
