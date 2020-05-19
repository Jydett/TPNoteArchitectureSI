package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.controller.MessagerController;
import fr.polytech.messager.client.gui.model.Message;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Function;

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
        contentPane.addTab("Voir tous mes messages", messageTable(true, (a, table) -> {
            controller.getMyMessage(res -> {
                table.getTableModel().clear();
                table.getTableModel().addAll(res);
            });
        }, table -> {
            JButton update = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable jTable = table.getTable();
                    int selectedRow = jTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Message toUpdate = table.getTableModel().getMessageAt(selectedRow);
                        String updated = JOptionPane.showInputDialog(null, "Update", toUpdate.getContent());
                        if (updated != null) {
                            controller.saveMessage(toUpdate.getId(), updated, v -> {
                                toUpdate.setContent(updated);
                                table.getTableModel().fireTableRowsUpdated(selectedRow, selectedRow);
                            });
                        }
                    }
                }
            });
            update.setText("Update");
            return Collections.singletonList(update);
        }));
        JTextField usernameField = new JTextField();
        usernameField.setColumns(30);
        contentPane.addTab("Voir tous les messages d'un utilisateur", messageTable(false, (a, table) -> {
            controller.getMessageFrom(usernameField.getText(), res -> {
                table.getTableModel().clear();
                table.getTableModel().addAll(res);
            });
        }, t -> Collections.singletonList(usernameField)));
    }

    private JPanel messageTable(boolean isModifiable, BiConsumer<ActionEvent, MessageTable> onRefresh) {
        return messageTable(isModifiable, onRefresh, t -> Collections.emptyList());
    }

    private JPanel messageTable(boolean isModifiable, BiConsumer<ActionEvent, MessageTable> onRefresh, Function<MessageTable, List<Component>> fields) {
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
