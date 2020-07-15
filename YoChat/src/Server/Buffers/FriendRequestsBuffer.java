package Server.Buffers;

import Tools.ClassTools.Users.User;

import java.util.*;

/**
 * This is a buffer that stores all the unsettled friend requests on server.
 * @author Yiming Li
 * @version 2020-03
 */
public class FriendRequestsBuffer {

        // Map<requestToUserId, Arraylist<requestFromUsers>>
        public static final Map<String, ArrayList<User>> friendRequestBuffer = new HashMap<String, ArrayList<User>>();

        /**
         * This method helps to add new requests to the requests buffer.
         * @param toid request to user id
         * @param user request from user
         */
        public static synchronized void addFriendRequestsBuffer(String toid, User user){
                if(friendRequestBuffer.containsKey(toid)){
                        ArrayList<User> temp = friendRequestBuffer.get(toid);
                        temp.add(user);
                        friendRequestBuffer.put(toid, temp);
                }
                else {
                        ArrayList<User> temp = new ArrayList<>();
                        temp.add(user);
                        friendRequestBuffer.put(toid, temp);
                }
        }
}
