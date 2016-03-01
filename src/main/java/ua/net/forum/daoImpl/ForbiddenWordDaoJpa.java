package ua.net.forum.daoImpl;

import javax.persistence.EntityManagerFactory;

import ua.net.forum.dao.IForbiddenWordDao;
import ua.net.forum.model.ForbiddenWord;

public class ForbiddenWordDaoJpa extends GenericDaoJpa<ForbiddenWord> implements IForbiddenWordDao {

    public ForbiddenWordDaoJpa(EntityManagerFactory emf) {
        super(ForbiddenWord.class, emf);
    }


}