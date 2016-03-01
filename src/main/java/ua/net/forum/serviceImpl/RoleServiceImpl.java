package ua.net.forum.serviceImpl;

import ua.net.forum.dao.DaoFactory;
import ua.net.forum.dao.IRoleDao;
import ua.net.forum.exceptions.ServiceException;
import ua.net.forum.model.Role;
import ua.net.forum.service.IRoleService;
import ua.net.forum.service.ServiceFactory;

public class RoleServiceImpl extends GenericServiceImpl<Role> implements IRoleService {

    protected IRoleDao roleDao;

    public Role findByName(String name) throws ServiceException {
        IRoleService service = ServiceFactory.DEFAULT.getRoleService();
        return service.findByName(name);
    }

    @Override
    protected void initDaoInterface() {
        roleDao = DaoFactory.OPENJPA.getRoleDao();
        dao = roleDao;
    }

    @Override
    protected Role createNewEntityFromTheOneToAdd(Role newEntity) {
        Role role = new Role();
        role.setName(newEntity.getName());
        role.setUsers(newEntity.getUsers());
        return role;
    }

    @Override
    protected void updateEntityWithOneNewValues(Role savedEntity, Role newEntity) {
        if (newEntity.getName() != null)
            savedEntity.setName(newEntity.getName());
        if (newEntity.getUsers() != null)
            savedEntity.setUsers(newEntity.getUsers());
    }

}
