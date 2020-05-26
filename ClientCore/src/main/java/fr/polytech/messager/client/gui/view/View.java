package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.utils.Toast;
import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        } else {
            frame.setEnabled(true);
            frame.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void toast(String message) {
        Toast.showToast((JComponent) frame.getContentPane(), message);
    }

    protected java.util.List<Image> getIcons(String iconName) {
        return Stream.of(16, 32, 64, 128)
        .map(size -> String.format("/icon/%s_%d.png", iconName, size))
        .map(p -> getClass().getResource(p))
        .map(ImageIcon::new)
        .map(ImageIcon::getImage)
        .collect(Collectors.toList());
    }
}