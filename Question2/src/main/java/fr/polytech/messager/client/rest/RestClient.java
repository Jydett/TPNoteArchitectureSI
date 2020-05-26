package fr.polytech.messager.client.rest;

import fr.polytech.messager.client.gui.io.MessagerClient;
import fr.polytech.messager.client.gui.model.Message;
import fr.polytech.messager.client.rest.dto.MessageDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RestClient implements MessagerClient {

    private WebTarget targetMessage;
    private WebTarget targetUser;

    public RestClient() {
        String apiPath = "http://localhost:8080/Question1_war_exploded";
        Client c = ClientBuilder.newClient();
        targetMessage = c.target(apiPath + "/api/MessageService/messages");
        targetUser = c.target(apiPath + "/api/UserService/");
    }

    @Override
    public String getAuthToken(String login, String psw) throws Exception {
        Response response = targetUser.path("login")
                .queryParam("username", login)
                .queryParam("password", psw)
                .request(MediaType.APPLICATION_XML_TYPE)
                .get();
        handleError(response);
        return response.readEntity(String.class);
    }

    @Override
    public void register(String login, String psw) throws Exception {
        Response response = targetUser.path("register")
            .queryParam("username", login)
            .queryParam("password", psw)
            .request(MediaType.APPLICATION_XML_TYPE)
            .get();
        handleError(response);
    }

    @Override
    public void sendMessage(String authToken, String text) throws Exception {
        Response response = targetMessage
            .queryParam("auth", authToken)
            .request(MediaType.APPLICATION_XML_TYPE)
            .post(Entity.entity(text, MediaType.APPLICATION_XML));
        handleError(response);
    }

    @Override
    public List<Message> getAllMessages() throws Exception {
        Response response = targetMessage
                .request(MediaType.APPLICATION_XML_TYPE)
                .get();
        handleError(response);
        return MessageDto.unwrap(response.readEntity(new GenericType<List<MessageDto>>() {}));
    }

    @Override
    public List<Message> getMyMessage(String authToken) throws Exception {
        Response response = targetMessage.path("mine")
            .queryParam("auth", authToken)
            .request(MediaType.APPLICATION_XML_TYPE)
            .get();
        handleError(response);
        return MessageDto.unwrap(response.readEntity(new GenericType<List<MessageDto>>() {}));
    }

    @Override
    public List<Message> getMessageFrom(String username) throws Exception {
        Response response = targetMessage
            .queryParam("username", username)
            .request(MediaType.APPLICATION_XML_TYPE)
            .get();
        handleError(response);
        return MessageDto.unwrap(response.readEntity(new GenericType<List<MessageDto>>() {}));
    }

    @Override
    public void delete(String authToken, Long messageId) throws Exception {
        Response response = targetMessage.path("/" + messageId)
            .queryParam("auth", authToken)
            .request(MediaType.APPLICATION_XML_TYPE)
            .delete();
        handleError(response);
    }

    @Override
    public void update(String authToken, Long messageId, String content) throws Exception {
        Response response = targetMessage.path("/" + messageId)
            .queryParam("auth", authToken)
            .request(MediaType.APPLICATION_XML_TYPE)
            .post(Entity.entity(content, MediaType.APPLICATION_XML));
        handleError(response);
    }

    private void handleError(Response response) throws Exception {
        if (response.getStatusInfo().getFamily().compareTo(Response.Status.Family.SUCCESSFUL) != 0) {
            throw new Exception("Server responded " + response.getStatus() + ", message: " + response.getEntity());
        }
    }
}
