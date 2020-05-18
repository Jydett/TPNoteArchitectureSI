package fr.polytech.messager.server.axis2.services;

import fr.polytech.messager.services.UserService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class AxisUserService extends UserService {

    @WebMethod
    public boolean returnTrue() {
        return true;
    }
}
