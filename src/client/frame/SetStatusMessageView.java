package client.frame;

import client.listener.SetStatusMessageButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetStatusMessageView extends JFrame {
    public SetStatusMessageView(FriendListView view) {
        setTitle("상태 메시지 설정");
        setSize(400, 200);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                view.setEnabled(true);
            }
        });

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);
        Font smallFont = new Font("맑은 고딕", Font.PLAIN, 12);
        Font titleFont = new Font("맑은 고딕", Font.BOLD, 20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new JPanel(), BorderLayout.WEST);
        container.add(new JPanel(), BorderLayout.EAST);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.add(new JPanel(), BorderLayout.NORTH);
        northPanel.add(new JPanel(), BorderLayout.SOUTH);
        northPanel.add(new JPanel(), BorderLayout.EAST);
        northPanel.add(new JPanel(), BorderLayout.WEST);
        container.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(new JPanel(), BorderLayout.NORTH);
        centerPanel.add(new JPanel(), BorderLayout.SOUTH);
        centerPanel.add(new JPanel(), BorderLayout.EAST);
        centerPanel.add(new JPanel(), BorderLayout.WEST);
        container.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(new JPanel(), BorderLayout.NORTH);
        southPanel.add(new JPanel(), BorderLayout.SOUTH);
        southPanel.add(new JPanel(), BorderLayout.EAST);
        southPanel.add(new JPanel(), BorderLayout.WEST);
        container.add(southPanel, BorderLayout.SOUTH);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        northPanel.add(titlePanel, BorderLayout.CENTER);

        JPanel addFriendPanel = new JPanel();
        addFriendPanel.setLayout(new GridBagLayout());
        centerPanel.add(addFriendPanel, BorderLayout.CENTER);

        JPanel addFriendButtonPanel = new JPanel();
        addFriendButtonPanel.setLayout(new BorderLayout());
        southPanel.add(addFriendButtonPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("상태 메시지 설정");
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel, BorderLayout.WEST);

        JTextField statusMessageField = new JTextField(10);
        statusMessageField.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        addFriendPanel.add(statusMessageField, gbc);

        JLabel infoLabel = new JLabel("설정하고자 하는 상태 메시지를 입력해주세요(최대 15자)");
        infoLabel.setFont(smallFont);
        infoLabel.setForeground(Color.GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addFriendPanel.add(infoLabel, gbc);

        JButton setStatusMessageButton = new JButton("설정");
        setStatusMessageButton.setFont(font);
        setStatusMessageButton.addActionListener(new SetStatusMessageButtonListener(view, statusMessageField));
        addFriendButtonPanel.add(setStatusMessageButton, BorderLayout.EAST);

        setVisible(true);
    }
}
