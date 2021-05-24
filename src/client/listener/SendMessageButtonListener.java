package client.listener;

import client.data.DataProvider;
import client.frame.ChatRoomView;
import client.runnable.ThreadLock;
import client.service.SendMessageService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageButtonListener implements ActionListener {

    private int roomId;

    private JTextArea messageArea;

    public SendMessageButtonListener(int roomId, JTextArea messageArea){
        this.roomId = roomId;
        this.messageArea = messageArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = messageArea.getText();
        boolean result;
        synchronized (ThreadLock.lock) {
            result = SendMessageService.getInstance().sendMessage(roomId, message);
            //DataProvider.getInstance().loadMessageData(roomId);
        }
        if (!result) {
            return;
        }
        messageArea.setText("");
        //view.repaint();
        //view.revalidate();
        //view.requestFocus();
    }
}
