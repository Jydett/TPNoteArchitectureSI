package fr.polytech.messager.client.rest;

import fr.polytech.messager.client.gui.io.MessagerClient;
import fr.polytech.messager.client.gui.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class RestClient implements MessagerClient {

    private WebTarget targetMessage;
    private WebTarget targetUser;

    public RestClient() {
        Client c = ClientBuilder.newClient();
         targetMessage = c.target("http://localhost:8080/RESTServer_war_exploded/api/MessageService/");
         targetUser = c.target("http://localhost:8080/RESTServer_war_exploded/api/UserService/");
    }

    @Override
    public void register(String login, String psw) throws Exception {
            targetUser.path("register")
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.entity(login, MediaType.APPLICATION_XML));
    }

    @Override
    public String getAuthToken(String login, String psw) throws Exception {
        return targetUser.path("login")
                .request(MediaType.APPLICATION_XML_TYPE)
                .get()
                .readEntity(new GenericType<String>() {});
    }

    @Override
    public void sendMessage(String authToken, String text) throws Exception {
        targetMessage.path("createMessage")
                .request(MediaType.APPLICATION_XML_TYPE)
                .post(Entity.entity(text, MediaType.APPLICATION_XML));
    }

    @Override
    public List<Message> getAllMessages() throws Exception {
        return targetMessage.path("messages")
                .request(MediaType.APPLICATION_XML_TYPE)
                .get()
                .readEntity(new GenericType<List<Message>>() {});
    }

    @Override
    public List<Message> getMyMessage(String authToken) throws Exception {
        return targetMessage.path("myMessage")
                .request(MediaType.APPLICATION_ATOM_XML_TYPE)
                .get()
                .readEntity(new GenericType<List<Message>>() {});
    }

    @Override
    public List<Message> getMessageFrom(String username) throws Exception {
        return targetMessage.path("messageOf")
                .request(MediaType.APPLICATION_XML_TYPE)
                .get()
                .readEntity(new GenericType<List<Message>>() {});
    }

    @Override
    public void delete(String authToken, Long messageId) throws Exception {
        targetMessage.path("deleteMessage")
                .request(MediaType.APPLICATION_XML_TYPE)
                .delete();
    }

    @Override
    public void update(String authToken, Long id, String content) throws Exception {
        targetMessage.path("updateMessage")
                .request(MediaType.APPLICATION_XML_TYPE)
                .post(Entity.entity(content, MediaType.APPLICATION_XML));
    }
}
