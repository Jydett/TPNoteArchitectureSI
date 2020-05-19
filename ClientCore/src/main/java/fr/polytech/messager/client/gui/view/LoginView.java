package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends View {

    private final LoginController controller;
    private JTextField textFieldIDLogin;
    private JPasswordField textFieldPasswordLogin;
    private JLabel lblEnterYourId;
    private JLabel lblEnterYourPassword;
    private JPanel panelPwdLogin;

    /**
     * Create the application.
     * @param LoginController
     */
    public LoginView(LoginController LoginController) {
        super(new JFrame());
        this.controller = LoginController;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame.setTitle("Please login or register");
        frame.setBounds(100, 100, 150, 164);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setMinimumSize(new Dimension(150, 220));

        lblEnterYourId = new JLabel("Enter your ID :");
        frame.getContentPane().add(lblEnterYourId);
        lblEnterYourId.setAlignmentX(Component.CENTER_ALIGNMENT);

        textFieldIDLogin = new JTextField();
        frame.getContentPane().add(textFieldIDLogin);
        textFieldIDLogin.setColumns(10);

        panelPwdLogin = new JPanel();
        frame.getContentPane().add(panelPwdLogin);

        lblEnterYourPassword = new JLabel("Enter your password :");
        frame.getContentPane().add(lblEnterYourPassword);
        lblEnterYourPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        //password
        textFieldPasswordLogin = new JPasswordField();
        frame.getContentPane().add(textFieldPasswordLogin);
        textFieldPasswordLogin.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(btnRegister);

        JPanel panelRegister = new JPanel();
        frame.getContentPane().add(panelRegister);

        textFieldPasswordLogin.addActionListener(a -> authentificate());
        btnLogin.addActionListener(e -> authentificate());
        btnRegister.addActionListener(e -> register());
    }

    private void register() {
        controller.register(textFieldIDLogin.getText(), textFieldPasswordLogin.getText());
    }

    private void authentificate() {
        controller.authentificate(textFieldIDLogin.getText(), textFieldPasswordLogin.getText());
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void dispose() {
        frame.setVisible(false);
    }
}