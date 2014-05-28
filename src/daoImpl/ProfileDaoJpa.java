package daoImpl;

import java.util.Collection;
import java.util.List;

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
	public Collection<Object> findTopProfiles() throws PersistenceException {
		return executeNativeQuery(DBQueries.GET_TOP_PROC, false);
	}

}