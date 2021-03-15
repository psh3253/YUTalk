package client.listener;

import client.frame.Login;
import client.service.RegisterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterButtonListener implements ActionListener {

    private JTextField userIdField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;

    public RegisterButtonListener(JTextField userIdField, JPasswordField passwordField, JPasswordField confirmPasswordField, JTextField nameField) {
        this.userIdField = userIdField;
        this.passwordField = passwordField;
        this.confirmPasswordField = confirmPasswordField;
        this.nameField = nameField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton registerButton = (JButton) e.getSource();

        String userId = userIdField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String name = nameField.getText();

        boolean result = RegisterService.getInstance().register(registerButton, userId, password, confirmPassword, name);
        if (!result)
            return;
        registerButton.getTopLevelAncestor().setVisible(false);
        new Login();
    }
}
