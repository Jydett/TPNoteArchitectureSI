package fr.polytech.messager.doa.user.impl;

import fr.polytech.messager.beans.User;
import fr.polytech.messager.doa.HibernateDao;
import fr.polytech.messager.doa.user.UserDao;

public class HibernateUserDao extends HibernateDao<Long, User> implements UserDao {

    public HibernateUserDao() {
        super(User.class);
    }
}
