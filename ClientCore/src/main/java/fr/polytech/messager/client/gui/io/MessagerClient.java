package fr.polytech.messager.client.gui.io;

import fr.polytech.messager.client.gui.model.Message;

import java.util.List;

public interface MessagerClient {
    void register(String login, String psw) throws Exception;

    String getAuthToken(String login, String psw) throws Exception;

    void sendMessage(String authToken, String text) throws Exception;

    List<Message> getAllMessages() throws Exception;

    List<Message> getMyMessage(String authToken) throws Exception;

    List<Message> getMessageFrom(String username) throws Exception;

    void delete(String authToken, Long messageId) throws Exception;

    void update(String authToken, Long id, String content) throws Exception;
}
