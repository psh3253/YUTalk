package client.service;

import client.model.ChatRoom;
import client.model.LoginAccount;
import client.network.ConnectionInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class LoadChatRoomService {

    private static LoadChatRoomService instance = null;

    public static LoadChatRoomService getInstance() {
        if (instance == null)
            instance = new LoadChatRoomService();
        return instance;
    }

    public ArrayList<ChatRoom> loadChatRoom() {
        ObjectInputStream in = ConnectionInfo.getInstance().getIn();
        ObjectOutputStream out = ConnectionInfo.getInstance().getOut();
        HashMap<String, ArrayList<ChatRoom>> responseObject;
        ArrayList<ChatRoom> chatRoomData = new ArrayList<>();

        String[] requestObject = new String[2];
        requestObject[0] = "loadChatRoomRequest";
        requestObject[1] = LoginAccount.getInstance().getMyInfo().getUserId();

        try {
            out.writeObject(requestObject);
            out.flush();
            while (true) {
                responseObject = (HashMap<String, ArrayList<ChatRoom>>) in.readObject();
                if (responseObject.containsKey("loadChatRoomResponse")) {
                    chatRoomData = responseObject.get("loadChatRoomResponse");
                }
                Collections.sort(chatRoomData);
                return chatRoomData;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
