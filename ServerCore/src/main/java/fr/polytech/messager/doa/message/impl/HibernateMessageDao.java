package fr.polytech.messager.doa.message.impl;

import fr.polytech.messager.beans.Message;
import fr.polytech.messager.doa.HibernateDao;
import fr.polytech.messager.doa.message.MessageDao;
import org.hibernate.Session;

import java.util.List;

public class HibernateMessageDao extends HibernateDao<Long, Message> implements MessageDao {

    public HibernateMessageDao() {
        super(Message.class);
    }

    @Override
    public List<Message> getMessageOf(Long authorId) {
        return SESSION.createQuery("FROM Message m where m.author.id = :authorId", Message.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }
}
