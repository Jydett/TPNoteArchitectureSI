package fr.polytech.messager.client.gui.controller;

import fr.polytech.messager.client.gui.model.Message;
import fr.polytech.messager.client.gui.view.MessagerView;

import java.util.List;
import java.util.function.Consumer;

public class MessagerController extends Controller<MessagerView> {
    public MessagerController(MainController rooter, String login) {
        super(rooter);
        setView(new MessagerView(this));
        currentView.setConnectedAs(login);
    }

    public void sendMessage(String text) {
        if (text.trim().isEmpty()) {
            currentView.showError("Votre message ne peux pas être vide", "Erreur");
            return;
        }
        currentView.setLoading(true);
        rooter.getMessagerClient().sendMessage(rooter.getAuthToken(), text, e -> {
            e.printStackTrace();
            currentView.showError("Server responded : " + e.getMessage(), "Erreur");
            currentView.setLoading(false);
        }, v -> {
            currentView.setLoading(false);
            currentView.toast("Message envoyé !");
            currentView.clearMessageBox();
        });
    }

    public void deleteMessage(Long messageId, Consumer<Void> callback) {
        currentView.setLoading(true);
        rooter.getMessagerClient().delete(rooter.getAuthToken(), messageId, e -> {
            e.printStackTrace();
            currentView.showError("Server responded : " + e.getMessage(), "Erreur");
            currentView.setLoading(false);
        }, cal -> {
            currentView.setLoading(false);
            callback.accept(cal);
        });
    }

    public void getAllMessages(Consumer<List<Message>> res) {
        currentView.setLoading(true);
        rooter.getMessagerClient().getAllMessages(e -> {
            e.printStackTrace();
            currentView.showError("Server responded : " + e.getMessage(), "Erreur");
            currentView.setLoading(false);
            res.accept(null);
        }, cal -> {
            currentView.setLoading(false);
            res.accept(cal);
        });
    }

    public void getMyMessage(Consumer<List<Message>> res) {
        currentView.setLoading(true);
        rooter.getMessagerClient().getMyMessage(rooter.getAuthToken(), e -> {
            e.printStackTrace();
            currentView.showError("Server responded : " + e.getMessage(), "Erreur");
            currentView.setLoading(false);
            res.accept(null);
        }, cal -> {
            currentView.setLoading(false);
            res.accept(cal);
        });
    }

    public void getMessageFrom(String username, Consumer<List<Message>> res) {
        currentView.setLoading(true);
        rooter.getMessagerClient().getMessageFrom(username, e -> {
            e.printStackTrace();
            currentView.showError("Server responded : " + e.getMessage(), "Erreur");
            currentView.setLoading(false);
            res.accept(null);
        }, cal -> {
            currentView.setLoading(false);
            res.accept(cal);
        });
    }

    public void saveMessage(Long messageId, String content, Consumer<Void> res) {
        if (content.trim().isEmpty()) {
            currentView.showError("Votre message ne peux pas être vide", "Erreur");
            return;
        }
        currentView.setLoading(true);
        rooter.getMessagerClient().updateMessage(rooter.getAuthToken(), messageId, content, e -> {
            currentView.showError("Server responded : " + e.getMessage(), "Erreur");
            currentView.setLoading(false);
        }, cal -> {
            currentView.setLoading(false);
            res.accept(cal);
        });
    }
}
