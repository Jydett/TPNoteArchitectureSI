package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.controller.MessagerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.BiConsumer;

public class MessagerView extends View {

    private MessagerController controller;
    private JTextArea message;
    private JPanel messageBox;

    public MessagerView(MessagerController controller) {
        super(new JFrame("Connecté à messager"));
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        JTabbedPane contentPane = new JTabbedPane();
        frame.setContentPane(contentPane);
        frame.setMinimumSize(new Dimension(700, 550));
        initMessageBox();

        contentPane.addTab("Envoyer un message", messageBox);
        contentPane.addTab("Voir tous les messages", messageTable(false, (a, tableModel) -> {
            controller.getAllMessages(allMessages -> {
                tableModel.getTableModel().clear();
                tableModel.getTableModel().addAll(allMessages);
            });
        }));
        contentPane.addTab("Voir tous mes messages", messageTable(true, (a, tableModel) -> {
            controller.getMyMessage(res -> {
                tableModel.getTableModel().clear();
                tableModel.getTableModel().addAll(res);
            });
        }));
        JTextField usernameField = new JTextField();
        usernameField.setColumns(30);
        contentPane.addTab("Voir tous les messages d'un utilisateur", messageTable(false, (a, tableModel) -> {
            controller.getMessageFrom(usernameField.getText(), res -> {
                tableModel.getTableModel().clear();
                tableModel.getTableModel().addAll(res);
            });
        }, usernameField));
    }

    private JPanel messageTable(boolean isModifiable, BiConsumer<ActionEvent, MessageTable> onRefresh, Component... fields) {
        JPanel allMessageTable = new JPanel();
        allMessageTable.setLayout(new BorderLayout(5, 5));
        allMessageTable.add(new MessageTable(controller, isModifiable, onRefresh, fields), BorderLayout.CENTER);
        return allMessageTable;
    }

    private void initMessageBox() {
        messageBox = new JPanel();
        messageBox.setLayout(new BorderLayout(5, 5));
        message = new JTextArea();
        messageBox.add(message, BorderLayout.CENTER);
        JButton sendMessage = new JButton("Envoyer");
        sendMessage.addActionListener(a -> controller.sendMessage(message.getText()));
        messageBox.add(sendMessage, BorderLayout.EAST);
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void dispose() {
        frame.setVisible(false);
    }

    public void clearMessageBox() {
        message.setText(null);
    }
}
