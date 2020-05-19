package fr.polytech.messager.doa.user;

import fr.polytech.messager.beans.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getById(Long authorId);
    Optional<User> authenficate(String username, String password);
    Optional<User> getByUserName(String username);
    void save(User user);
}
