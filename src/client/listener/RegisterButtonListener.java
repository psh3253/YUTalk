package client.listener;

import client.frame.Login;
import client.service.RegisterService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterButtonListener implements ActionListener {

    private JTextField idField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;

    public RegisterButtonListener(JTextField idField, JPasswordField passwordField, JPasswordField confirmPasswordField, JTextField nameField) {
        this.idField = idField;
        this.passwordField = passwordField;
        this.confirmPasswordField = confirmPasswordField;
        this.nameField = nameField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton registerButton = (JButton) e.getSource();
        Font font = new Font("맑은 고딕", Font.PLAIN, 15);

        String userId = idField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String name = nameField.getText();

        boolean result = RegisterService.getInstance().register(registerButton, userId, password, confirmPassword, name);
        if (!result)
            return;
        JOptionPane.showMessageDialog(registerButton, "회원가입이 완료되었습니다.");
        registerButton.getTopLevelAncestor().setVisible(false);
        new Login();
    }
}
