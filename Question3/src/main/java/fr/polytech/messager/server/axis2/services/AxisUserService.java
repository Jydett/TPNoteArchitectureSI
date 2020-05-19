package fr.polytech.messager.server.axis2.services;

import fr.polytech.messager.doa.init.HibernateDaoInitializer;
import fr.polytech.messager.services.UserService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class AxisUserService extends UserService {

    public AxisUserService() {
        super(HibernateDaoInitializer.getUserDao());
    }

    @Override
    @WebMethod
    public String getAuthToken(String userName, String password) {
        return super.getAuthToken(userName, password);
    }
}
