package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.controller.MessagerController;
import fr.polytech.messager.client.gui.model.MessageTableModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.BiConsumer;

public class MessageTable extends JPanel {

    @Getter
    private final MessageTableModel tableModel;

    public MessageTable(MessagerController controller, boolean isModifiable, BiConsumer<ActionEvent, MessageTable> onRefresh, Component... fields) {
        tableModel = new MessageTableModel();
        JTable table = new JTable(tableModel);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (Component field : fields) {
            buttons.add(field);
        }
        JButton refreshButton = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRefresh.accept(e, MessageTable.this);
            }
        });
        refreshButton.setText("Refresh");
        buttons.add(refreshButton);
        //TODO faire l'update
        if (isModifiable) {
            JButton deleteButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    controller.deleteMessage(tableModel.getMessageIdAt(selectedRow), v -> {
                        tableModel.removeMessage(selectedRow);
                    });
                }
                }
            });
            deleteButton.setText("Delete");
            buttons.add(deleteButton);
        }
        add(buttons, BorderLayout.NORTH);
    }
}
