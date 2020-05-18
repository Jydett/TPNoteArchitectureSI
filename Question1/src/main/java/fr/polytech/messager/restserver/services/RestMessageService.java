package fr.polytech.messager.restserver.services;

import fr.polytech.messager.doa.init.HibernateDaoInitializer;
import fr.polytech.messager.doa.message.MessageDao;
import fr.polytech.messager.doa.user.UserDao;
import fr.polytech.messager.services.MessageService;

public class RestMessageService extends MessageService {
    public RestMessageService() {
        super(HibernateDaoInitializer.getMessageDao(), HibernateDaoInitializer.getUserDao());
    }
}
