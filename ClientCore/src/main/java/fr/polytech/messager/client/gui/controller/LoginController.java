package fr.polytech.messager.client.gui.controller;

import fr.polytech.messager.client.gui.view.LoginView;

public class LoginController extends Controller<LoginView> {
    public LoginController(MainController rooter) {
        super(rooter);
        setView(new LoginView(this));
    }

    public void register(String email, String login, String psw) {
        if ("".equals(login) || "".equals(psw) || "".equals(email)) {
            currentView.showError("Please fill every fields", "Error registering");
        } else {
            currentView.setLoading(true);
            try {
                rooter.getMessagerClient().register(login, psw);
                authentificate(login, psw);
            } catch (Exception e) {//TODO error
                currentView.showError("A user with the same name already exist", "Error register");
            }
            currentView.setLoading(false);
        }
    }

    public void authentificate(String login, String psw) {
        currentView.setLoading(true);
        try {
            rooter.setAuthToken(rooter.getMessagerClient().getAuthToken(login, psw));
            nextView();
        } catch (Exception e) {//TODO error
            currentView.showError(e.getMessage(), "Error register");
        }
        currentView.setLoading(false);
    }

    private void nextView() {
        rooter.changeController(new MessagerController(rooter));
    }

}
