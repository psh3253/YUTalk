package client.data;

import client.model.ChatRoom;
import client.model.Member;
import client.service.LoadChatRoomService;
import client.service.LoadFriendService;

import java.util.ArrayList;
import java.util.Collections;

public class DataProvider {

    private ArrayList<Member> memberData = new ArrayList<>();

    private ArrayList<ChatRoom> chatRoomData = new ArrayList<>();

    private static DataProvider instance = null;

    public static DataProvider getInstance() {
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public void loadMemberData() {
        memberData = LoadFriendService.getInstance().loadFriend();
    }

    public ArrayList<Member> getMemberData() {
        return memberData;
    }

    public void addMemberData(Member member) {
        memberData.add(member);
        Collections.sort(memberData);
    }

    public Boolean containsUserId(String userId) {
        for(int i=0; i< memberData.size(); i++) {
            if(userId.equals(memberData.get(i).getUserId()))
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void loadChatRoomData() {
        chatRoomData = LoadChatRoomService.getInstance().loadChatRoom();
    }

    public ArrayList<ChatRoom> getChatRoomData() {
        return chatRoomData;
    }
}
