package fr.polytech.messager.services;

import fr.polytech.messager.beans.Message;
import fr.polytech.messager.beans.User;
import fr.polytech.messager.doa.message.MessageDao;
import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.auth.InvalidCredentialsException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class MessageService {

    protected MessageDao messageDao;
    protected UserDao userDao;

    protected void createMessage(String authToken, String message) throws Exception {
        User u = auth(authToken);
        messageDao.save(new Message(LocalDateTime.now(), message, u));
    }

    protected List<Message> getMessages() {
        return messageDao.getAll();
    }

    protected List<Message> getMyMessage(String token) throws Exception {
        return getMessagesOf(JwtUtils.getInstance().getUserNameFromJwtToken(token));
    }

    protected List<Message> getMessagesOf(String username) {
        return userDao.getByUserName(username)
                .map(user -> messageDao.getMessageOf(user.getId()))
                .orElse(Collections.emptyList());
    }

    protected void deleteMessage(String authToken, Long messageId) throws Exception {
        User u = auth(authToken);
        Optional<Message> optionalMessage = messageDao.getById(messageId);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            if (message.getAuthor().getId().equals(u.getId())) {
                messageDao.remove(message);
                return;
            }
        }
        HttpException forbidden = new HttpException("Forbidden");
        forbidden.setReasonCode(403);
        throw forbidden;
    }

    private User auth(String authToken) throws Exception {
        JwtUtils jwtUtils = JwtUtils.getInstance();
        if (jwtUtils.validateJwtToken(authToken)) {
            return userDao.getByUserName(jwtUtils.getUserNameFromJwtToken(authToken))
                    .orElseThrow(InvalidCredentialsException::new);
        }
        throw new InvalidCredentialsException();
    }
}
