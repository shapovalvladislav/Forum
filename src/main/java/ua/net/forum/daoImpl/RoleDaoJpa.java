package ua.net.forum.daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import ua.net.forum.dao.IRoleDao;
import ua.net.forum.model.DBQueries;
import ua.net.forum.model.Role;

public class RoleDaoJpa extends GenericDaoJpa<Role> implements IRoleDao {

    public RoleDaoJpa(EntityManagerFactory emf) {
        super(Role.class, emf);
    }

    public Role findByName(String name) throws PersistenceException {
        return executeQuery(DBQueries.GET_ROLE_BY_NAME, true, true, name);
    }

}
