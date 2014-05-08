package service;

import model.ForbiddenWord;
import model.Section;

public interface IForbiddenWordService extends IGenericService<ForbiddenWord> {
	public String hideForbiddenWords(String text);
}
