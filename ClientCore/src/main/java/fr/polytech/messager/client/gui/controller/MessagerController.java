package fr.polytech.messager.client.gui.controller;

import fr.polytech.messager.client.gui.model.Message;
import fr.polytech.messager.client.gui.view.MessagerView;

import java.util.List;

public class MessagerController extends Controller<MessagerView> {
    public MessagerController(MainController rooter) {
        super(rooter);
        setView(new MessagerView(this));
    }

    public void sendMessage(String text) {
        try {
            rooter.getMessagerClient().sendMessage(rooter.getAuthToken(), text);
            currentView.clearMessageBox();
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            currentView.showError("TODO", "Error");
        }
    }

    public boolean deleteMessage(Long messageId) {
        try {
            rooter.getMessagerClient().delete(rooter.getAuthToken(), messageId);
            return true;
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            currentView.showError("TODO", "Error");
        }
        return false;
    }

    public List<Message> getAllMessages() {
        try {
            return rooter.getMessagerClient().getAllMessages();
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            currentView.showError("TODO", "Error");
        }
        return null;
    }

    public List<Message> getMyMessage() {
        try {
            return rooter.getMessagerClient().getMyMessage(rooter.getAuthToken());
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            currentView.showError("TODO", "Error");
        }
        return null;
    }

    public List<Message> getMessageFrom(String username) {
        try {
            return rooter.getMessagerClient().getMessageFrom(username);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            currentView.showError("TODO", "Error");
        }
        return null;
    }
}
