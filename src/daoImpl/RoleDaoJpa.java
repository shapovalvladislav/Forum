package daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import model.DBQueries;
import model.Role;
import model.Section;
import dao.IRoleDao;
import dao.ISectionDao;

public class RoleDaoJpa extends GenericDaoJpa<Role> implements IRoleDao {

	public RoleDaoJpa(EntityManagerFactory emf) {
		super(Role.class, emf);
	}

	@Override
	public Role findByName(String name) throws PersistenceException {
		return executeQuery(DBQueries.GET_ROLE_BY_NAME, true, true, name);
	}

}
