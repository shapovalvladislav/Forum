package dao;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.PersistenceException;

import model.Section;
import model.User;

/**
 * Интерфейс слоя dao для работы с классом {@link Section}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface ISectionDao extends IGenericDao<Section> {

}
