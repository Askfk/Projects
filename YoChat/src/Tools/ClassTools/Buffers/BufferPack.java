package Tools.ClassTools.Buffers;

import Tools.ClassTools.Infors.Info;
import Tools.ClassTools.Users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Buffer pack class to be exchanged between server and client, contains the messages, requests, replies, friend status
 * and group status
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class BufferPack implements Serializable {

    private static final long serialVersionUID = 595016955L;

    public ArrayList<Info> infoBuffer;

    public ArrayList<User> friendsRequestBuffer;

    public ArrayList<String> friendsReplyBuffer;

    public ArrayList<User> friendsStatus;

    public Map<String, ArrayList<User>> groupsStatus;

    /**
     *
     * @param infoBuffer
     * @param friendsRequestBuffer
     * @param friendsReplyBuffer
     * @param friendsStatus
     * @param groupsStatus
     */
    public BufferPack(ArrayList<Info> infoBuffer, ArrayList<User> friendsRequestBuffer, ArrayList<String> friendsReplyBuffer,
                      ArrayList<User> friendsStatus, Map<String, ArrayList<User>> groupsStatus){
        this.infoBuffer = infoBuffer;
        this.friendsRequestBuffer = friendsRequestBuffer;
        this.friendsReplyBuffer = friendsReplyBuffer;
        this.friendsStatus = friendsStatus;
        this.groupsStatus = groupsStatus;
    }
}
