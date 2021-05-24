package client.frame;

import client.data.DataProvider;
import client.listener.SendMessageButtonListener;
import client.model.LoginAccount;
import client.model.Member;
import client.model.Message;
import client.model.OpenedChatRoomViewList;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ChatRoomView extends JFrame {

    private JTextArea messageListArea;
    private int roomId;

    public ChatRoomView(int roomId) {
        this.roomId = roomId;
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

        messageListArea = new JTextArea();
        messageListArea.setFont(font);
        messageListArea.setForeground(Color.BLACK);
        messageListArea.setLineWrap(true);
        messageListArea.setEditable(false);
        ArrayList<Message> messageList = DataProvider.getInstance().getMessageData(roomId);
        messageListArea.setText("");
        for(int i=0; i<messageList.size(); i++) {
            String text = messageListArea.getText();
            messageListArea.setText(text + messageList.get(i).getUserId() + " >> " + messageList.get(i).getMessage() + "\n");
        }

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

        JPanel sendMessageButtonPanel = new JPanel();
        sendMessageButtonPanel.setLayout(new BorderLayout());
        sendMessageButtonPanel.add(new JPanel(), BorderLayout.NORTH);
        sendMessageButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
        sendMessageButtonPanel.add(new JPanel(), BorderLayout.EAST);
        sendMessageButtonPanel.add(new JPanel(), BorderLayout.WEST);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        southPanel.add(sendMessageButtonPanel, gbc);

        JButton sendMessageButton = new JButton("전송");
        sendMessageButton.setFont(font);
        sendMessageButton.addActionListener(new SendMessageButtonListener(roomId, messageArea));
        sendMessageButtonPanel.add(sendMessageButton, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void repaint() {
        super.repaint();
        ArrayList<Message> messageList = DataProvider.getInstance().getMessageData(roomId);
        messageListArea.setText("");
        for(int i=0; i<messageList.size(); i++) {
            //messageListArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            String text = messageListArea.getText();
            if(messageList.get(i).getUserId().equals(LoginAccount.getInstance().getMyInfo().getUserId())) {
                //messageListArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                messageListArea.setText(text + messageList.get(i).getUserId() + " >> " + messageList.get(i).getMessage() + "\n");
            } else {
                //messageListArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                messageListArea.setText(text + messageList.get(i).getUserId() + " >> " + messageList.get(i).getMessage() + "\n");
            }
        }
    }
}
