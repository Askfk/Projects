package Client.ClientThreads;

import Client.ClientGUI.Frames.FriendsRequestFrame;
import Tools.ClassTools.Users.User;

import java.util.ArrayList;

/**
 * This class helps to show all new friends requests in a friend request frame.
 * @author Yiming Li
 * @version 2020-03
 */
public class FriendRequestThread extends Thread {

    private ArrayList<User> user;

    /**
     *
     * @param user Users that request to be friends
     */
    public FriendRequestThread(ArrayList<User> user){
        this.user = user;
    }

    public void run(){
        new FriendsRequestFrame(user);
    }

}
