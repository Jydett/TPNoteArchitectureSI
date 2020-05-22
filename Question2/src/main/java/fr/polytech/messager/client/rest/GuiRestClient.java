package fr.polytech.messager.client.rest;

import fr.polytech.messager.client.gui.controller.MainController;

public class GuiRestClient {
    public static void main(String[] args) {
        MainController.invoke(new RestClient());
    }
}
