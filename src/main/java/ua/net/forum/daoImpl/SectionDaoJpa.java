package ua.net.forum.daoImpl;

import javax.persistence.EntityManagerFactory;

import ua.net.forum.dao.ISectionDao;
import ua.net.forum.model.Section;

public class SectionDaoJpa extends GenericDaoJpa<Section> implements ISectionDao {

    public SectionDaoJpa(EntityManagerFactory emf) {
        super(Section.class, emf);
    }

}
