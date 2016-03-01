package ua.net.forum.dao;

import java.util.Collection;

import javax.persistence.PersistenceException;

import ua.net.forum.model.Profile;

public interface IProfileDao extends IGenericDao<Profile> {
    public Collection<Object> findTopProfiles() throws PersistenceException;

}
