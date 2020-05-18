package fr.polytech.messager.doa.init;

import fr.polytech.messager.doa.message.MessageDao;
import fr.polytech.messager.doa.message.impl.HibernateMessageDao;
import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.doa.user.impl.HibernateUserDao;
import lombok.Getter;

public class HibernateDaoInitializer {
    private static final HibernateMessageDao MESSAGE_DAO;
    private static final HibernateUserDao USER_DAO;

    static {
        MESSAGE_DAO = new HibernateMessageDao();
        USER_DAO = new HibernateUserDao();
    }

    public static MessageDao getMessageDao() {
        return MESSAGE_DAO;
    }

    public static UserDao getUserDao() {
        return USER_DAO;
    }
}
