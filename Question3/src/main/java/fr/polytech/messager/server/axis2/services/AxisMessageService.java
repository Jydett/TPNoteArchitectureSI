package fr.polytech.messager.server.axis2.services;

import fr.polytech.messager.beans.Message;
import fr.polytech.messager.doa.init.HibernateDaoInitializer;
import fr.polytech.messager.services.MessageService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class AxisMessageService extends MessageService {
    public AxisMessageService() {
        super(HibernateDaoInitializer.getMessageDao(), HibernateDaoInitializer.getUserDao());
    }

    @WebMethod
    public List<Message> getAllMessage() {
         return super.getMessages();
    }

    @WebMethod
    public List<Message> getMessageFrom(String username) {
        return super.getMessagesOf(username);
    }

    @WebMethod
    public List<Message> getMyMessage(String authToken) throws Exception {
        return super.getMyMessage(authToken);
    }

    @WebMethod
    public void deleteMessage(String authToken, Long messageId) throws Exception {
        super.deleteMessage(authToken, messageId);
    }

    @WebMethod
    public void createMessage(String authToken, String message) throws Exception {
        super.createMessage(authToken, message);
    }
}