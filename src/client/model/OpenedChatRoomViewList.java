package client.model;

import client.frame.ChatRoomListView;
import client.frame.ChatRoomView;

import java.util.HashMap;

public class OpenedChatRoomViewList {

    private static OpenedChatRoomViewList instance = null;

    private HashMap<Integer, ChatRoomView> openedChatRoomView = new HashMap<>();

    private ChatRoomListView chatRoomListView = null;

    public static OpenedChatRoomViewList getInstance() {
        if(instance == null)
            instance = new OpenedChatRoomViewList();
        return instance;
    }

    public HashMap<Integer, ChatRoomView> getOpenedChatRoomView() {
        return openedChatRoomView;
    }

    public void setOpenedChatRoomView(HashMap<Integer, ChatRoomView> openedChatRoomView) {
        this.openedChatRoomView = openedChatRoomView;
    }

    public ChatRoomListView getChatRoomListView() {
        return chatRoomListView;
    }

    public void setChatRoomListView(ChatRoomListView chatRoomListView) {
        this.chatRoomListView = chatRoomListView;
    }
}
