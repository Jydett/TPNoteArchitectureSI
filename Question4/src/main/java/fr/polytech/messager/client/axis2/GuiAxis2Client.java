package fr.polytech.messager.client.axis2;

import fr.polytech.messager.client.gui.controller.MainController;

public class GuiAxis2Client {

    public static void main(String[] args) throws Exception {
        AxisClient axisClient = new AxisClient();
        MainController.invoke(axisClient);
    }
}
