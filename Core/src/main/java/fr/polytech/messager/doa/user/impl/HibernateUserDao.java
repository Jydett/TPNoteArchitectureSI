package fr.polytech.messager.doa.user.impl;

import fr.polytech.messager.beans.User;
import fr.polytech.messager.doa.HibernateDao;
import fr.polytech.messager.doa.user.UserDao;

import java.util.Optional;

public class HibernateUserDao extends HibernateDao<Long, User> implements UserDao {

    public HibernateUserDao() {
        super(User.class);
    }

    @Override
    public Optional<User> authenficate(String username, String password) {
        return super.getOne(SESSION.createQuery("select u from User u where u.username = :username and u.password = :password", User.class)
            .setParameter("username", username)
            .setParameter("password", password)
        );
    }

    @Override
    public Optional<User> getByUserName(String username) {
        return super.getOne(SESSION.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username));
    }
}
