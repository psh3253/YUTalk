package client.frame;

import client.data.DataProvider;
import client.model.LoginAccount;
import client.model.Member;
import client.model.OpenedChatRoomViewList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatRoomView extends JFrame {
    public ChatRoomView(int roomId) {
        setTitle(DataProvider.getInstance().getChatRoom(roomId).getName());
        setSize(370, 580);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().remove(roomId);
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
        northPanel.setLayout(new BorderLayout());
        northPanel.add(new JPanel(), BorderLayout.NORTH);
        northPanel.add(new JPanel(), BorderLayout.SOUTH);
        northPanel.add(new JPanel(), BorderLayout.WEST);
        northPanel.add(new JPanel(), BorderLayout.EAST);
        container.add(northPanel, BorderLayout.NORTH);

        //JPanel messageListPanel = new JPanel();
        //messageListPanel.setLayout(new GridBagLayout());

        JTextArea messageListArea = new JTextArea();
        messageListArea.setFont(font);
        messageListArea.setLineWrap(true);
        messageListArea.setEnabled(false);

        JScrollPane messageListPanelScroll = new JScrollPane(messageListArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(messageListPanelScroll, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        container.add(southPanel, BorderLayout.SOUTH);

        JPanel northCenterPanel = new JPanel();
        northCenterPanel.setLayout(new BorderLayout());
        northPanel.add(northCenterPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(DataProvider.getInstance().getChatRoom(roomId).getName());
        titleLabel.setFont(titleFont);
        northCenterPanel.add(titleLabel, BorderLayout.WEST);

        JButton contactButton = new JButton("대화 상대");
        contactButton.setFont(font);
        northCenterPanel.add(contactButton, BorderLayout.EAST);

        JTextArea messageArea = new JTextArea(3, 45);
        messageArea.setFont(font);
        messageArea.setLineWrap(true);

        JScrollPane messageAreaScrollPane = new JScrollPane(messageArea);
        messageAreaScrollPane.setPreferredSize(new Dimension(270, 90));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        southPanel.add(messageAreaScrollPane, gbc);

        JPanel sendButtonPanel = new JPanel();
        sendButtonPanel.setLayout(new BorderLayout());
        sendButtonPanel.add(new JPanel(), BorderLayout.NORTH);
        sendButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
        sendButtonPanel.add(new JPanel(), BorderLayout.EAST);
        sendButtonPanel.add(new JPanel(), BorderLayout.WEST);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        southPanel.add(sendButtonPanel, gbc);

        JButton sendButton = new JButton("전송");
        sendButton.setFont(font);
        sendButtonPanel.add(sendButton, BorderLayout.CENTER);


        /*JPanel trashPanel = new JPanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        southPanel.add(trashPanel, gbc);

         */

        setVisible(true);
    }
}
