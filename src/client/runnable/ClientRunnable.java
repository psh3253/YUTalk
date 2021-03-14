package client.runnable;

import client.network.ConnectionInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientRunnable implements Runnable {
    @Override
    public void run() {
        ObjectInputStream in;
        ObjectOutputStream out;
        String[] receivedObject;
        try {
            in = ConnectionInfo.getInstance().getIn();
            out = ConnectionInfo.getInstance().getOut();
            while (true) {
                receivedObject = (String[]) in.readObject();
                if (receivedObject[0].equals("test")) {
                    out.writeObject(receivedObject);
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
