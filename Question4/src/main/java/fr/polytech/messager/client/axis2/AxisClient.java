package fr.polytech.messager.client.axis2;

import fr.polytech.messager.client.axis2.generated.MessageServiceStub;
import fr.polytech.messager.client.axis2.generated.UserServiceStub;
import fr.polytech.messager.client.gui.io.MessagerClient;
import fr.polytech.messager.client.gui.model.Message;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AxisClient implements MessagerClient {

    private final MessageServiceStub messageServiceStub;
    private final UserServiceStub userServiceStub;

    public AxisClient() throws Exception {
        messageServiceStub = new MessageServiceStub();
        userServiceStub = new UserServiceStub();
    }

    @Override
    public void delete(String authToken, Long messageId) throws Exception {
        MessageServiceStub.DeleteMessage deleteMessage = new MessageServiceStub.DeleteMessage();
        deleteMessage.setAuthToken(authToken);
        deleteMessage.setMessageId(messageId);
        messageServiceStub.deleteMessage(deleteMessage);
    }

    @Override
    public void update(String authToken, Long id, String content) throws Exception {
        MessageServiceStub.UpdateMessage updateMessageRequest = new MessageServiceStub.UpdateMessage();
        updateMessageRequest.setAuthToken(authToken);
        updateMessageRequest.setMessageId(id);
        updateMessageRequest.setContent(content);
        messageServiceStub.updateMessage(updateMessageRequest);
    }

    @Override
    public void sendMessage(String authToken, String text) throws Exception {
        MessageServiceStub.CreateMessage createMessageRequest = new MessageServiceStub.CreateMessage();
        createMessageRequest.setAuthToken(authToken);
        createMessageRequest.setMessage(text);
        messageServiceStub.createMessage(createMessageRequest);
    }

    @Override
    public void register(String login, String psw) throws Exception {
        UserServiceStub.Register registerRequest = new UserServiceStub.Register();
        registerRequest.setUserName(login);
        registerRequest.setPassword(psw);
        userServiceStub.register(registerRequest);
    }

    @Override
    public String getAuthToken(String userName, String password) throws Exception {
        UserServiceStub.GetAuthToken getAuthTokenRequest = new UserServiceStub.GetAuthToken();
        getAuthTokenRequest.setUserName(userName);
        getAuthTokenRequest.setPassword(password);
        UserServiceStub.GetAuthTokenResponse response = userServiceStub.getAuthToken(getAuthTokenRequest);
        return response.get_return();
    }

    @Override
    public List<Message> getMyMessage(String authToken) throws Exception {
        MessageServiceStub.GetMyMessage getMyMessageRequest = new MessageServiceStub.GetMyMessage();
        getMyMessageRequest.setAuthToken(authToken);
        MessageServiceStub.GetMyMessageResponse response = messageServiceStub.getMyMessage(getMyMessageRequest);
        return wrap(response.get_return());
    }

    @Override
    public List<Message> getMessageFrom(String username) throws Exception {
        MessageServiceStub.GetMessageFrom getMessageFromRequest = new MessageServiceStub.GetMessageFrom();
        getMessageFromRequest.setUsername(username);
        MessageServiceStub.GetMessageFromResponse response = messageServiceStub.getMessageFrom(getMessageFromRequest);
        return wrap(response.get_return());
    }

    @Override
    public List<Message> getAllMessages() throws Exception {
        MessageServiceStub.GetAllMessage getAllMessageRequest = new MessageServiceStub.GetAllMessage();
        MessageServiceStub.GetAllMessageResponse response = messageServiceStub.getAllMessage(getAllMessageRequest);
        return wrap(response.get_return());
    }

    private static List<Message> wrap(MessageServiceStub.Message[] messages) {
        if (messages == null) return null;
        return Arrays.stream(messages)
                .map(m -> new Message(m.getId(), m.getAuthor().getUsername(), m.getContent()))
                .collect(Collectors.toList());
    }
}
