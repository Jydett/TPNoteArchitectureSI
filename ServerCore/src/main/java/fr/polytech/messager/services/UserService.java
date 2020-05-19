package fr.polytech.messager.services;

import fr.polytech.messager.beans.User;
import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.utils.JwtUtils;
import lombok.AllArgsConstructor;

import javax.naming.AuthenticationException;
import java.util.Optional;

@AllArgsConstructor
public abstract class UserService {

    private UserDao userDao;

    public String getAuthToken(String userName, String password) throws Exception {
        return userDao.authenficate(userName, password)
                .map(user -> JwtUtils.getInstance()
                    .generateJwtToken(user.getUsername()))
                .orElseThrow(AuthenticationException::new);
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
