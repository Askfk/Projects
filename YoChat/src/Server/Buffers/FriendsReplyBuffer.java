package Server.Buffers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class stores all the friends replies
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class FriendsReplyBuffer {

    public static Map<String, ArrayList<String>> friendsReplyBuffer = new TreeMap<String, ArrayList<String>>();

    public static synchronized void addFriendsReplyBuffer(String s, String reply){
        if(friendsReplyBuffer.containsKey(s)){
            ArrayList<String> temp = friendsReplyBuffer.get(s);
            temp.add(reply);
            friendsReplyBuffer.put(s, temp);
        }
        else {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(reply);
            friendsReplyBuffer.put(s, temp);
        }
    }

}
