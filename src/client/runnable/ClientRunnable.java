package client.runnable;

import client.data.DataProvider;
import client.frame.ChatRoomListView;
import client.frame.ChatRoomView;
import client.model.OpenedChatRoomViewList;

import java.util.ArrayList;
import java.util.Set;

public class ClientRunnable implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                synchronized (ThreadLock.lock) {
                    DataProvider.getInstance().loadChatRoomData();
                }
                ChatRoomListView chatRoomListView = OpenedChatRoomViewList.getInstance().getChatRoomListView();
                if (chatRoomListView != null && chatRoomListView.isVisible()) {
                    chatRoomListView.repaint();
                    chatRoomListView.revalidate();
                }
                Set<Integer> keySet = OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().keySet();
                ArrayList<Integer> openedChatRoomViewList = new ArrayList<>(keySet);
                for (int i = 0; i < openedChatRoomViewList.size(); i++) {
                    ChatRoomView chatRoomView = OpenedChatRoomViewList.getInstance().getOpenedChatRoomView().get(openedChatRoomViewList.get(i));
                    synchronized (ThreadLock.lock) {
                        DataProvider.getInstance().loadMessageData(openedChatRoomViewList.get(i));
                    }
                    chatRoomView.repaint();
                    chatRoomView.revalidate();
                }
                Thread.sleep(1000);
            }
            // 메시지 받기
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
