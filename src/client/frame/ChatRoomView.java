package client.frame;

import client.model.LoginAccount;
import client.model.Member;

import javax.swing.*;
import java.awt.*;

public class ChatRoomView extends JFrame {
    public ChatRoomView(Member[] members) {

        setTitle(LoginAccount.getInstance().getMyInfo().getName() + "의 단체채팅방");
        setSize(370, 580);
        setResizable(false);

        Font font = new Font("맑은 고딕", Font.PLAIN, 15);

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

        JPanel chatListPanel = new JPanel();
        chatListPanel.setLayout(new GridBagLayout());

        JScrollPane chatListPanelScroll = new JScrollPane(chatListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(chatListPanelScroll, BorderLayout.CENTER);

        JPanel sendPanel = new JPanel();
        sendPanel.setLayout(new GridBagLayout());
        container.add(sendPanel, BorderLayout.SOUTH);

        int i=
        for()
    }
}
