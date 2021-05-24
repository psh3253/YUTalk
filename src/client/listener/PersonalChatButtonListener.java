package client.listener;

import client.data.DataProvider;
import client.frame.ChatRoomView;
import client.model.OpenedChatRoomViewList;
import client.runnable.ThreadLock;
import client.service.CreateChatRoomService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonalChatButtonListener implements ActionListener {

    private String friendId;

    public PersonalChatButtonListener(String friendId) {
        this.friendId = friendId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton personalChatButton = (JButton) e.getSource();
        int roomId;
        synchronized (ThreadLock.lock) {
            roomId = CreateChatRoomService.getInstance().createChatRoom("private", friendId);
            DataProvider.getInstance().loadChatRoomData();
        }
        if (roomId == -1)
            return;
        if (OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().containsKey(roomId)) {
            OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().get(roomId).setState(JFrame.NORMAL);
            OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().get(roomId).requestFocus();
        } else {
            OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().put(roomId, new ChatRoomView(roomId));
        }
    }
}
