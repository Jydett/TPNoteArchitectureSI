package fr.polytech.messager.client.gui.controller;

import fr.polytech.messager.client.gui.model.Message;
import fr.polytech.messager.client.gui.model.MessageTableModel;
import fr.polytech.messager.client.gui.view.MessageTable;
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

    private void deleteMessage(Long messageId, Consumer<Void> callback) {
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

    private void getAllMessages(Consumer<List<Message>> res) {
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

    private void getMyMessage(Consumer<List<Message>> res) {
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

    private void getMessageFrom(String username, Consumer<List<Message>> res) {
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

    private void saveMessage(Long messageId, String content, Consumer<Void> res) {
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

    public void getAllMessagesAction(MessageTable messageTable) {
        getAllMessages(allMessages -> fillModel(messageTable.getTableModel(), allMessages));
    }

    public void getMyMessageAction(MessageTable messageTable) {
        getMyMessage(allMessages -> fillModel(messageTable.getTableModel(), allMessages));
    }

    public void getMessageFrom(MessageTable messageTable, String username) {
        getMessageFrom(username, allMessages -> fillModel(messageTable.getTableModel(), allMessages));
    }

    public void saveMessageAction(MessageTableModel tableModel, Message toUpdate, String content) {
        saveMessage(toUpdate.getId(), content, v -> {
            toUpdate.setContent(content);
            int rowIndex = tableModel.indexOf(toUpdate);
            tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
        });
    }

    private void fillModel(MessageTableModel tableModel, List<Message> allMessages) {
        tableModel.clear();
        tableModel.addAll(allMessages);
    }

    public void deleteMessageAction(MessageTableModel tableModel, Message message) {
        deleteMessage(message.getId(), v -> tableModel.removeMessage(tableModel.indexOf(message)));
    }
}
