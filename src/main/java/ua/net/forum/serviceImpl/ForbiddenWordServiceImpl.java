package ua.net.forum.serviceImpl;

import java.util.Collection;

import ua.net.forum.dao.DaoFactory;
import ua.net.forum.dao.IForbiddenWordDao;
import ua.net.forum.model.ForbiddenWord;
import ua.net.forum.service.IForbiddenWordService;

public class ForbiddenWordServiceImpl extends GenericServiceImpl<ForbiddenWord>
implements IForbiddenWordService {
    protected IForbiddenWordDao forbiddenWordDao;

    @Override
    protected void initDaoInterface() {
        forbiddenWordDao = DaoFactory.OPENJPA.getForbiddenWordDao();
        dao = forbiddenWordDao;
    }

    @Override
    protected ForbiddenWord createNewEntityFromTheOneToAdd(ForbiddenWord newEntity) {
        ForbiddenWord word = new ForbiddenWord();
        word.setWord(newEntity.getWord());
        return word;
    }

    @Override
    protected void updateEntityWithOneNewValues(ForbiddenWord savedEntity,
            ForbiddenWord newEntity) {
        if (newEntity.getWord() != null)
            savedEntity.setWord(newEntity.getWord());
    }

    public String hideForbiddenWords(String text) {
        Collection<ForbiddenWord> forbiddenWords = getAllEntites();
        boolean found = false;
        for (ForbiddenWord word : forbiddenWords) {
            String forbiddenWord = word.getWord();
            if (text.lastIndexOf(forbiddenWord) != -1) {
                text = text.replaceAll(forbiddenWord, "<span style='color: red'>&ltcensored&gt</span>");
                found = true;
            }
        }
        if (!found)
            return null;
        return text;
    }
}
