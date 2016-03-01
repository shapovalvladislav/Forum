package ua.net.forum.service;

import java.sql.Timestamp;

import ua.net.forum.model.Profile;
import ua.net.forum.model.Section;

public interface ISectionService extends IGenericService<Section> {
    public Profile getLastMsgProfile(Section section);
    public Timestamp getLastMsgDate(Section section);
    public int getMsgCount(Section section);
    public int getTopicCount(Section section);
}
