package client.runnable;

import client.data.DataProvider;
import client.frame.ChatRoomListView;
import client.frame.ChatRoomView;
import client.model.OpenedViewList;

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
                ChatRoomListView chatRoomListView = OpenedViewList.getInstance().getChatRoomListView();
                if (chatRoomListView != null && chatRoomListView.isVisible()) {
                    chatRoomListView.repaint();
                    chatRoomListView.revalidate();
                }
                Set<Integer> keySet = OpenedViewList.getInstance().getOpenedChatRoomView().keySet();
                ArrayList<Integer> openedChatRoomViewList = new ArrayList<>(keySet);
                for (int i = 0; i < openedChatRoomViewList.size(); i++) {
                    ChatRoomView chatRoomView = OpenedViewList.getInstance().getOpenedChatRoomView().get(openedChatRoomViewList.get(i));
                    synchronized (ThreadLock.lock) {
                        DataProvider.getInstance().loadMessageData(openedChatRoomViewList.get(i));
                    }
                    chatRoomView.repaint();
                    chatRoomView.revalidate();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
