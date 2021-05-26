package client.frame;

import client.data.DataProvider;
import client.model.OpenedViewList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ContactView extends JFrame {

    GridBagConstraints gbc = new GridBagConstraints();
    Font font = new Font("맑은 고딕", Font.PLAIN, 15);
    Font boldFont = new Font("맑은 고딕", Font.BOLD, 15);
    Font smallFont = new Font("맑은 고딕", Font.PLAIN, 12);
    Font miniFont = new Font("맑은 고딕", Font.PLAIN, 11);
    Font titleFont = new Font("맑은 고딕", Font.BOLD, 20);
    JPanel contactListPanel;

    public ContactView(ChatRoomView view, int roomId) {
        setTitle("대화 상대");
        setSize(370, 480);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                OpenedViewList.getInstance().getOpenedChatRoomView().remove(roomId);
                view.setEnabled(true);
            }
        });

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

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        container.add(southPanel, BorderLayout.SOUTH);

        JPanel northCenterPanel = new JPanel();
        northCenterPanel.setLayout(new BorderLayout());
        northPanel.add(northCenterPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("대화 상대");
        titleLabel.setFont(titleFont);
        northCenterPanel.add(titleLabel, BorderLayout.WEST);

        JButton inviteMemberButton = new JButton("친구 초대");
        inviteMemberButton.setFont(font);
        northCenterPanel.add(inviteMemberButton, BorderLayout.EAST);

        contactListPanel = new JPanel();
        contactListPanel.setLayout(new GridBagLayout());

        JScrollPane chatRoomListPanelScroll = new JScrollPane(contactListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(chatRoomListPanelScroll, BorderLayout.CENTER);

        int i = 0;
        ArrayList<String[]> chatRoomMemberData = DataProvider.getInstance().getChatRoomMemberData(roomId);
        System.out.println(chatRoomMemberData.size());
        for (; i < chatRoomMemberData.size(); i++) {
            JPanel contactInfoTabPanel = new JPanel();
            contactInfoTabPanel.setLayout(new BorderLayout());
            contactInfoTabPanel.add(new JPanel(), BorderLayout.NORTH);
            contactInfoTabPanel.add(new JPanel(), BorderLayout.SOUTH);
            contactInfoTabPanel.add(new JPanel(), BorderLayout.EAST);
            contactInfoTabPanel.add(new JPanel(), BorderLayout.WEST);
            contactInfoTabPanel.setPreferredSize(new Dimension(getWidth() - 25, 70));
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.gridheight = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0;
            contactListPanel.add(contactInfoTabPanel, gbc);

            JPanel contactInfoPanel = new JPanel();
            contactInfoPanel.setLayout(new GridBagLayout());
            contactInfoTabPanel.add(contactInfoPanel, BorderLayout.CENTER);

            JLabel contactNameLabel = new JLabel(chatRoomMemberData.get(i)[1]);
            contactNameLabel.setFont(boldFont);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 0.0;
            gbc.weighty = 1.0;
            contactInfoPanel.add(contactNameLabel, gbc);

            JPanel trashPanel = new JPanel();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;

            JButton addFriendButton = new JButton("추가");
            addFriendButton.setFont(boldFont);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.CENTER;
            gbc.weightx = 0;
            contactInfoPanel.add(contactNameLabel, gbc);
        }

        JPanel trashPanel1 = new JPanel();
        trashPanel1.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.weightx = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        contactListPanel.add(trashPanel1, gbc);

        setVisible(true);
    }
}
