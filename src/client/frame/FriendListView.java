package client.frame;

import client.data.DataProvider;
import client.model.LoginAccount;
import client.model.Member;
import client.network.ConnectionTermination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FriendListView extends JFrame {

    public FriendListView() {
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

        JPanel friendListPanel = new JPanel();
        friendListPanel.setLayout(new GridBagLayout());

        JScrollPane friendListPanelScroll = new JScrollPane(friendListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(friendListPanelScroll, BorderLayout.CENTER);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        container.add(menuPanel, BorderLayout.SOUTH);

        JPanel northCenterPanel = new JPanel();
        northCenterPanel.setLayout(new BorderLayout());
        northPanel.add(northCenterPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("친구");
        titleLabel.setFont(titleFont);
        northCenterPanel.add(titleLabel, BorderLayout.WEST);

        JButton addFriendButton = new JButton("친구 추가");
        addFriendButton.setFont(font);
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFriendView((FriendListView) addFriendButton.getTopLevelAncestor());
                setEnabled(false);
            }
        });
        northCenterPanel.add(addFriendButton, BorderLayout.EAST);

        JButton friendButton = new JButton("친구");
        friendButton.setFont(font);
        friendButton.setEnabled(false);
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

        JPanel myProfileTabPanel = new JPanel();
        myProfileTabPanel.setLayout(new BorderLayout());
        myProfileTabPanel.add(new JPanel(), BorderLayout.NORTH);
        myProfileTabPanel.add(new JPanel(), BorderLayout.WEST);
        myProfileTabPanel.add(new JPanel(), BorderLayout.EAST);
        myProfileTabPanel.add(new JPanel(), BorderLayout.SOUTH);
        myProfileTabPanel.setPreferredSize(new Dimension(getWidth() - 25, 60));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0;
        friendListPanel.add(myProfileTabPanel, gbc);

        JPanel myProfilePanel = new JPanel();
        myProfilePanel.setLayout(new GridBagLayout());
        myProfileTabPanel.add(myProfilePanel, BorderLayout.CENTER);

        JLabel myNameLabel = new JLabel(LoginAccount.getInstance().getMyInfo().getName());
        myNameLabel.setFont(boldFont);
        myNameLabel.setHorizontalAlignment(JLabel.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weighty = 1.0;
        myProfilePanel.add(myNameLabel, gbc);

        JLabel myStatusMessageLabel = new JLabel(LoginAccount.getInstance().getMyInfo().getStatus_message());
        myStatusMessageLabel.setFont(smallFont);
        myStatusMessageLabel.setForeground(Color.GRAY);
        myStatusMessageLabel.setHorizontalAlignment(JLabel.LEFT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        myProfilePanel.add(myStatusMessageLabel, gbc);

        JPanel editButtonPanel = new JPanel();
        editButtonPanel.setLayout(new BorderLayout());
        editButtonPanel.add(new JPanel(), BorderLayout.NORTH);
        editButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
        editButtonPanel.add(new JPanel(), BorderLayout.EAST);
        editButtonPanel.add(new JPanel(), BorderLayout.WEST);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.EAST;
        myProfilePanel.add(editButtonPanel, gbc);

        JButton editButton = new JButton("편집");
        editButton.setFont(font);
        editButtonPanel.add(editButton, BorderLayout.CENTER);

        HashMap<String, Member> memberData = DataProvider.getInstance().getMemberData();
        Iterator<String> iterator = memberData.keySet().iterator();
        int i = 1;

        for (String friendId : memberData.keySet()) {
            JPanel friendProfileTabPanel = new JPanel();
            friendProfileTabPanel.setLayout(new BorderLayout());
            friendProfileTabPanel.add(new JPanel(), BorderLayout.NORTH);
            friendProfileTabPanel.add(new JPanel(), BorderLayout.SOUTH);
            friendProfileTabPanel.add(new JPanel(), BorderLayout.EAST);
            friendProfileTabPanel.add(new JPanel(), BorderLayout.WEST);
            friendProfileTabPanel.setPreferredSize(new Dimension(getWidth() - 25, 60));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.weightx = 1.0;
            gbc.weighty = 0;
            friendListPanel.add(friendProfileTabPanel, gbc);

            JPanel friendProfilePanel = new JPanel();
            friendProfilePanel.setLayout(new GridBagLayout());
            friendProfileTabPanel.add(friendProfilePanel, BorderLayout.CENTER);

            JLabel friendNameLabel = new JLabel(memberData.get(friendId).getName());
            friendNameLabel.setFont(boldFont);
            friendNameLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weighty = 1.0;
            friendProfilePanel.add(friendNameLabel, gbc);

            JLabel friendStatusMessageLabel = new JLabel(memberData.get(friendId).getStatus_message());
            friendStatusMessageLabel.setFont(smallFont);
            friendStatusMessageLabel.setForeground(Color.GRAY);
            friendStatusMessageLabel.setHorizontalAlignment(JLabel.LEFT);
            gbc.gridx = 0;
            gbc.gridy = 1;
            friendProfilePanel.add(friendStatusMessageLabel, gbc);

            JPanel trashPanel = new JPanel();
            trashPanel.setLayout(new GridBagLayout());
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            friendProfilePanel.add(trashPanel, gbc);

            JPanel personalChatButtonPanel = new JPanel();
            personalChatButtonPanel.setLayout(new BorderLayout());
            personalChatButtonPanel.add(new JPanel(), BorderLayout.NORTH);
            personalChatButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
            personalChatButtonPanel.add(new JPanel(), BorderLayout.EAST);
            personalChatButtonPanel.add(new JPanel(), BorderLayout.WEST);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.weightx = 0;
            friendProfilePanel.add(personalChatButtonPanel, gbc);

            JButton personalChatButton = new JButton("채팅");
            personalChatButton.setFont(font);
            personalChatButtonPanel.add(personalChatButton, BorderLayout.CENTER);

            JPanel blockFriendButtonPanel = new JPanel();
            blockFriendButtonPanel.setLayout(new BorderLayout());
            blockFriendButtonPanel.add(new JPanel(), BorderLayout.NORTH);
            blockFriendButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
            blockFriendButtonPanel.add(new JPanel(), BorderLayout.EAST);
            blockFriendButtonPanel.add(new JPanel(), BorderLayout.WEST);
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            friendProfilePanel.add(blockFriendButtonPanel, gbc);

            JButton blockFriendButton = new JButton("차단");
            blockFriendButton.setFont(font);
            blockFriendButtonPanel.add(blockFriendButton, BorderLayout.CENTER);
            i++;
        }

        JPanel trashPanel2 = new JPanel();
        trashPanel2.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        friendListPanel.add(trashPanel2, gbc);

        setVisible(true);
    }
}
