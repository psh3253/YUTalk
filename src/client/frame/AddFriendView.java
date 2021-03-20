package client.frame;

import client.listener.AddFriendButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddFriendView extends JFrame {
    public AddFriendView(FriendListView view) {
        setTitle("친구추가");
        setSize(300, 200);
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

        JLabel titleLabel = new JLabel("친구 추가");
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel, BorderLayout.WEST);

        JTextField friendIdField = new JTextField(10);
        friendIdField.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        addFriendPanel.add(friendIdField, gbc);

        JLabel infoLabel = new JLabel("추가하고자 하는 친구 ID를 입력해주세요");
        infoLabel.setFont(smallFont);
        infoLabel.setForeground(Color.GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        addFriendPanel.add(infoLabel, gbc);

        JButton addFriendButton = new JButton("친구 추가");
        addFriendButton.setFont(font);
        addFriendButton.addActionListener(new AddFriendButtonListener(view, friendIdField));
        addFriendButtonPanel.add(addFriendButton, BorderLayout.EAST);

        setVisible(true);
    }

}
