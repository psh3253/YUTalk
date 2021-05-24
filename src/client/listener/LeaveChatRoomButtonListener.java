package client.listener;

import client.model.OpenedChatRoomViewList;
import client.runnable.ThreadLock;
import client.service.LeaveChatRoomService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaveChatRoomButtonListener implements ActionListener {

    private int roomId;

    public LeaveChatRoomButtonListener(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton leaveChatRoomButton = (JButton) e.getSource();
        boolean result;
        int choice = JOptionPane.showConfirmDialog(leaveChatRoomButton, "채팅방에서 나가시겠습니까?", "채팅방 나가기", JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            synchronized (ThreadLock.lock) {
                result = LeaveChatRoomService.getInstance().leaveChatRoom(roomId);
            }
            if (!result) {
                return;
            }
        }
        else {
            return;
        }
        if (OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().containsKey(roomId)) {
            OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().get(roomId).setVisible(false);
            OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().remove(roomId);
        }
    }
}

