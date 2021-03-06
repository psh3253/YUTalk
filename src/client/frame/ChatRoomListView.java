package client.frame;

import client.data.DataProvider;
import client.listener.GroupChatButtonListener;
import client.listener.LeaveChatRoomButtonListener;
import client.model.ChatRoom;
import client.model.OpenedViewList;
import client.network.ConnectionTermination;
import client.runnable.ThreadLock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatRoomListView extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    Font font = new Font("맑은 고딕", Font.PLAIN, 15);
    Font boldFont = new Font("맑은 고딕", Font.BOLD, 13);
    Font smallFont = new Font("맑은 고딕", Font.PLAIN, 12);
    Font miniFont = new Font("맑은 고딕", Font.PLAIN, 11);
    Font titleFont = new Font("맑은 고딕", Font.BOLD, 20);
    JPanel chatRoomListPanel;

    public ChatRoomListView(Point location) {
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

        chatRoomListPanel = new JPanel();
        chatRoomListPanel.setLayout(new GridBagLayout());

        JScrollPane chatRoomListPanelScroll = new JScrollPane(chatRoomListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(chatRoomListPanelScroll, BorderLayout.CENTER);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        container.add(menuPanel, BorderLayout.SOUTH);

        JPanel northCenterPanel = new JPanel();
        northCenterPanel.setLayout(new BorderLayout());
        northPanel.add(northCenterPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("채팅");
        titleLabel.setFont(titleFont);
        northCenterPanel.add(titleLabel, BorderLayout.WEST);

        JButton createChatRoomButton = new JButton("새로운 채팅");
        createChatRoomButton.setFont(font);
        createChatRoomButton.addActionListener(new GroupChatButtonListener());
        northCenterPanel.add(createChatRoomButton, BorderLayout.EAST);

        JButton friendButton = new JButton("친구");
        friendButton.setFont(font);
        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new FriendListView(getLocation());
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        menuPanel.add(friendButton, gbc);

        JButton chatButton = new JButton("채팅");
        chatButton.setEnabled(false);

        chatButton.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        menuPanel.add(chatButton, gbc);

        ArrayList<ChatRoom> chatRoomData = DataProvider.getInstance().getChatRoomData();
        int i = 0;
        String lastMessage;
        for (; i < chatRoomData.size(); i++) {
            ChatRoom chatRoom = chatRoomData.get(i);

            JPanel chatRoomInfoTabPanel = new JPanel();
            chatRoomInfoTabPanel.setLayout(new BorderLayout());
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.NORTH);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.SOUTH);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.EAST);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.WEST);
            chatRoomInfoTabPanel.setPreferredSize(new Dimension(getWidth() - 25, 70));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0;
            chatRoomListPanel.add(chatRoomInfoTabPanel, gbc);

            JPanel chatRoomInfoPanel = new JPanel();
            chatRoomInfoPanel.setLayout(new GridBagLayout());
            chatRoomInfoTabPanel.add(chatRoomInfoPanel, BorderLayout.CENTER);

            JLabel chatRoomNameLabel = new JLabel(chatRoomData.get(i).getName());
            chatRoomNameLabel.setPreferredSize(new Dimension(100, 20));
            chatRoomNameLabel.setFont(boldFont);
            chatRoomNameLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weighty = 0.5;
            chatRoomInfoPanel.add(chatRoomNameLabel, gbc);


            if(chatRoomData.get(i).getLastMessage() == null)
                lastMessage = "";
            else
                lastMessage = chatRoomData.get(i).getLastMessage();
            JLabel chatRoomLastMessageLabel = new JLabel(lastMessage);
            chatRoomLastMessageLabel.setFont(smallFont);
            chatRoomLastMessageLabel.setPreferredSize(new Dimension(100, 20));
            chatRoomLastMessageLabel.setForeground(Color.GRAY);
            chatRoomLastMessageLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 1;
            chatRoomInfoPanel.add(chatRoomLastMessageLabel, gbc);

            JLabel headcountLabel = new JLabel(String.valueOf(chatRoomData.get(i).getHeadcount()));
            headcountLabel.setFont(smallFont);
            headcountLabel.setForeground(Color.GRAY);
            headcountLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            chatRoomInfoPanel.add(headcountLabel, gbc);

            JPanel trashPanel = new JPanel();
            trashPanel.setLayout(new GridBagLayout());
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            chatRoomInfoPanel.add(trashPanel, gbc);

            JLabel chatRoomLastTimeLabel = new JLabel(convertLastTime(chatRoomData.get(i).getLastTime()));
            chatRoomLastTimeLabel.setFont(miniFont);
            chatRoomLastTimeLabel.setForeground(Color.GRAY);
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.weightx = 0;
            chatRoomInfoPanel.add(chatRoomLastTimeLabel, gbc);

            JLabel chatRoomUnreadMessageCountLabel = new JLabel("");
            gbc.gridx = 2;
            gbc.gridy = 1;

            JPanel enterChatRoomButtonPanel = new JPanel();
            enterChatRoomButtonPanel.setLayout(new BorderLayout());
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.NORTH);
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.EAST);
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.WEST);
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            chatRoomInfoPanel.add(enterChatRoomButtonPanel, gbc);

            JButton enterChatRoomButton = new JButton("입장");
            enterChatRoomButton.setFont(miniFont);
            enterChatRoomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (OpenedViewList.getInstance().getOpenedChatRoomView().containsKey(chatRoom.getRoomId())) {
                        OpenedViewList.getInstance().getOpenedChatRoomView().get(chatRoom.getRoomId()).setState(JFrame.NORMAL);
                        OpenedViewList.getInstance().getOpenedChatRoomView().get(chatRoom.getRoomId()).requestFocus();
                    } else {
                        synchronized (ThreadLock.lock) {
                            DataProvider.getInstance().loadMessageData(chatRoom.getRoomId());
                        }
                        OpenedViewList.getInstance().getOpenedChatRoomView().put(chatRoom.getRoomId(), new ChatRoomView(chatRoom.getRoomId()));
                    }
                }
            });
            enterChatRoomButtonPanel.add(enterChatRoomButton, BorderLayout.CENTER);

            JPanel leaveChatRoomButtonPanel = new JPanel();
            leaveChatRoomButtonPanel.setLayout(new BorderLayout());
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.NORTH);
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.EAST);
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.WEST);
            gbc.gridx = 5;
            gbc.gridy = 0;
            chatRoomInfoPanel.add(leaveChatRoomButtonPanel, gbc);

            JButton leaveChatRoomButton = new JButton("나가기");
            leaveChatRoomButton.setFont(miniFont);
            leaveChatRoomButton.addActionListener(new LeaveChatRoomButtonListener(chatRoom.getRoomId()));
            leaveChatRoomButtonPanel.add(leaveChatRoomButton, BorderLayout.CENTER);
        }

        JPanel trashPanel2 = new JPanel();
        trashPanel2.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        chatRoomListPanel.add(trashPanel2, gbc);

        setVisible(true);
    }

    public String convertLastTime(Date lastTime) {
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(lastTime);
        Calendar currentCalendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format1 = new SimpleDateFormat("a hh:mm");
        int year = lastCalendar.get(Calendar.YEAR);
        int month = lastCalendar.get(Calendar.MONTH) + 1;
        int date = lastCalendar.get(Calendar.DATE);
        String lastTimeString;
        if (year == currentCalendar.get(Calendar.YEAR) && month == currentCalendar.get(Calendar.MONTH) + 1 && date == currentCalendar.get(Calendar.DATE)) {
            lastTimeString = format1.format(lastTime);
        } else {
            lastTimeString = format.format(lastTime);
        }
        return lastTimeString;
    }

    @Override
    public void repaint() {
        String lastMessage;
        super.repaint();
        chatRoomListPanel.removeAll();
        ArrayList<ChatRoom> chatRoomData = DataProvider.getInstance().getChatRoomData();
        int i = 0;
        for (; i < chatRoomData.size(); i++) {
            ChatRoom chatRoom = chatRoomData.get(i);

            JPanel chatRoomInfoTabPanel = new JPanel();
            chatRoomInfoTabPanel.setLayout(new BorderLayout());
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.NORTH);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.SOUTH);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.EAST);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.WEST);
            chatRoomInfoTabPanel.setPreferredSize(new Dimension(getWidth() - 25, 70));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0;
            chatRoomListPanel.add(chatRoomInfoTabPanel, gbc);

            JPanel chatRoomInfoPanel = new JPanel();
            chatRoomInfoPanel.setLayout(new GridBagLayout());
            chatRoomInfoTabPanel.add(chatRoomInfoPanel, BorderLayout.CENTER);

            JLabel chatRoomNameLabel = new JLabel(chatRoomData.get(i).getName());
            chatRoomNameLabel.setPreferredSize(new Dimension(100, 20));
            chatRoomNameLabel.setFont(boldFont);
            chatRoomNameLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weighty = 0.5;
            chatRoomInfoPanel.add(chatRoomNameLabel, gbc);

            if(chatRoomData.get(i).getLastMessage() == null)
                lastMessage = "";
            else
                lastMessage = chatRoomData.get(i).getLastMessage();
            JLabel chatRoomLastMessageLabel = new JLabel(lastMessage);
            chatRoomLastMessageLabel.setFont(smallFont);
            chatRoomLastMessageLabel.setPreferredSize(new Dimension(100, 20));
            chatRoomLastMessageLabel.setForeground(Color.GRAY);
            chatRoomLastMessageLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 1;
            chatRoomInfoPanel.add(chatRoomLastMessageLabel, gbc);

            JLabel headcountLabel = new JLabel(String.valueOf(chatRoomData.get(i).getHeadcount()));
            headcountLabel.setFont(smallFont);
            headcountLabel.setForeground(Color.GRAY);
            headcountLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weighty = 1.0;
            chatRoomInfoPanel.add(headcountLabel, gbc);

            JPanel trashPanel = new JPanel();
            trashPanel.setLayout(new GridBagLayout());
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            chatRoomInfoPanel.add(trashPanel, gbc);

            JLabel chatRoomLastTimeLabel = new JLabel(convertLastTime(chatRoomData.get(i).getLastTime()));
            chatRoomLastTimeLabel.setFont(miniFont);
            chatRoomLastTimeLabel.setForeground(Color.GRAY);
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.weightx = 0;
            chatRoomInfoPanel.add(chatRoomLastTimeLabel, gbc);

            JLabel chatRoomUnreadMessageCountLabel = new JLabel("");
            gbc.gridx = 2;
            gbc.gridy = 1;

            JPanel enterChatRoomButtonPanel = new JPanel();
            enterChatRoomButtonPanel.setLayout(new BorderLayout());
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.NORTH);
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.EAST);
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.WEST);
            gbc.gridx = 4;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            chatRoomInfoPanel.add(enterChatRoomButtonPanel, gbc);

            JButton enterChatRoomButton = new JButton("입장");
            enterChatRoomButton.setFont(miniFont);
            enterChatRoomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (OpenedViewList.getInstance().getOpenedChatRoomView().containsKey(chatRoom.getRoomId())) {
                        OpenedViewList.getInstance().getOpenedChatRoomView().get(chatRoom.getRoomId()).setState(JFrame.NORMAL);
                        OpenedViewList.getInstance().getOpenedChatRoomView().get(chatRoom.getRoomId()).requestFocus();
                    } else {
                        synchronized (ThreadLock.lock) {
                            DataProvider.getInstance().loadMessageData(chatRoom.getRoomId());
                        }
                        OpenedViewList.getInstance().getOpenedChatRoomView().put(chatRoom.getRoomId(), new ChatRoomView(chatRoom.getRoomId()));
                    }
                }
            });
            enterChatRoomButtonPanel.add(enterChatRoomButton, BorderLayout.CENTER);

            JPanel leaveChatRoomButtonPanel = new JPanel();
            leaveChatRoomButtonPanel.setLayout(new BorderLayout());
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.NORTH);
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.EAST);
            leaveChatRoomButtonPanel.add(new JPanel(), BorderLayout.WEST);
            gbc.gridx = 5;
            gbc.gridy = 0;
            chatRoomInfoPanel.add(leaveChatRoomButtonPanel, gbc);

            JButton leaveChatRoomButton = new JButton("나가기");
            leaveChatRoomButton.setFont(miniFont);
            leaveChatRoomButton.addActionListener(new LeaveChatRoomButtonListener(chatRoom.getRoomId()));
            leaveChatRoomButtonPanel.add(leaveChatRoomButton, BorderLayout.CENTER);
        }

        JPanel trashPanel2 = new JPanel();
        trashPanel2.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.weighty = 1.0;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        chatRoomListPanel.add(trashPanel2, gbc);
    }
}
