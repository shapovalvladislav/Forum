package dao;

import java.util.Collection;

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
	
	public Collection<Profile> findTopProfiles() throws PersistenceException;
	
}
