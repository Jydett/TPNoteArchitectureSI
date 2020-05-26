package fr.polytech.messager.restserver.services;

import fr.polytech.messager.doa.init.HibernateDaoInitializer;
import fr.polytech.messager.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static fr.polytech.messager.restserver.services.ResponseUtils.failureResponse;

@Path("/UserService")
public class RestUserService extends UserService {
    public RestUserService() {
        super(HibernateDaoInitializer.getUserDao());
    }

    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/login")
    public Response getAuthTokenEndpoint(@QueryParam("username") String userName, @QueryParam("password") String password) {
        try {
            String token = super.getAuthToken(userName, password);
            return Response.ok().entity(token).build();
        } catch (Exception e) {
            return failureResponse(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("/register")
    public Response registerEndpoint(@QueryParam("username") String userName, @QueryParam("password") String password) {
        try {
            super.register(userName, password);
            return Response.ok().build();
        } catch (Exception e) {
            return failureResponse(e);
        }
    }
}
