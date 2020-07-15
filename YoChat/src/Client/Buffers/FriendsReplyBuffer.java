package Client.Buffers;

import java.util.ArrayList;

/**
 * This is FriendsReplyBuffer which contains all the unsettled replies that current user reply to others.
 * @author Yiming Li
 * @version 2020-03
 */

public class FriendsReplyBuffer {

    public static ArrayList<String> friendsReplyBuffer = new ArrayList<String>();

    public static synchronized void addFriendsReplyBuffer(String s){
        friendsReplyBuffer.add(s);
    }

}
