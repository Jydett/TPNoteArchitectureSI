package fr.polytech.messager.client.gui.controller;

import fr.polytech.messager.client.gui.view.LoginView;

public class LoginController extends Controller<LoginView> {
    public LoginController(MainController rooter) {
        super(rooter);
        setView(new LoginView(this));
    }

    public void register(String login, String psw) {
        if ("".equals(login) || "".equals(psw)) {
            currentView.showError("Please fill every fields", "Error registering");
        } else {
            currentView.setLoading(true);
            rooter.getMessagerClient().register(login, psw, e ->
                currentView.showError("A user with the same name already exist", "Error register")
            , v -> {
                authentificate(login, psw);
                currentView.setLoading(false);
            });
        }
    }

    public void authentificate(String login, String psw) {
        currentView.setLoading(true);
        rooter.getMessagerClient().getAuthToken(login, psw, e -> {
            currentView.showError(e.getMessage(), "Error auth");
            currentView.setLoading(false);
            },
            res -> {
                rooter.setAuthToken(res);
                nextView();
                currentView.setLoading(false);
            }
        );
    }

    private void nextView() {
        rooter.changeController(new MessagerController(rooter));
    }

}
