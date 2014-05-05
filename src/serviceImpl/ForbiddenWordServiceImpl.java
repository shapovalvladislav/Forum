package serviceImpl;

import model.ForbiddenWord;
import dao.DaoFactory;
import dao.IForbiddenWordDao;
import dao.IMessageDao;
import service.IForbiddenWordService;
import serviceImpl.GenericServiceImpl;

public class ForbiddenWordServiceImpl extends GenericServiceImpl<ForbiddenWord>
		implements IForbiddenWordService {
	protected IForbiddenWordDao forbiddenWordDao;

	@Override
	protected void initDaoInterface() {
		forbiddenWordDao = DaoFactory.OPENJPA.getForbiddenWordDao();
		dao = forbiddenWordDao;
	}

	@Override
	protected ForbiddenWord createNewEntityFromTheOneToAdd(
			ForbiddenWord newEntity) {
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
}
