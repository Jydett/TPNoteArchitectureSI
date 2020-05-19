package fr.polytech.messager.services;

import fr.polytech.messager.beans.User;
import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.utils.JwtUtils;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public abstract class UserService {

    private UserDao userDao;

    public String getAuthToken(String userName, String password) {
        return userDao.authenficate(userName, password)
                .map(user -> JwtUtils.getInstance()
                    .generateJwtToken(user.getUsername()))
                .orElse(null);
    }

    public void register(String userName, String password) {
        Optional<User> byUserName = userDao.getByUserName(userName);
        if (byUserName.isPresent()) {
            throw new IllegalArgumentException("Username already used");
        } else {
            userDao.save(new User(userName, password));
        }
    }
}
