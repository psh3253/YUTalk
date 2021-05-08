package client.listener;

import client.data.DataProvider;
import client.model.OpenedChatRoomViewList;
import client.runnable.ThreadStatus;
import client.service.LeaveChatRoomService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaveChatRoomListener implements ActionListener {

    private int roomId;

    public LeaveChatRoomListener(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        JButton leaveChatRoomButton = (JButton)e.getSource();
        int result = JOptionPane.showConfirmDialog(leaveChatRoomButton, "채팅방에서 나가시겠습니까?", "채팅방 나가기", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            ThreadStatus.run = false;
            LeaveChatRoomService.getInstance().leaveChatRoom(roomId);
            DataProvider.getInstance().loadChatRoomData();
            notify();
            leaveChatRoomButton.getTopLevelAncestor().setVisible(false);
            leaveChatRoomButton.getTopLevelAncestor().setVisible(true);
            if (OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().containsKey(roomId)) {
                OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().get(roomId).setVisible(false);
                OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().remove(roomId);
            }
        }
    }
}
