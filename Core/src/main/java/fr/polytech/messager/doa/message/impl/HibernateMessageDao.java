package fr.polytech.messager.doa.message.impl;

import fr.polytech.messager.beans.Message;
import fr.polytech.messager.doa.HibernateDao;
import fr.polytech.messager.doa.message.MessageDao;

import java.util.List;

public class HibernateMessageDao extends HibernateDao<Long, Message> implements MessageDao {

    public HibernateMessageDao() {
        super(Message.class);
    }

    @Override
    public List<Message> getMessageOf(Long authorId) {
        //TODO
        throw new IllegalArgumentException("Not implemented");
    }
}
