package client.listener;

import client.data.DataProvider;
import client.frame.FriendListView;
import client.model.LoginAccount;
import client.runnable.ThreadStatus;
import client.service.AddFriendService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriendButtonListener implements ActionListener {

    private FriendListView view;
    private JTextField friendIdField;

    public AddFriendButtonListener(FriendListView view, JTextField friendIdField) {
        this.friendIdField = friendIdField;
        this.view = view;
    }
    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        JButton addFriendButton = (JButton) e.getSource();

        String friendId = friendIdField.getText();
        ThreadStatus.run = false;
        boolean result = AddFriendService.getInstance().addFriend(addFriendButton, friendId);
        if(!result)
            return;
        DataProvider.getInstance().loadMemberData();
        notify();
        addFriendButton.getTopLevelAncestor().setVisible(false);
        view.setVisible(false);
        new FriendListView();
    }
}
