package fr.polytech.messager.client.gui.model;

import fr.polytech.messager.client.gui.model.Message;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MessageTableModel extends AbstractTableModel {

    private List<Message> messages = new ArrayList<>();

    private static final String[] HEADERS = {"Author", "Content"};

    @Override
    public String getColumnName(int columnIndex) {
        return HEADERS[columnIndex];
    }

    @Override
    public int getRowCount() {
        return messages.size();
    }

    @Override
    public int getColumnCount() {
        return HEADERS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Message message = messages.get(rowIndex);
        switch (columnIndex) {
            case 0: return message.getAuthor();
            case 1: return message.getContent();
            case 2: return "X";
            default: return null;
        }
    }

    public void removeMessage(int rowIndex) {
        messages.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clear() {
        messages.clear();
    }

    public void addAll(List<Message> messages) {
        if (messages != null) {
            this.messages.addAll(messages);
            fireTableDataChanged();
        }
    }

    public Long getMessageIdAt(int selectedRow) {
        return messages.get(selectedRow).getId();
    }
}
