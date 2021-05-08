package client.runnable;

import client.network.ConnectionInfo;
import client.service.LoadChatRoomService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientRunnable implements Runnable {
    @Override
    public synchronized void run() {
        ObjectInputStream in = ConnectionInfo.getInstance().getIn();
        ObjectOutputStream out = ConnectionInfo.getInstance().getOut();
        String[] receivedObject;
        try {
            while (true) {
                if (!ThreadStatus.run) {
                    wait();
                }
                ThreadStatus.run = true;
                receivedObject = (String[]) in.readObject();
                if (receivedObject[0].equals("reloadChatRoom")) {
                    LoadChatRoomService.getInstance().loadChatRoom();
                }
                // 메시지 받기
            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
