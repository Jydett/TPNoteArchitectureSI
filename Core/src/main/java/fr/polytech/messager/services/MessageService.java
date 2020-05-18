package fr.polytech.messager.services;

import fr.polytech.messager.beans.Message;
import fr.polytech.messager.doa.message.MessageDao;
import fr.polytech.messager.doa.user.UserDao;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public abstract class MessageService {

    protected MessageDao messageDao;
    protected UserDao userDao;

    protected void createMessage(String message, Long authorId) {
        userDao.getById(authorId)
                .ifPresent(user -> messageDao.save(new Message(LocalDateTime.now(), message, user)));
    }

    protected List<Message> getMessages() {
        return messageDao.getAll();
    }

    protected List<Message> getMessagesOf(Long authorId) {
        return messageDao.getMessageOf(authorId);
    }

    protected void deleteMessage(Long messageId) {
        messageDao.getById(messageId).ifPresent(message -> messageDao.remove(message));
    }
}
