package daoImpl;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import dao.ISectionDao;
import model.Section;
import model.User;

/**
 * Implementation of worker dao
 * @author bsa
 */
public class SectionDaoJpa extends GenericDaoJpa<Section> implements ISectionDao {
	
	public SectionDaoJpa(EntityManagerFactory emf) {
		super(Section.class, emf);
	}

}
