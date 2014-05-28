package daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import com.sun.xml.internal.fastinfoset.sax.Properties;

import dao.DaoFactory;
import dao.IForbiddenWordDao;
import dao.IMessageDao;
import dao.IProfileDao;
import dao.IRoleDao;
import dao.ISectionDao;
import dao.ITopicDao;
import dao.IUserDao;

/**
 * Implementation of the DAOFactory
 * 
 * @author bsa
 */
public class DaoFactoryJpa extends DaoFactory {

	private static final String PERSISTENT_UNIT = "Forum";
	
	/** Entity manager factory that will create EntityManager objects for dao */
	private static EntityManagerFactory emf;
	

	/**
	 * Constructor that will initialize EntityManagerFactory
	 * 
	 * @throws RuntimeException
	 */
	public DaoFactoryJpa(){
		super();
		try {
			initEntityManagerFactory();
		} catch (PersistenceException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Initializing entity manager factory, with specifying properties from
	 * external property file
	 * 
	 * @throws PersistenceException
	 *             If error occurs
	 */
	private void initEntityManagerFactory() throws PersistenceException {
		try {
			// Create the factory defined by the PERSISTENT_UNIT
			// entity-manager entry
			emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
			
		} catch (Exception e) {
			throw new PersistenceException(
					"Failed to create EntityManagerFactory", e);
		}
		
		if (emf == null) {
			throw new PersistenceException(
					"EntityManagerFactory was not created. See log for details.");
		}
	}


	/* (non-Javadoc)
	 * @see ua.cn.stu.oop.example.dao.DAOFactory#getWorkerDao()
	 */
	@Override
	public ISectionDao getSectionDao() {
		return new SectionDaoJpa(emf);
	}

	@Override
	public ITopicDao getTopicDao() {
		return new TopicDaoJpa(emf);
	}

	@Override
	public IProfileDao getProfileDao() {
		return new ProfileDaoJpa(emf);
	}

	@Override
	public IUserDao getUserDao() {
		return new UserDaoJpa(emf);
	}

	@Override
	public IRoleDao getRoleDao() {
		return new RoleDaoJpa(emf);
	}

	@Override
	public IMessageDao getMessageDao() {
		return new MessageDaoJpa(emf);
	}

	@Override
	public IForbiddenWordDao getForbiddenWordDao() {
		return new ForbiddenWordDaoJpa(emf);
	}


}
