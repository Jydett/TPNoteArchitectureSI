package fr.polytech.messager.restserver.services;

import fr.polytech.messager.beans.Message;
import fr.polytech.messager.doa.init.HibernateDaoInitializer;
import fr.polytech.messager.doa.message.MessageDao;
import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.services.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/MessageService/messages")
public class RestMessageService extends MessageService {
    public RestMessageService() {
        super(HibernateDaoInitializer.getMessageDao(), HibernateDaoInitializer.getUserDao());
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Override
    public void createMessage(String authToken, String message) throws Exception {
        super.createMessage(authToken, message);
    }

    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Path("/messages")
    public List<Message> getMessages(String username) {
        if (username != null){
            return super.getMessagesOf(username);
        }
        return super.getMessages();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{id}")
    @Override
    public void deleteMessage(String authToken, @PathParam("id") Long messageId) throws Exception {
        super.deleteMessage(authToken, messageId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{id}")
    @Override
    public void updateMessage(String authToken, @PathParam("id") Long messageId, String content) throws Exception {
        super.updateMessage(authToken, messageId, content);
    }
}
