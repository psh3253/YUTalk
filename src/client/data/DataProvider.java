package client.data;

import client.model.Member;
import client.service.LoadFriendService;

import java.util.HashMap;

public class DataProvider {

    private HashMap<String, Member> memberData = new HashMap<>();

    private static DataProvider instance = null;

    public static DataProvider getInstance() {
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public void loadMemberData() {
        memberData = LoadFriendService.getInstance().loadFriend();
    }

    public HashMap<String, Member> getMemberData() {
        return memberData;
    }
}
