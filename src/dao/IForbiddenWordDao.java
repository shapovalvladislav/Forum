package dao;

import javax.persistence.PersistenceException;

import model.ForbiddenWord;
import model.Section;

/**
 * Интерфейс слоя dao для работы с классом {@link Section}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface IForbiddenWordDao extends IGenericDao<ForbiddenWord> {
		
}
