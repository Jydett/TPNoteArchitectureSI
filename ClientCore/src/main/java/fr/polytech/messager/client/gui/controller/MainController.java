package fr.polytech.messager.client.gui.controller;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import fr.polytech.messager.client.gui.io.MessageClientAsyncWrapper;
import fr.polytech.messager.client.gui.io.MessagerClient;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class MainController {

    public static void invoke(MessagerClient client) {
        EventQueue.invokeLater(() -> {
            try {
                new MainController(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Getter
    private final MessageClientAsyncWrapper messagerClient;
    private Controller currentController;
    @Getter @Setter
    private String authToken;

    public MainController(MessagerClient messagerClient) {
        FlatCyanLightIJTheme.install();
        this.messagerClient = new MessageClientAsyncWrapper(messagerClient);
        currentController = new LoginController(this);
        currentController.showView();
    }

    public void changeController(Controller controller) {
        currentController.disposeView();
        currentController = controller;
        currentController.showView();
    }
}
