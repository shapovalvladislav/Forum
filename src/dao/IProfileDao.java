package dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import model.Profile;
import model.Section;

/**
 * Интерфейс слоя dao для работы с классом {@link Section}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface IProfileDao extends IGenericDao<Profile> {
	
	public Collection<Object> findTopProfiles() throws PersistenceException;
	
}
