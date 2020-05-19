package fr.polytech.messager.client.gui.view;

import fr.polytech.messager.client.gui.controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends View {

    private final LoginController controller;
    private JTextField textFieldMail;
    private JPasswordField textFieldPassword;
    private JTextField textFieldIDReg;
    private JTextField textFieldIDLogin;
    private JPasswordField textFieldPasswordLogin;
    private JLabel lblEnterYourId;
    private JLabel lblEnterYourPassword;
    private JLabel lblEnterMailReg;
    private JLabel lblEnterIdReg;
    private JLabel lblEnterPasswordReg;
    private JPanel panelMailReg;
    private JPanel panelIDReg;
    private JPanel panelPwdReg;
    private JPanel panelIdLogin;
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
        frame.setBounds(100, 100, 166, 164);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
        frame.setMinimumSize(new Dimension(270, 220));

        JPanel panelLogin = new JPanel();
        frame.getContentPane().add(panelLogin);
        GridBagLayout gbl_panelLogin = new GridBagLayout();
        gbl_panelLogin.columnWidths = new int[]{120};
        gbl_panelLogin.rowHeights = new int[]{50, 50, 30};
        gbl_panelLogin.columnWeights = new double[]{0.0};
        gbl_panelLogin.rowWeights = new double[]{0.0, 0.0, 0.0};
        panelLogin.setLayout(gbl_panelLogin);

        panelIdLogin = new JPanel();
        GridBagConstraints gbc_panelIdLogin = new GridBagConstraints();
        gbc_panelIdLogin.fill = GridBagConstraints.BOTH;
        gbc_panelIdLogin.insets = new Insets(0, 0, 5, 0);
        gbc_panelIdLogin.gridx = 0;
        gbc_panelIdLogin.gridy = 0;
        panelLogin.add(panelIdLogin, gbc_panelIdLogin);

        lblEnterYourId = new JLabel("Enter your ID :");
        panelIdLogin.add(lblEnterYourId);
        lblEnterYourId.setAlignmentX(Component.CENTER_ALIGNMENT);

        textFieldIDLogin = new JTextField();
        panelIdLogin.add(textFieldIDLogin);
        textFieldIDLogin.setColumns(10);

        panelPwdLogin = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        panelLogin.add(panelPwdLogin, gbc_panel);

        lblEnterYourPassword = new JLabel("Enter your password :");
        panelPwdLogin.add(lblEnterYourPassword);
        lblEnterYourPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        //password
        textFieldPasswordLogin = new JPasswordField();
        textFieldPasswordLogin.addActionListener(a -> authentificate());
        panelPwdLogin.add(textFieldPasswordLogin);
        textFieldPasswordLogin.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 2;
        panelLogin.add(btnLogin, gbc_btnLogin);

        JPanel panelRegister = new JPanel();
        frame.getContentPane().add(panelRegister);
        GridBagLayout gbl_panelRegister = new GridBagLayout();
        gbl_panelRegister.columnWidths = new int[]{150};
        gbl_panelRegister.rowHeights = new int[]{50, 50, 50, 30};
        gbl_panelRegister.columnWeights = new double[]{0.0};
        gbl_panelRegister.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        panelRegister.setLayout(gbl_panelRegister);

        panelMailReg = new JPanel();
        panelMailReg.setMinimumSize(new Dimension(100, 30));
        GridBagConstraints gbc_panelMailReg = new GridBagConstraints();
        gbc_panelMailReg.fill = GridBagConstraints.BOTH;
        gbc_panelMailReg.gridx = 0;
        gbc_panelMailReg.gridy = 0;
        panelRegister.add(panelMailReg, gbc_panelMailReg);

        lblEnterMailReg = new JLabel("Enter your mail :");
        panelMailReg.add(lblEnterMailReg);
        textFieldMail = new JTextField();
        panelMailReg.add(textFieldMail);
        textFieldMail.setColumns(10);

        panelIDReg = new JPanel();
        GridBagConstraints gbc_panelIDReg = new GridBagConstraints();
        gbc_panelIDReg.fill = GridBagConstraints.BOTH;
        gbc_panelIDReg.gridx = 0;
        gbc_panelIDReg.gridy = 1;
        panelRegister.add(panelIDReg, gbc_panelIDReg);

        lblEnterIdReg = new JLabel("Enter your ID :");
        panelIDReg.add(lblEnterIdReg);

        textFieldIDReg = new JTextField();
        panelIDReg.add(textFieldIDReg);
        textFieldIDReg.setColumns(10);

        panelPwdReg = new JPanel();
        GridBagConstraints gbc_panelPwdReg = new GridBagConstraints();
        gbc_panelPwdReg.fill = GridBagConstraints.BOTH;
        gbc_panelPwdReg.gridx = 0;
        gbc_panelPwdReg.gridy = 2;

        lblEnterPasswordReg = new JLabel("Enter your password :");
        panelPwdReg.add(lblEnterPasswordReg);

        textFieldPassword = new JPasswordField();
        textFieldPassword.addActionListener(a -> register());
        textFieldPassword.setColumns(10);
        panelPwdReg.add(textFieldPassword);
        panelRegister.add(panelPwdReg, gbc_panelPwdReg);

        JButton btnRegister = new JButton("Register");
        GridBagConstraints gbc_btnRegister = new GridBagConstraints();
        gbc_btnRegister.gridx = 0;
        gbc_btnRegister.gridy = 3;
        panelRegister.add(btnRegister, gbc_btnRegister);


        btnLogin.addActionListener(e -> authentificate());

        btnRegister.addActionListener(e -> register());
    }

    private void register() {
        controller.register(textFieldMail.getText(), textFieldIDReg.getText(), textFieldPassword.getText());
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