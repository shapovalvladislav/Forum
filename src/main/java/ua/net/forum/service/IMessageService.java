package ua.net.forum.service;

import java.util.Collection;

import ua.net.forum.model.Message;
import ua.net.forum.model.Topic;

public interface IMessageService extends IGenericService<Message> {
    public Collection<Message> findByTopic(Topic topic);
}
