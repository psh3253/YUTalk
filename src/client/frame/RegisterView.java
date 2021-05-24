package client.frame;

import client.listener.RegisterButtonListener;
import client.network.ConnectionTermination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterView extends JFrame {

    public RegisterView(Point location) {
        setTitle("YUTalk");
        setSize(370, 580);
        setLocation(location);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ConnectionTermination.getInstance().disconnect();
            }
        });

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);
        Font titleFont = new Font("맑은 고딕", Font.BOLD, 30);

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
        northPanel.add(new JPanel(), BorderLayout.WEST);
        northPanel.add(new JPanel(), BorderLayout.EAST);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(northPanel, gbc);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(registerPanel, gbc);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(new JPanel(), BorderLayout.SOUTH);
        southPanel.add(new JPanel(), BorderLayout.EAST);
        southPanel.add(new JPanel(), BorderLayout.WEST);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(southPanel, gbc);

        JLabel titleLabel = new JLabel("YUTalk 회원가입");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        northPanel.add(titleLabel, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("아래의 항목을 모두 적어주세요");
        infoLabel.setFont(font);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        northPanel.add(infoLabel, BorderLayout.SOUTH);

        JLabel userIdLabel = new JLabel("아이디");
        userIdLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(userIdLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(new JPanel(), gbc);

        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(passwordLabel, gbc);


        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인");
        confirmPasswordLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        registerPanel.add(new JPanel(), gbc);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        registerPanel.add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        registerPanel.add(new JPanel(), gbc);

        JTextField userIdField = new JTextField(10);
        userIdField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        registerPanel.add(userIdField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        registerPanel.add(new JPanel(), gbc);

        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 2;
        registerPanel.add(passwordField, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(10);
        confirmPasswordField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 3;
        registerPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        registerPanel.add(new JPanel(), gbc);

        JTextField nameField = new JTextField(10);
        nameField.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 5;
        registerPanel.add(nameField, gbc);

        JButton registerButton = new JButton("회원가입");
        registerButton.setFont(font);
        registerButton.addActionListener(new RegisterButtonListener(userIdField, passwordField, confirmPasswordField, nameField));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        registerPanel.add(registerButton, gbc);

        JLabel goLoginLabel = new JLabel("이미 계정이 있으신가요?");
        goLoginLabel.setFont(font);
        goLoginLabel.setHorizontalAlignment(JLabel.CENTER);
        southPanel.add(goLoginLabel, BorderLayout.NORTH);

        JButton goLoginButton = new JButton("로그인");
        goLoginButton.setFont(font);
        goLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginView(getLocation());
            }
        });
        southPanel.add(goLoginButton, BorderLayout.CENTER);

        setVisible(true);
    }
}
