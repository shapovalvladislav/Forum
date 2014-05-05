package serviceImpl;

import dao.DaoFactory;
import dao.IMessageDao;
import dao.IRoleDao;
import model.Role;
import exceptions.ServiceException;
import service.IRoleService;
import service.ServiceFactory;
import serviceImpl.GenericServiceImpl;

public class RoleServiceImpl extends GenericServiceImpl<Role> implements
		IRoleService {

	protected IRoleDao roleDao;
	
	@Override
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
