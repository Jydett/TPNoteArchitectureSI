package fr.polytech.messager.client.gui.view;

import javax.swing.*;
import java.awt.*;

public abstract class View {

    protected JFrame frame;

    public View(JFrame frame) {
        this.frame = frame;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public abstract void show();

    public abstract void dispose();

    public void showError(String errorDesc, String errorTitle) {
        JOptionPane.showMessageDialog(frame, errorDesc, errorTitle, JOptionPane.ERROR_MESSAGE);
    }

    public void setLoading(boolean isLoading) {
        if (isLoading) {
            frame.setEnabled(false);
            frame.setCursor(Cursor.WAIT_CURSOR);
        } else {
            frame.setEnabled(true);
            frame.setCursor(Cursor.getDefaultCursor());
        }
    }
}