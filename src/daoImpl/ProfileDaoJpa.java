package daoImpl;

import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import model.DBQueries;
import model.Profile;
import model.Section;
import dao.IProfileDao;
import dao.ISectionDao;

public class ProfileDaoJpa extends GenericDaoJpa<Profile> implements IProfileDao {

	public ProfileDaoJpa(EntityManagerFactory emf) {
		super(Profile.class, emf);
	}

	@Override
	public Collection<Profile> findTopProfiles() throws PersistenceException {
		return executeQuery(DBQueries.GET_TOP_PROFILES, true, false);
	}

}