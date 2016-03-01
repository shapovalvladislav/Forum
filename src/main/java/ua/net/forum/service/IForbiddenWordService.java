package ua.net.forum.service;

import ua.net.forum.model.ForbiddenWord;

public interface IForbiddenWordService extends IGenericService<ForbiddenWord> {
    public String hideForbiddenWords(String text);
}
