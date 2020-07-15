package Client.Buffers;

import Tools.ClassTools.Users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the friends request buffer which contains all the unsettled friends requests that current user have made.
 * @author Yiming Li
 * @version 2020-03
 */

public class FriendsRequestBuffer {
    public static ArrayList<User> friendsRequestBuffer = new ArrayList<>();

    public static synchronized void addFriendsRequestBuffer(User user){
        for(User user1 : friendsRequestBuffer){
            if(user.getUID().equals(user1.getUID())){
                return;
            }
        }
        friendsRequestBuffer.add(user);
    }
}
