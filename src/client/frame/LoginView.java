package client.frame;

import client.listener.LoginButtonListener;
import client.network.ConnectionTermination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginView extends JFrame {

    public LoginView() {
        setTitle("YUTalk");
        setSize(370, 580);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ConnectionTermination.getInstance().disconnect();
            }
        });

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);
        Font logoFont = new Font("맑은 고딕", Font.BOLD, 50);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new JPanel(), BorderLayout.NORTH);
        container.add(new JPanel(), BorderLayout.SOUTH);
        container.add(new JPanel(), BorderLayout.WEST);
        container.add(new JPanel(), BorderLayout.EAST);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        container.add(panel, BorderLayout.CENTER);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.add(new JPanel(), BorderLayout.NORTH);
        northPanel.add(new JPanel(), BorderLayout.SOUTH);
        northPanel.add(new JPanel(), BorderLayout.WEST);
        northPanel.add(new JPanel(), BorderLayout.EAST);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(northPanel, gbc);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(loginPanel, gbc);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(new JPanel(), BorderLayout.NORTH);
        southPanel.add(new JPanel(), BorderLayout.EAST);
        southPanel.add(new JPanel(), BorderLayout.WEST);
        southPanel.add(new JPanel(), BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(southPanel, gbc);

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridBagLayout());
        northPanel.add(logoPanel, BorderLayout.CENTER);

        JLabel logoLabel = new JLabel("YU");
        logoLabel.setFont(logoFont);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        logoPanel.add(logoLabel, gbc);

        JLabel logoLabel2 = new JLabel("TALK");
        logoLabel2.setFont(logoFont);
        logoLabel2.setHorizontalAlignment(JLabel.CENTER);
        logoLabel2.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        logoPanel.add(logoLabel2, gbc);

        JLabel userIdLabel = new JLabel("아이디");
        userIdLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        loginPanel.add(userIdLabel, gbc);

        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(new JPanel(), gbc);

        JTextField userIdField = new JTextField(10);
        userIdField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(userIdField, gbc);

        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("로그인");
        loginButton.setFont(font);

        loginButton.addActionListener(new LoginButtonListener(userIdField, passwordField));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        JButton registerButton = new JButton("회원가입");
        registerButton.setFont(font);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new RegisterView();
            }
        });
        southPanel.add(registerButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
