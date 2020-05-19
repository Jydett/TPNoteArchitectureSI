package fr.polytech.messager.client.gui.utils;

import javax.swing.*;
import java.awt.*;

public class Toast {
    private final JComponent component;
    private Point   location;
    private final String  message;
    private long duration; //in millisecond

    public Toast(JComponent comp, Point toastLocation, String msg, long forDuration) {
        this.component = comp;
        this.location = toastLocation;
        this.message = msg;
        this.duration = forDuration;

        if (this.component != null) {
            if (this.location == null) {
                this.location = component.getLocationOnScreen();
            }
            new Thread(() -> {
                JLabel tip = new JLabel(message);
                tip.setBackground(new Color(99, 222, 87));
                tip.setBorder(BorderFactory.createLineBorder(Color.black));
                int textOffset = tip.getFontMetrics(javax.swing.UIManager.getDefaults().getFont("Label.font")).stringWidth(message) / 2;
                Popup view  = PopupFactory.getSharedInstance().getPopup(component, tip, location.x + (component.getWidth() / 2) - (textOffset), location.y + component.getHeight() - 30);
                view.show();
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    view.hide();
                }
            }).start();
        }
    }

    public static void showToast(JComponent component, String message) {
        new Toast(component, null, message, 2000/*Default 2 Sec*/);
    }
}