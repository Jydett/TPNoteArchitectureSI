package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.controller.MessagerController;
import fr.polytech.messager.client.gui.model.MessageTableModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MessageTable extends JPanel {

    @Getter
    private final MessageTableModel tableModel;
    @Getter
    private final JTable table;

    public MessageTable(MessagerController controller, boolean isModifiable, BiConsumer<ActionEvent, MessageTable> onRefresh, Function<MessageTable, List<Component>> fields) {
        tableModel = new MessageTableModel();
        table = new JTable(tableModel);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (Component field : fields.apply(this)) {
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
        if (isModifiable) {
            JButton deleteButton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    controller.deleteMessage(tableModel.getMessageAt(selectedRow).getId(), v -> {
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
