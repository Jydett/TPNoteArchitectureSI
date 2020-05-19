package fr.polytech.messager.client.gui.controller;

import fr.polytech.messager.client.gui.view.View;

public abstract class Controller<T extends View> {

    protected final MainController rooter;
    protected T currentView;

    public Controller(MainController rooter) {
        this.rooter = rooter;
    }

    protected void setView(T currentView) {
        this.currentView = currentView;
    }

    public void showView() {
        this.currentView.show();
    }

    public void disposeView() {
        this.currentView.dispose();
    }
}