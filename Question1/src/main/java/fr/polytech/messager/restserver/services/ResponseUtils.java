package fr.polytech.messager.restserver.services;

import org.apache.commons.httpclient.auth.InvalidCredentialsException;

import javax.ws.rs.core.Response;

public class ResponseUtils {

    public static Response failureResponse(Exception e) {
        if (e instanceof InvalidCredentialsException) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(e.getMessage())
            .build();
    }
}
