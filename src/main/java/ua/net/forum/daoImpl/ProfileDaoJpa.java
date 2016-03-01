package ua.net.forum.daoImpl;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import ua.net.forum.dao.IProfileDao;
import ua.net.forum.model.DBQueries;
import ua.net.forum.model.Profile;

public class ProfileDaoJpa extends GenericDaoJpa<Profile> implements IProfileDao {

    public ProfileDaoJpa(EntityManagerFactory emf) {
        super(Profile.class, emf);
    }

    public Collection<Object> findTopProfiles() throws PersistenceException {
        return executeNativeQuery(DBQueries.GET_TOP_PROC, false);
    }

}