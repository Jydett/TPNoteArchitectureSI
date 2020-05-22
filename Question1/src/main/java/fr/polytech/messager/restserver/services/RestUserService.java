package fr.polytech.messager.restserver.services;

import fr.polytech.messager.doa.init.HibernateDaoInitializer;
import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.services.UserService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class RestUserService extends UserService {
    public RestUserService() {
        super(HibernateDaoInitializer.getUserDao());
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("/login")
    @Override
    public String getAuthToken(String userName, String password) throws Exception {
        return super.getAuthToken(userName, password);
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("/register")
    @Override
    public void register(String userName, String password) {
        super.register(userName, password);
    }
}
