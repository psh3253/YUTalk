package client.frame;

import client.data.DataProvider;
import client.model.ChatRoom;
import client.network.ConnectionTermination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatRoomListView extends JFrame {

    public ChatRoomListView() {
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
        Font boldFont = new Font("맑은 고딕", Font.BOLD, 15);
        Font smallFont = new Font("맑은 고딕", Font.PLAIN, 12);
        Font miniFont = new Font("맑은 고딕", Font.PLAIN, 10);
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

        JPanel chatRoomListPanel = new JPanel();
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

        JButton addChatButton = new JButton("새로운 채팅");
        addChatButton.setFont(font);
        northCenterPanel.add(addChatButton, BorderLayout.EAST);

        JButton friendButton = new JButton("친구");
        friendButton.setFont(font);
        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new FriendListView();
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

        JButton settingButton = new JButton("설정");
        settingButton.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 0;
        menuPanel.add(settingButton, gbc);

        ArrayList<ChatRoom> chatRoomData = DataProvider.getInstance().getChatRoomData();
        int i = 0;
        for (; i < chatRoomData.size(); i++) {
            JPanel chatRoomInfoTabPanel = new JPanel();
            chatRoomInfoTabPanel.setLayout(new BorderLayout());
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.NORTH);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.SOUTH);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.EAST);
            chatRoomInfoTabPanel.add(new JPanel(), BorderLayout.WEST);
            chatRoomInfoTabPanel.setPreferredSize(new Dimension(getWidth() - 25, 70));
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            chatRoomListPanel.add(chatRoomInfoTabPanel, gbc);

            JPanel chatRoomInfoPanel = new JPanel();
            chatRoomInfoPanel.setLayout(new GridBagLayout());
            chatRoomInfoTabPanel.add(chatRoomInfoPanel, BorderLayout.CENTER);

            JLabel chatRoomNameLabel = new JLabel(chatRoomData.get(i).getName());
            chatRoomNameLabel.setFont(boldFont);
            chatRoomNameLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.CENTER;
            chatRoomInfoPanel.add(chatRoomNameLabel, gbc);

            JLabel chatRoomLastMessageLabel = new JLabel(chatRoomData.get(i).getLastMessage());
            chatRoomLastMessageLabel.setFont(smallFont);
            chatRoomLastMessageLabel.setForeground(Color.GRAY);
            chatRoomLastMessageLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 1;
            chatRoomInfoPanel.add(chatRoomLastMessageLabel, gbc);

            JPanel trashPanel = new JPanel();
            trashPanel.setLayout(new GridBagLayout());
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH;
            chatRoomInfoPanel.add(trashPanel, gbc);

            JLabel chatRoomLastTimeLabel = new JLabel(convertLastTime(chatRoomData.get(i).getLastTime()));
            chatRoomLastTimeLabel.setFont(miniFont);
            chatRoomLastTimeLabel.setForeground(Color.LIGHT_GRAY);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.CENTER;
            chatRoomInfoPanel.add(chatRoomLastTimeLabel, gbc);

            //JLabel chatRoomUnreadMessageCountLabel = new JLabel("");

            JPanel enterChatRoomButtonPanel = new JPanel();
            enterChatRoomButtonPanel.setLayout(new BorderLayout());
            enterChatRoomButtonPanel.add(new JPanel(), BorderLayout.NORTH);
        }

        setVisible(true);
    }

    public String convertLastTime(Date lastTime) {
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(lastTime);
        Calendar currentCalendar = Calendar.getInstance();
        int year = lastCalendar.get(Calendar.YEAR);
        int month = lastCalendar.get(Calendar.MONTH) + 1;
        int date = lastCalendar.get(Calendar.DATE);
        int hour = lastCalendar.get(Calendar.HOUR);
        int ampm = lastCalendar.get(Calendar.AM_PM);
        int minute = lastCalendar.get(Calendar.MINUTE);
        String lastTimeString;
        if (year == currentCalendar.get(Calendar.YEAR) && month == currentCalendar.get(Calendar.MONTH) + 1 && date == currentCalendar.get(Calendar.DATE)) {
            if(ampm == 0) {
                lastTimeString = "오전 " + hour + ":" + minute;
            } else {
                lastTimeString = "오후 " + hour + ":" + minute;
            }
        } else {
            lastTimeString = year + "-" + month + "-" + date;
        }
        return lastTimeString;
    }
}
