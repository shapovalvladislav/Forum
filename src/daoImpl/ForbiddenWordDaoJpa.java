package daoImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import model.ForbiddenWord;
import model.Section;
import dao.IForbiddenWordDao;
import dao.ISectionDao;

public class ForbiddenWordDaoJpa extends GenericDaoJpa<ForbiddenWord> implements IForbiddenWordDao {

	public ForbiddenWordDaoJpa(EntityManagerFactory emf) {
		super(ForbiddenWord.class, emf);
	}


}