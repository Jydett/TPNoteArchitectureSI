package fr.polytech.messager.services;

import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.utils.JwtUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class UserService {

    private UserDao userDao;

    public String getAuthToken(String userName, String password) {
        return userDao.authenficate(userName, password)
                .map(user -> JwtUtils.getInstance()
                    .generateJwtToken(user.getUsername()))
                .orElse(null);
    }
}
