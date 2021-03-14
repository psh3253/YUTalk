package client.frame;

import client.network.ConnectionTermination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatList extends JFrame {

    public ChatList() {
        setTitle("YUTalk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ConnectionTermination.getInstance().disconnect();
            }
        });

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);
        Font titleFont = new Font("맑은 고딕", Font.BOLD, 20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.add(new JPanel(), BorderLayout.NORTH);
        northPanel.add(new JPanel(), BorderLayout.SOUTH);
        northPanel.add(new JPanel(), BorderLayout.WEST);
        northPanel.add(new JPanel(), BorderLayout.EAST);
        container.add(northPanel, BorderLayout.NORTH);

        JPanel chatListPanel = new JPanel();
        chatListPanel.setLayout(new GridBagLayout());

        JScrollPane friendListPanelScroll = new JScrollPane(chatListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(friendListPanelScroll, BorderLayout.CENTER);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        container.add(menuPanel, BorderLayout.SOUTH);

        JPanel northCenterPanel = new JPanel();
        northCenterPanel.setLayout(new BorderLayout());
        northPanel.add(northCenterPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("채팅");
        titleLabel.setFont(titleFont);
        northCenterPanel.add(titleLabel, BorderLayout.WEST);

        JButton addChatButton = new JButton("새로운 채팅");
        addChatButton.setFont(font);
        northCenterPanel.add(addChatButton, BorderLayout.EAST);

        JButton friendButton = new JButton("친구");
        friendButton.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        menuPanel.add(friendButton, gbc);

        JButton chatButton = new JButton("채팅");
        chatButton.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        menuPanel.add(chatButton, gbc);

        JButton settingButton = new JButton("설정");
        settingButton.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 0;
        menuPanel.add(settingButton, gbc);

        setSize(370, 550);
        setVisible(true);
    }
}
