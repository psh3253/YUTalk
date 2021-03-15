package client.listener;

import client.frame.FriendList;
import client.frame.Login;
import client.service.LoginService;
import client.service.RegisterService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonListener implements ActionListener {

    private JTextField userIdField;
    private JPasswordField passwordField;

    public LoginButtonListener(JTextField userIdField, JPasswordField passwordField) {
        this.userIdField = userIdField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton loginButton = (JButton) e.getSource();

        String userId = userIdField.getText();
        String password = new String(passwordField.getPassword());

        boolean result = LoginService.getInstance().login(loginButton, userId, password);
        if (!result)
            return;
        loginButton.getTopLevelAncestor().setVisible(false);
        new FriendList();
    }
}
