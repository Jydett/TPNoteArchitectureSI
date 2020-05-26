package fr.polytech.messager.restserver.services;

import fr.polytech.messager.beans.Message;
import fr.polytech.messager.doa.init.HibernateDaoInitializer;
import fr.polytech.messager.restserver.dto.MessageDto;
import fr.polytech.messager.services.MessageService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.httpclient.auth.InvalidCredentialsException;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static fr.polytech.messager.restserver.services.ResponseUtils.failureResponse;

@Path("/MessageService/messages")
public class RestMessageService extends MessageService {
    public RestMessageService() {
        super(HibernateDaoInitializer.getMessageDao(), HibernateDaoInitializer.getUserDao());
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createMessageEndpoint(@QueryParam("auth") String authToken, String message) {
        try {
            super.createMessage(authToken, message);
        } catch (Exception e) {
            return failureResponse(e);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/mine")
    public Response getMyMessagesEndpoint(@QueryParam("auth") String authToken) {
        List<Message> resp;
        try {
            resp = super.getMyMessage(authToken);
        } catch (Exception e) {
            return failureResponse(e);
        }
        return wrapListOfMessage(resp);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getMessagesEndpoint(@QueryParam("username") String username) {
        List<Message> resp;
        try {
            if (username != null) {
                resp = super.getMessagesOf(username);
            } else {
                resp = super.getMessages();
            }
        } catch (Exception e) {
            return failureResponse(e);
        }
        return wrapListOfMessage(resp);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteMessageEndpoint(@QueryParam("auth") String authToken, @PathParam("id") Long messageId) {
        try {
            super.deleteMessage(authToken, messageId);
        } catch (Exception e) {
            return failureResponse(e);
        }
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{id}")
    public Response updateMessageEndpoint(@QueryParam("auth") String authToken, @PathParam("id") Long messageId, String content) {
        try {
            super.updateMessage(authToken, messageId, content);
        } catch (Exception e) {
            return failureResponse(e);
        }
        return Response.ok().build();
    }

    private Response wrapListOfMessage(List<Message> messages) {
        final GenericEntity<List<MessageDto>> entity =
                new GenericEntity<List<MessageDto>>(MessageDto.wrap(messages)) {};
        return Response.ok(entity).build();
    }
}
