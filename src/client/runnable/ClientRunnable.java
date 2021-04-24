package client.runnable;

import client.network.ConnectionInfo;

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
                if(in.available() > 0)
                {
                    if (!ThreadStatus.run) {
                        wait();
                    }
                    ThreadStatus.run = true;
                    receivedObject = (String[]) in.readObject();
                    // 메시지 받기
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
