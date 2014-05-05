package service;

import java.sql.Timestamp;
import java.util.Collection;

import exceptions.ServiceException;
import model.Profile;
import model.Section;

/**
 * Интерфейс слоя служб для работы с классом {@link Worker}. Данный интерфейс, расширяет
 * общий интерфейс, добавляя в него дополнительные функции.
 * 
 * @author Prepodi
 */
public interface ISectionService extends IGenericService<Section> {
	public Profile getLastMsgProfile(Section section);
	public Timestamp getLastMsgDate(Section section);
	public int getMsgCount(Section section);
	public int getTopicCount(Section section);
	
}
