package ua.net.forum.service;

import java.util.Collection;

import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.Profile;

public interface IProfileService extends IGenericService<Profile> {
    public Collection<Object> findTopProfiles() throws ServiceException;
    public Profile findProfileByLogin(String login);
}
