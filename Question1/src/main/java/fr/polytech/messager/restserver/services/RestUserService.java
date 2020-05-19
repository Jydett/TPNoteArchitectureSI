package fr.polytech.messager.restserver.services;

import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.services.UserService;

public class RestUserService extends UserService {
    public RestUserService(UserDao userDao) {
        super(userDao);
    }
}
