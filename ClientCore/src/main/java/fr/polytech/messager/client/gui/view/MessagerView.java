package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.controller.MessagerController;
import fr.polytech.messager.client.gui.model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.function.Consumer;

public class MessagerView extends View {

    public static final String APP_TITLE = "Connecté à messager";

    private MessagerController controller;
    private JTextArea message;
    private JPanel messageBox;
    private JTabbedPane tabbedPane;

    public MessagerView(MessagerController controller) {
        super(new JFrame(APP_TITLE));
        frame.setIconImages(getIcons("icon"));
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        tabbedPane = new JTabbedPane();
        frame.setContentPane(tabbedPane);
        frame.setMinimumSize(new Dimension(700, 550));
        initMessageBox();

        tabbedPane.addTab("Envoyer un message", messageBox);
        tabbedPane.addTab("Voir tous les messages", messageTable(false, controller::getAllMessagesAction));
        tabbedPane.addTab("Voir tous mes messages", messageTable(true, controller::getMyMessageAction, table -> {
            JButton update = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable jTable = table.getTable();
                    int selectedRow = jTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Message toUpdate = table.getTableModel().getMessageAt(selectedRow);
                        String updated = JOptionPane.showInputDialog(null, "Update", toUpdate.getContent());
                        if (updated != null) {
                            controller.saveMessageAction(table.getTableModel(), toUpdate, updated);
                        }
                    }
                }
            });
            update.setText("Update");
            return Collections.singletonList(update);
        }));
        JTextField usernameField = new JTextField();
        usernameField.setColumns(30);
        tabbedPane.addTab("Voir tous les messages d'un utilisateur", messageTable(false,
                table -> controller.getMessageFrom(table, usernameField.getText()), t -> Collections.singletonList(usernameField)));
    }

    private JPanel messageTable(boolean isModifiable, Consumer<MessageTable> onRefresh) {
        return messageTable(isModifiable, onRefresh, t -> Collections.emptyList());
    }

    private JPanel messageTable(boolean isModifiable, Consumer<MessageTable> onRefresh, MessageTable.FieldsProvider fields) {
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

    @Override
    public void setLoading(boolean isLoading) {
        if (isLoading) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        } else {
            frame.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void setConnectedAs(String login) {
        frame.setTitle(APP_TITLE + " - " + login);
    }
}
