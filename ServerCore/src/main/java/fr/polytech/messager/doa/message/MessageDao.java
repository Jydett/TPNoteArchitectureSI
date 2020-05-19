package fr.polytech.messager.doa.message;

import fr.polytech.messager.beans.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDao {
    Optional<Message> getById(Long messageId);
    List<Message> getAll();
    void remove(Message message);
    List<Message> getMessageOf(Long authorId);
    void save(Message message);
}
